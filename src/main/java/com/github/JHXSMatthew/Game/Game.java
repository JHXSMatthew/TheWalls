package com.github.JHXSMatthew.Game;

import com.github.JHXSMatthew.Config.Config;
import com.github.JHXSMatthew.Config.Message;
import com.github.JHXSMatthew.Main;
import com.github.JHXSMatthew.Objects.FireWorkUlt;
import com.github.JHXSMatthew.Objects.Wall;
import com.github.JHXSMatthew.event.GameEndEvent;
import com.github.JHXSMatthew.event.GameInitReadyEvent;
import com.github.JHXSMatthew.event.GameStartEvent;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CommentedOutCode")
public class Game {

    private final GameMap map;
    private final World world;
    private final List<GamePlayer> players;
    private final List<GameTeam> teams;

    private final GameTeam specTeam;
    /*
      0 is lobby
      1 is starting
      2 is wall not fall
      3 is wall fall
      4 is finishing
    */
    @Getter
    private GameState gameState = GameState.LOBBY;
    private BukkitTask currentTask = null;

    public Game(GameMap m, World w) {
        map = m;
        world = w;

        players = new ArrayList<>();
        teams = new ArrayList<>();
        for (int i = 0; i < map.getSpanwPointsCount(); i++) {
            teams.add(new GameTeam(map.getSpawnPoints(i), false));
            teams.get(i).setName(Main.getMsg().parseTeamName(i));
        }


        specTeam = (new GameTeam(map.getSpawnPoints(0), true));
        specTeam.setName(ChatColor.GRAY + "观战");

        Bukkit.getPluginManager().callEvent(new GameInitReadyEvent());
        System.out.println("should fire init ready");
    }

    public String getWorld() {
        return world.getName();
    }


    private List<Chunk> getChunks() {
        int minX = map.getDown().getBlockX();
        int minZ = map.getDown().getBlockY();
        int maxX = map.getUp().getBlockX();
        int maxZ = map.getUp().getBlockZ();
        int minY = map.getDown().getBlockY();
        int maxY = map.getUp().getBlockY();
        Block min = world.getBlockAt(minX, minY, minZ);
        Block max = world.getBlockAt(maxX, maxY, maxZ);
        Chunk cMin = min.getChunk();
        Chunk cMax = max.getChunk();
        List<Chunk> chunks = new ArrayList<>();

        for (int cx = cMin.getX(); cx < cMax.getX(); cx++) {
            for (int cz = cMin.getZ(); cz < cMax.getZ(); cz++) {
                Chunk currentChunk = world.getChunkAt(cx, cz);
                chunks.add(currentChunk);
            }
        }
        return chunks;
    }

    public void joinGame(GamePlayer p) {
        players.add(p);
        if (gameState == GameState.LOBBY || gameState == GameState.STARTING) {

            GameTeam team = teams.get(0);
            for (GameTeam t : teams) {
                if (team.getPlayerAmount() > t.getPlayerAmount()) {
                    team = t;
                }
            }

            team.joinTeam(p);
            p.setGameLobby(this, team);
            sendToAllMessage(p.get().getName() + Main.getMsg().getMessage("join-team-msg1") + team.getName() + ChatColor.GREEN + "  (" + players.size() + "/" + map.getPlayerLimit() + ")");
            Main.getIc().giveLobbyItem(p);
            if (players.size() >= map.getPlayerLimit() / 2 && gameState == GameState.LOBBY) {
                switchState(GameState.STARTING);
            } else {
                updateScoreBoard();
            }
        } else {
            joinSpec(p);
        }


    }

    public void joinSpec(GamePlayer gp) {
        gp.setGameSpec(this, specTeam);
        checkWinning();
        updateScoreBoard();
    }

    public void checkWinning() {
        if (gameState == GameState.FINISHING) {
            return;
        }
        GameTeam preTeam = null;

        for (GameTeam t : teams) {
            if (t.getPlayerAmount() > 0) {
                preTeam = t;
                break;
            }
        }

        for (GameTeam t : teams) {
            if (t.getPlayerAmount() > 0) {
                if (t != preTeam) {
                    return;
                }
            }
        }

        switchState(GameState.FINISHING);
    }

    public void joinTeamRequest(GamePlayer p, String teamName) {
        int count = 0;
        GameTeam target = null;
        for (GameTeam t : teams) {
            if (t.getName().equals(teamName)) {
                target = t;
            }
            count += t.getPlayerAmount();
        }
        if (target == null) {
            throw new IllegalStateException("Team is Null");
        }

        if (p.getTeam().equals(target)) {
            p.get().sendMessage(Message.prefix + Main.getMsg().getMessage("team-join-same"));
            return;
        }

        if (target.getPlayerAmount() - count / teams.size() - 1 > 0 || target.getPlayerAmount() >= map.getPlayerLimit() / teams.size()) {
            p.get().sendMessage(Message.prefix + Main.getMsg().getMessage("team-join-full"));
            return;
        }

        p.getTeam().removeTeam(p);
        target.joinTeam(p);
        p.setTeam(target);
        sendToAllMessage(p.get().getName() + Main.getMsg().getMessage("team-join-msg") + target.getName());
        updateScoreBoard();
    }


    public void quitGame(GamePlayer gp) {
        gp.get().teleport(Main.getGc().getLobby());
        gp.getTeam().removeTeam(gp);
        players.remove(gp);
        if (!gp.isSpec()) {
            if (gameState == GameState.LOBBY || gameState == GameState.STARTING) {
                sendToAllMessage(Main.getMsg().getMessage("lobby-quit-msg1") + gp.get().getName() + Main.getMsg().getMessage("lobby-quit-msg2") + ChatColor.GREEN + "  (" + players.size() + "/" + map.getPlayerLimit() + ")");
            } else if (gameState != GameState.FINISHING) {
                sendToAllMessage(gp.getTeam().getName() + Main.getMsg().getMessage("game-quit-msg1") + gp.get().getName() + Main.getMsg().getMessage("game-quit-msg2"));
                checkWinning();
            }
        }
        updateScoreBoard();

    }

    public int getPlayerCount() {
        return players.size();

    }

    public GamePlayer getFirstPlayer() {
        for (GamePlayer gp : players) {
            if (gp.get().getGameMode() != GameMode.SPECTATOR) {
                return gp;
            }
        }
        return players.get(0);
    }

    private void startCount(int state) {
        switch (state) {
            case 1:
                currentTask = new BukkitRunnable() {
                    int count = 90;

                    @Override
                    public void run() {
                        if (count > 0) {
                            if (players.size() < map.getPlayerLimit() / 2) {
                                switchState(GameState.LOBBY);
                                cancel();
                            } else if (players.size() == map.getPlayerLimit() && count > 30) {
                                sendToAllMessage(Main.getMsg().getMessage("time-jump-to-30"));
                                sendToAllSound(Sound.PISTON_EXTEND);
                                count = 30;
                            }

                            if (count == 90 || count == 60 || count == 30 || count == 10) {
                                sendToAllMessage(Main.getMsg().getMessage("start-count-msg3") + count + Main.getMsg().getMessage("start-count-msg4"));
                            }
                            if (count < 10) {
                                for (GameTeam gt : teams) {
                                    gt.getLocation().getChunk().load();
                                }
                                sendToAllTitle(String.valueOf(count));
                                sendToAllSound(Sound.CLICK);
                            }
                            sendToAllActionBarAndExp(Main.getMsg().getMessage("start-count-msg1") + count + Main.getMsg().getMessage("start-count-msg2"), count);

                        } else {
                            sendToAllEXP(0);
                            switchState(GameState.WALL_NOT_FALL);
                            cancel();
                        }
                        count--;

                    }

                }.runTaskTimer(Main.get(), 0, 20);
                break;
            case 2:
                try {
                    currentTask.cancel();
                } catch (Exception ignored) {
                }
                currentTask = new BukkitRunnable() {
                    int count = map.getWallTime();

                    @Override
                    public void run() {
                        if (count > 0) {
                            if (count == 300 || count == 120 || count == 60) {
                                String s = Main.getMsg().getMessage("wall-fall-time3") + count + Main.getMsg().getMessage("wall-fall-time4");
                                sendToAllSoundAndMsgAndTitle(Sound.CLICK, s, s);
                            }

                            if (count < 10) {
                                sendToAllSound(Sound.ARROW_HIT);
                            }

                            sendToAllActionBar(Main.getMsg().getMessage("wall-fall-time1") + count + Main.getMsg().getMessage("wall-fall-time2"));
                            count--;
                            return;
                        }


                        switchState(GameState.WALL_FALL);
                        cancel();
                    }


                }.runTaskTimer(Main.get(), 0, 20);
                break;
            case 4:
                try {
                    currentTask.cancel();
                } catch (Exception ignored) {
                }
                currentTask = new BukkitRunnable() {
                    int count = 15;

                    @Override
                    public void run() {
                        if (count > 0) {
                            for (GamePlayer temp : players) {
                                if (!temp.isSpec()) {
                                    FireWorkUlt.spawnFireWork(temp.get().getLocation(), temp.get().getWorld());
                                }
                            }
                            count--;
                            return;
                        }


                        for (GamePlayer temp : players) {
                            Main.getBc().quitSend(temp.get());
                        }


                        new BukkitRunnable() {
                            public void run() {
                                if (!Bukkit.getOnlinePlayers().isEmpty() && Main.getGc().getGame() != null && Main.getGc().getGame().getGameState() == GameState.FINISHING) {
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        Main.getBc().quitSend(p);
                                    }

                                    new BukkitRunnable() {
                                        public void run() {
                                            if (!Bukkit.getOnlinePlayers().isEmpty() && Main.getGc().getGame() != null && Main.getGc().getGame().getGameState() == GameState.FINISHING) {
                                                for (Player p : Bukkit.getOnlinePlayers()) {
                                                    p.kickPlayer("战墙强制退出");
                                                }
                                            }

                                        }
                                    }.runTaskLater(Main.get(), 100);
                                }
                            }
                        }.runTaskLater(Main.get(), 100);

                        cancel();
                    }


                }.runTaskTimer(Main.get(), 0, 20);
                break;
        }
    }


    private void balanceTeam() {
		/*
		int average = players.size()/teams.size();
		if(average ==0){
			return;
		}
		
		for(GameTeam gt : teams){
			while(gt.getPlayerAmount() > average){
				for(GameTeam temp : teams){
					if(temp.getPlayerAmount() < average){
						GamePlayer removePlayer = gt.getPlayers().get(gt.getPlayerAmount() -1);
						gt.removeTeam(removePlayer);
						temp.joinTeam(removePlayer);
						break;
					}
				}
				
			}
		}
		*/
    }

    public int getTop() {
        return map.getUp().getBlockY();
    }

    public int getDown() {
        return map.getDown().getBlockY();
    }

    public void switchState(GameState state) {
        switch (state) {
            case LOBBY:
                if (gameState != GameState.LOBBY) {
                    System.out.print("Illegal Switch to 0");
                    return;
                }
                Main.getMsg().getMessage("not-enough-people");
                sendToAllSound(Sound.VILLAGER_NO);
                sendToAllEXP(0);
                updateScoreBoard();
                break;

            case STARTING:
                gameState = GameState.STARTING;
                startCount(1);
                updateScoreBoard();
                break;
            case WALL_NOT_FALL:
                gameState = GameState.WALL_NOT_FALL;

                balanceTeam();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        specTeam.sendTeamActionBar(Main.getMsg().getSpecMsg());
                    }

                }.runTaskTimerAsynchronously(Main.get(), 0, 20);
                for (GameTeam gt : teams) {
                    gt.prepareTeamForBegin();
                }

                sendToAllMessage(ChatColor.YELLOW + "游戏开始,您有 " + map.getWallTime() / 60 + " 分钟生存为大战做准备!");
                sendToAllMessage(ChatColor.RED + ChatColor.BOLD.toString() + "团结协作,六人齐心协力才是取胜的宝典!均分矿物,两个半神装永远比一个神装强!为了胜利,加油!");
                sendToAllMessage(ChatColor.RED + ChatColor.BOLD.toString() + "团结协作,六人齐心协力才是取胜的宝典!均分矿物,两个半神装永远比一个神装强!为了胜利,加油!");
                sendToAllMessage(ChatColor.RED + ChatColor.BOLD.toString() + "团结协作,六人齐心协力才是取胜的宝典!均分矿物,两个半神装永远比一个神装强!为了胜利,加油!");

                sendToAllSound(Sound.LEVEL_UP);
                sendToAllTitle(ChatColor.RED + map.getDisplayerName(), ChatColor.YELLOW + "建筑师: " + map.getBuilder());
                startCount(2);
                updateScoreBoard();
                Bukkit.getPluginManager().callEvent(new GameStartEvent());
                break;

            case WALL_FALL:
                gameState = GameState.WALL_FALL;
                List<Wall> walls = map.getWalls();
                for (Wall w : walls) {
                    w.setFallen();
                }
                for (GamePlayer p : players) {
                    p.get().setHealth(p.get().getMaxHealth());
                    p.get().setFoodLevel(20);
                    p.get().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                }
                sendToAllSoundAndMsgAndTitle(Sound.EXPLODE, Main.getMsg().getMessage("wall-fall-msg"), Main.getMsg().getMessage("wall-fall-title"));
                break;
            case FINISHING:
                gameState = GameState.FINISHING;
                for (GamePlayer p : players) {
                    if (!p.isSpec()) {
                        p.getGs().addWin();
//						if(Main.economy != null){
//							 try{
//							 Main.economy.depositPlayer(Bukkit.getOfflinePlayer(p.get().getUniqueId()), null, Config.RealMoney);
//							 }catch (Exception e){
//								 Bukkit.getLogger().info("EXCEPTION WHILE DEPOSIT TO ID : " + p.get().getName());
//								 e.printStackTrace();
//							 }
//							 p.get().sendMessage(ChatColor.DARK_GREEN + "YourCraft >> " + ChatColor.WHITE + "恭喜您积极参与游戏，获得金币奖励 "+ChatColor.GOLD +   Config.RealMoney +".00 " + ChatColor.WHITE + " 枚!");
//						 }

                        p.getGs().giveMoney(Config.wimMoney);
                        p.get().sendMessage(Message.prefix + Main.getMsg().getMessage("win-coin-earn1") + Config.wimMoney + Main.getMsg().getMessage("win-coin-earn2"));
                        p.sendTitle(ChatColor.RED + "你赢了");
                    }
                }
                startCount(4);
                Bukkit.getPluginManager().callEvent(new GameEndEvent());
                break;
        }
    }

    public void changeTeamTo(Player p, int i) {
        // TODO
    }

    private Scoreboard getEmptyScoreBoard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        for (GameTeam t : teams) {
            Team local_team = board.registerNewTeam(t.getName());
            t.initialTeamScoreBoard(local_team);
        }

        Objective ob = board.registerNewObjective("walls", "dummy");
        ob.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "YourCraft");
        ob.setDisplaySlot(DisplaySlot.SIDEBAR);

        return board;
    }

    private void updateScoreBoard() {

        switch (gameState) {
            case LOBBY:
            case STARTING:
                for (GamePlayer gp : players) {
                    updateScoreboardLobby(gp);
                }
                return;

        }

        Scoreboard board = getEmptyScoreBoard();
        Objective ob = board.getObjective(DisplaySlot.SIDEBAR);
        Score score;
        score = ob.getScore("地图: " + ChatColor.AQUA + map.getDisplayerName());
        score.setScore(11);

        score = ob.getScore("人数: " + players.size());
        score.setScore(10);

        score = ob.getScore("玩家: " + (players.size() - specTeam.getPlayerAmount()));
        score.setScore(9);

        score = ob.getScore("观战: " + specTeam.getPlayerAmount());
        score.setScore(8);

        score = ob.getScore("  ");
        score.setScore(7);
        int i = 6;
        for (GameTeam t : teams) {
            score = ob.getScore(t.getName() + ": " + ChatColor.RESET + t.getPlayerAmount());
            score.setScore(i);
            i--;
        }
        score = ob.getScore(" ");
        score.setScore(i--);

        score = ob.getScore(ChatColor.AQUA + "www.mcndsj.com");
        score.setScore(i--);
        for (GamePlayer gp : players) {
            gp.get().setScoreboard(board);
        }
    }

    public void updateScoreboardLobby(GamePlayer gp) {
        Scoreboard board = getEmptyScoreBoard();
        Objective ob = board.getObjective(DisplaySlot.SIDEBAR);
        Score score;
        switch (gameState) {
            case LOBBY:
                score = ob.getScore("场次: " + ChatColor.GREEN + gp.getGs().getGames());
                score.setScore(10);

                score = ob.getScore("胜场: " + ChatColor.GREEN + gp.getGs().getWins());
                score.setScore(9);

                score = ob.getScore("击杀: " + ChatColor.GREEN + gp.getGs().getKills());
                score.setScore(8);

                score = ob.getScore("死亡: " + ChatColor.GREEN + gp.getGs().getDeath());
                score.setScore(7);

                score = ob.getScore("代币: " + ChatColor.GREEN + gp.getGs().getMoney());
                score.setScore(6);

                score = ob.getScore("职业: " + gp.getKitName());
                score.setScore(5);


                score = ob.getScore("  ");
                score.setScore(4);

                score = ob.getScore("地图: " + ChatColor.AQUA + map.getDisplayerName());
                score.setScore(3);

                score = ob.getScore("玩家: " + players.size() + "/" + map.getPlayerLimit());
                score.setScore(2);

                score = ob.getScore("状态: " + ChatColor.GOLD + "等待中...");
                score.setScore(1);

                score = ob.getScore(" ");
                score.setScore(0);

                score = ob.getScore(ChatColor.AQUA + "www.mcndsj.com");
                score.setScore(-1);

                gp.get().setScoreboard(board);

                return;
            case STARTING:
                board = getEmptyScoreBoard();
                ob = board.getObjective(DisplaySlot.SIDEBAR);
                score = ob.getScore("场次: " + ChatColor.GREEN + gp.getGs().getGames());
                score.setScore(10);

                score = ob.getScore("胜场: " + ChatColor.GREEN + gp.getGs().getWins());
                score.setScore(9);

                score = ob.getScore("击杀: " + ChatColor.GREEN + gp.getGs().getKills());
                score.setScore(8);

                score = ob.getScore("死亡: " + ChatColor.GREEN + gp.getGs().getDeath());
                score.setScore(7);

                score = ob.getScore("代币: " + ChatColor.GREEN + gp.getGs().getMoney());
                score.setScore(6);

                score = ob.getScore("职业: " + gp.getKitName());
                score.setScore(5);


                score = ob.getScore("  ");
                score.setScore(4);

                score = ob.getScore("地图: " + ChatColor.AQUA + map.getDisplayerName());
                score.setScore(3);

                score = ob.getScore("玩家: " + players.size() + "/" + map.getPlayerLimit());
                score.setScore(2);

                score = ob.getScore("状态: " + ChatColor.GOLD + "开始中...");
                score.setScore(1);

                score = ob.getScore(" ");
                score.setScore(0);

                score = ob.getScore(ChatColor.AQUA + "www.mcndsj.com");
                score.setScore(-1);

                gp.get().setScoreboard(board);

        }
    }

    public void sendToAllMessage(String msg) {
        for (GamePlayer p : players) {
            p.get().sendMessage(Message.prefix + msg);
        }
    }

    private void sendToAllActionBar(String str) {
        for (GamePlayer p : players) {
            p.sendActionBar(str);
        }
    }

    private void sendToAllActionBarAndExp(String str, int level) {
        for (GamePlayer p : players) {
            p.sendActionBar(str);
            p.get().setLevel(level);
        }
    }

    public void sendAllChatMessage(String str) {
        for (GamePlayer p : players) {
            p.get().sendMessage(Main.getMsg().getMessage("all-msg-prefix") + str);
        }
        Bukkit.getConsoleSender().sendMessage(str);
    }


    private void sendToAllEXP(int level) {
        for (GamePlayer p : players) {
            p.get().setLevel(level);
        }
    }

    private void sendToAllSound(Sound s) {
        for (GamePlayer p : players) {
            p.get().playSound(p.get().getLocation(), s, 1, 1);
        }
    }


    private void sendToAllSoundAndMsgAndTitle(Sound s, String msg, String title) {
        for (GamePlayer p : players) {
            p.get().playSound(p.get().getLocation(), s, 1, 1);
            p.get().sendMessage(Message.prefix + msg);
            p.sendTitle(title);
        }
    }

    private void sendToAllTitle(String title) {
        for (GamePlayer p : players) {
            p.sendTitle(title);
        }
    }

    private void sendToAllTitle(String title, String small) {
        for (GamePlayer p : players) {
            p.sendTitle(title, small);
        }
    }
    /*
     * public
     *
     */


    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isBuildAllow(Location l) {
        if (gameState == GameState.LOBBY || gameState == GameState.STARTING) {
            return false;
        }
        Location up = map.getUp();
        Location down = map.getDown();
        if (!(l.getBlockX() > down.getBlockX() && l.getBlockX() < up.getBlockX() && l.getBlockY() > down.getBlockY() && l.getBlockY() < up.getBlockY() && l.getBlockZ() < up.getBlockZ() && l.getBlockZ() > down.getBlockZ())) {
            return false;
        }

        if (gameState == GameState.WALL_NOT_FALL) {
            for (Wall w : map.getWalls()) {
                if (w.isWallBlock(l)) return false;
            }
        }

        return true;
    }

    public boolean isInGame(GamePlayer p) {
        return players.contains(p);
    }

    public String getGameStateString() {
        switch (gameState) {
            case LOBBY:
                return ChatColor.GREEN + "等待中";
            case STARTING:
                return ChatColor.GREEN + "开始中";
            case WALL_NOT_FALL:
            case WALL_FALL:
            case FINISHING:
                return ChatColor.RED + "游戏中";
        }
        return ChatColor.RED + "游戏中";
    }


}
