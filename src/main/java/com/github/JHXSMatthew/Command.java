package com.github.JHXSMatthew;

import com.github.JHXSMatthew.Game.GameMap;
import com.github.JHXSMatthew.Game.GameState;
import com.github.JHXSMatthew.Listeners.SelectEvent;
import com.github.JHXSMatthew.Objects.Wall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Command implements CommandExecutor {
    private static SelectEvent se = null;
    private GameMap setMap = null;
    private Editor editor;
    private final Map<Integer,Help> helps;
    public Command() {
        helps = new HashMap<>();
        Help p1 = new Help(
                ChatColor.AQUA+"=========[TheWall]=========",
                ChatColor.YELLOW+"/wall help [页码] 显示本菜单",
                ChatColor.YELLOW+"/wall wand 获取设置场地工具",
                ChatColor.YELLOW+"/wall create <名字> <显示名字> 创建新的场地",
                ChatColor.YELLOW+"/wall builder <名字> 场地建造者",
                ChatColor.YELLOW+"/wall bound 设置场地范围§l(一定要提前用工具选择好范围)",
                ChatColor.GRAY+"<>内内容为必填,[]内内容为选填",
                ChatColor.AQUA+"===========(1/3)==========="
        );
        helps.put(1,p1);
        Help p2 = new Help(
                ChatColor.AQUA+"=========[TheWall]=========",
                ChatColor.YELLOW+"/wall wall 为当前场地添加一面墙",
                ChatColor.YELLOW+"/wall spawn 添加一个队伍出生点"+ChatColor.GRAY+"(顺序请按红,黄,蓝,绿)",
                ChatColor.YELLOW+"/wall player <数量> 设置当前场地最大玩家数量",
                ChatColor.YELLOW+"/wall walltime <时间/s> 设置墙倒塌前的时间",
                ChatColor.YELLOW+"/wall percentage <0~1> ???",
                ChatColor.GRAY+"<>内内容为必填,[]内内容为选填",
                ChatColor.AQUA+"===========(2/3)==========="
        );
        helps.put(2,p2);
        Help p3 = new Help(
                ChatColor.AQUA+"=========[TheWall]=========",
                ChatColor.YELLOW+"/wall lobby 设置当前位置为等待大厅",
                ChatColor.YELLOW+"/wall save 设置完成时保存当前地图",
                ChatColor.YELLOW+"/wall start 强制开始这场游戏",
                ChatColor.GRAY+"<>内内容为必填,[]内内容为选填",
                ChatColor.AQUA+"===========(2/3)==========="
        );
        helps.put(3,p3);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String cmd, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("[TheWall]" + ChatColor.RED + "抱歉,该指令只能由玩家执行");
            return true;
        }
        Player p = (Player)sender;
        if((!(sender.isOp()))
                && (!sender.hasPermission("thewall.admin")))
        {
            sender.sendMessage("[§bTheWall§r]"+ChatColor.RED+"对不起,您没有足够的权限");
        }
        if(args.length == 0){
            sender.sendMessage(ChatColor.AQUA+"=========[TheWall]=========");
            sender.sendMessage(ChatColor.YELLOW +"欢迎使用TheWall(YC)战墙插件");
            sender.sendMessage(ChatColor.YELLOW+"您可以输入/wall help 获取更多帮助");
            sender.sendMessage(ChatColor.YELLOW+"或者输入/wall setup开始创建游戏");
            sender.sendMessage(ChatColor.YELLOW+"插件作者QQ: 68638023");
            sender.sendMessage(ChatColor.YELLOW+"如遇到bug,请及时提交");
            sender.sendMessage(ChatColor.AQUA+"===========================");
            return true;
        }
        if(args.length == 1){
            String s1 = args[0];
            if(s1.equalsIgnoreCase("help")){
                Help h = helps.get(1);
                h.sendHelp(p);
                return true;
            }
            if(s1.equalsIgnoreCase("setup")){
                editor = new Editor();
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"成功开始场地创建");
                p.sendMessage("[§bTheWall§r]"+ChatColor.YELLOW+"请输入/wall create <名字> <显示名字>来创建一个场地");
                return true;
            }
            if(s1.equalsIgnoreCase("wand")){
                if (se == null) {
                    se = new SelectEvent();
                    Main.get().getServer().getPluginManager().registerEvents(se, Main.get());
                }
                se.getWand(p);
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"工具已给予");
                return true;
            }
            if(s1.equalsIgnoreCase("start")){
                Main.getGc().getGame().switchState(GameState.WALL_NOT_FALL);
                return true;
            }
            if(s1.equalsIgnoreCase("bound")){
                setMap.setBound(se.left.clone(), se.right.clone());
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"区域已设置");
                p.sendMessage(ChatColor.YELLOW+"坐标一:"+"x: "+se.left.getBlockX()+" y: "+se.left.getBlockY()+" z: "+se.left.getBlockZ());
                p.sendMessage(ChatColor.YELLOW+"坐标二:"+"x: "+se.right.getBlockX()+" y: "+se.right.getBlockY()+" z: "+se.right.getBlockZ());
                editor.setBound(true);
                editor.sendEditor(p);
                se.clear();
                return true;
            }
            if(s1.equalsIgnoreCase("wall")){
                setMap.addWall(new Wall(se.left.clone(), se.right.clone()));
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"成功添加城墙");
                se.clear();
                editor.setWall(editor.getWall()+1);
                editor.sendEditor(p);
                return true;
            }
            if(s1.equalsIgnoreCase("spawn")){
                setMap.addSpawnPoints(p.getLocation());
                String team = Main.getMsg().parseTeamName(editor.getSpawn());
                editor.setSpawn(editor.getSpawn()+1);
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"成功设置 "+team+" §a队出声点");
                editor.sendEditor(p);
                return true;
            }
            if(s1.equalsIgnoreCase("lobby")){
                Main.getCon().lobby = p.getLocation();
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"大厅设置成功");
                editor.setLobby(true);
                editor.sendEditor(p);
                return true;
            }
            if(s1.equalsIgnoreCase("save")){
                setMap.setPercentage(1f);
                setMap.save();

                File target = new File(Bukkit.getPluginManager().getPlugin("TheWalls").getDataFolder() + "/" + "maps" + "/", setMap.getName());
                File source = new File(p.getWorld().getName());
                Main.getWc().copyWorld(source, target);
                Main.getCon().save();
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"保存成功,场地设置完毕");
                return true;
            }
        }
        if(args.length == 2){
            String s1 = args[0];
            String s2 = args[1];


            if(s1.equalsIgnoreCase("help")){
                Integer page = null;
                try {
                    page = Integer.parseInt(s2);
                }catch (Exception e){
                    sender.sendMessage("[§bTheWall§r]"+ChatColor.RED+"参数有误:请输入1-3的数字");
                }
                if (page == null) {
                    return true;
                }
                if(page > 3){
                    p.sendMessage("[§bTheWall§r]"+ChatColor.RED+"错误,该页不存在");
                    return true;
                }
                Help h = helps.get(page);
                h.sendHelp(p);
                return true;
            }


            if(s1.equalsIgnoreCase("builder")){
                StringBuilder sb = new StringBuilder();
                int time = args.length-1;
                for(int i = 1;i <= time;i++){
                    if(i == time){
                        sb.append(args[i]);
                    }else {
                        sb.append(args[i]);
                        sb.append(",");
                    }
                }
                setMap.setBuilder(sb.toString());
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"成功设置建筑师");
                editor.setBuilder(true);
                editor.sendEditor(p);
                return true;
            }


            if(s1.equalsIgnoreCase("walltime")){
                try {
                    setMap.setWallTime(Integer.parseInt(args[1]));
                    p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"等待时间成功设置为"+args[1]+"秒");
                    editor.setWalltime(true);
                    editor.sendEditor(p);
                }catch (NumberFormatException e){
                    p.sendMessage("[§bTheWall§r]"+ChatColor.RED+"错误!输入的应为数字");
                }
                return true;
            }


            if(s1.equalsIgnoreCase("player")){
                try {
                    setMap.setPlayer(Integer.parseInt(args[1]));
                    p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"人数上限设置为:"+args[1]);
                    editor.setPlayerCount(true);
                    editor.sendEditor(p);
                }catch (NumberFormatException e){
                    p.sendMessage("[§bTheWall§r]"+ChatColor.RED+"错误!输入的应为数字");
                }
                return true;
            }


            //if(s1.equalsIgnoreCase("percentage")){
            //    setMap.setPercentage(Float.parseFloat(args[1]));
            //    p.sendMessage(args[1]);
            //    return true;
            //}


        }
        if(args.length == 3){
            if(args[0].equalsIgnoreCase("create")){
                if (setMap != null) {
                    setMap.save();
                    setMap = new GameMap(args[1]);
                } else {
                    setMap = new GameMap(args[1]);
                }
                setMap.setDisplayName(args[2]);
                p.sendMessage("[§bTheWall§r]"+ChatColor.GREEN+"创建成功!");
                if(editor != null) {
                    editor.sendEditor(p);
                    p.sendMessage("[§bTheWall§r]"+ChatColor.YELLOW+"请输入/wall wand 获取选区工具");
                }
                return true;
            }
        }


        sender.sendMessage("[§bTheWall§r]§c您输入的指令有误,请检查一下");
        return true;
    }
//    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String cl, String[] args) {
//        if (!(sender instanceof Player)) {
//            return false;
//        }
//        if (!sender.isOp()) {
//            return false;
//        }
//
//        if (args.length < 1) {
//            return false;
//        }
//
//        Player p = (Player) sender;
//
//        if (args[0].equals("wand")) {
//            if (se == null) {
//                se = new SelectEvent();
//                Main.get().getServer().getPluginManager().registerEvents(se, Main.get());
//            }
//            se.getWand(p);
//            p.sendMessage("wand done");
//            return true;
//
//        }
//
//        if (args[0].equals("start")) {
//            Main.getGc().getGame().switchState(2);
//            return true;
//        }
//
//        if (args[0].equals("create")) {
//            if (args.length < 3) {
//                p.sendMessage("Wrong length!");
//                return true;
//            }
//            if (setMap != null) {
//                setMap.save();
//                setMap = new GameMap(args[1]);
//
//            } else {
//                setMap = new GameMap(args[1]);
//            }
//            setMap.setDisplayName(args[2]);
//            p.sendMessage("created!");
//            return true;
//
//        }
//        if (args[0].equals("builder")) {
//            if (args.length < 2) {
//                p.sendMessage("Wrong length!");
//                return true;
//            }
//            setMap.setBuilder(args.toString().replace("builder", ""));
//            return true;
//        }
//
//        if (setMap == null) {
//            p.sendMessage("fucking empty");
//            return false;
//        }
//
//        if (args[0].equals("bound")) {
//            setMap.setBound(se.left.clone(), se.right.clone());
//
//            p.sendMessage(se.left.toString());
//            p.sendMessage(se.right.toString());
//            se.clear();
//            return true;
//        }
//
//        if (args[0].equals("wall")) {
//            setMap.addWall(new Wall(se.left.clone(), se.right.clone()));
//            p.sendMessage(se.left.toString());
//            p.sendMessage(se.right.toString());
//            se.clear();
//            p.sendMessage("wall : " + setMap.getWalls().size());
//            return true;
//        }
//
//        if (args[0].equals("spawn")) {
//            setMap.addSpawnPoints(p.getLocation());
//            p.sendMessage("count " + setMap.getSpanwPointsCount());
//            return true;
//        }
//
//        if (args[0].equals("player")) {
//            if (args.length < 2) {
//                p.sendMessage("Wrong length!");
//                return true;
//            }
//
//            setMap.setPlayer(Integer.parseInt(args[1]));
//            p.sendMessage(args[1]);
//            return true;
//        }
//
//        if (args[0].equals("walltime")) {
//            if (args.length < 2) {
//                p.sendMessage("Wrong length!");
//                return true;
//            }
//
//            setMap.setWallTime(Integer.parseInt(args[1]));
//            p.sendMessage(args[1]);
//            return true;
//        }
//
//        if (args[0].equals("percentage")) {
//            if (args.length < 2) {
//                p.sendMessage("Wrong length!");
//                return true;
//            }
//
//            setMap.setPercentage(Float.parseFloat(args[1]));
//            p.sendMessage(args[1]);
//            return true;
//        }
//
//
//        if (args[0].equals("lobby")) {
//            Main.getCon().lobby = p.getLocation();
//            p.sendMessage(Main.getCon().lobby.toString());
//            return true;
//        }
//
//
//        if (args[0].equals("save")) {
//            setMap.save();
//
//            File target = new File(Bukkit.getPluginManager().getPlugin("TheWalls").getDataFolder() + "/" + "maps" + "/", setMap.getName());
//            File source = new File(p.getWorld().getName());
//            Main.getWc().copyWorld(source, target);
//            Main.getCon().save();
//            p.sendMessage("saved");
//            return true;
//        }
//
//        return false;
//    }
}
