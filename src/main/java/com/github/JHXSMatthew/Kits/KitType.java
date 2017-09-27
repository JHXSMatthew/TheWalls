package com.github.JHXSMatthew.Kits;

import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Kits.allKits.*;
import com.github.JHXSMatthew.Utils.ItemFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Matthew on 2016/5/12.
 */
public enum KitType {


    cook(ItemFactory.create(Material.PORK,(byte)0,ChatColor.WHITE + "厨师","衣食无忧,天下太平."),"cook",Cook.class, SkillType.Cook_1,SkillType.Cook_4,SkillType.Cook_6,SkillType.Cook_8),
    archer(ItemFactory.create(Material.BOW,(byte)0,ChatColor.WHITE + "弓箭手","以睹余物，皆丘山也."),"archer",Archer.class,SkillType.Archer_1,SkillType.Archer_4,SkillType.Archer_6,SkillType.Archer_8),
    assassin(ItemFactory.create(Material.DIAMOND_SWORD,(byte)0,ChatColor.WHITE + "刺客","十步杀一人,千里不留情."),"assassin",Assassin.class,SkillType.Assassin_1,SkillType.Assassin_4,SkillType.Assassin_6,SkillType.Assassin_8),
    enderman(ItemFactory.create(Material.ENDER_PORTAL_FRAME,(byte)0,ChatColor.WHITE + "末影使者","掌控世间粒子的运动规则"),"enderman",Enderman.class,SkillType.Enderman_1,SkillType.Enderman_4,SkillType.Enderman_6,SkillType.Enderman_8),
    bloodSeeker(ItemFactory.create(Material.RED_ROSE,(byte)0, ChatColor.WHITE + "嗜血者","遇人杀人，遇佛弑佛."),"bloodSeeker",BloodSeeker.class,SkillType.BloodSeeker_1,SkillType.BloodSeeker_4,SkillType.BloodSeeker_6,SkillType.BloodSeeker_8),
    fish(ItemFactory.create(Material.RAW_FISH,(byte)0, ChatColor.WHITE + "咸鱼","咸鱼翻身,鲤鱼打滚."),"fish",Fish.class,SkillType.Fish_1,SkillType.Fish_4,SkillType.Fish_6,SkillType.Fish_8),
    knight(ItemFactory.create(Material.SADDLE,(byte)0, ChatColor.WHITE + "骑士","马到之处,寸草不生."),"knight",Fish.class,SkillType.Knight_1,SkillType.Knight_4,SkillType.Knight_6,SkillType.Knight_8),
    orc(ItemFactory.create(Material.DIAMOND_CHESTPLATE,(byte)0, ChatColor.WHITE + "兽人","皮糙肉厚,行动笨拙."),"orc",Fish.class,SkillType.Orc_1,SkillType.Orc_4,SkillType.Orc_6,SkillType.Orc_8),
    Paladin(ItemFactory.create(Material.IRON_SWORD,(byte)0, ChatColor.WHITE + "圣骑士","威武庄重,神圣青睐."),"paladin",Fish.class,SkillType.Paladin_1,SkillType.Paladin_4,SkillType.Paladin_6,SkillType.Paladin_8);


    //,

    //proRedstone(ItemFactory.create(Material.REDSTONE,(byte)0,ChatColor.WHITE + "红石大师","开局获得红石相关的道具"),"proRedstone",ProRedstone.class,"获得15个红石,12个中继器和%( level - 1 ) * 10%个粘性活塞"),
    //lazeman(ItemFactory.create(Material.IRON_INGOT,(byte)0,ChatColor.WHITE + "懒人","开局获得一定量的铁锭"),"lazeman",Lazeman.class,"获得%2 * level%块铁"),
    //alchemist(ItemFactory.create(Material.POTION,(byte)0,ChatColor.WHITE + "炼药师","开局获得一定量的伤害药水"),"alchemist",Alchemist.class,"获得%level * 3%瓶伤害药水"),

    //oldDriver(ItemFactory.create(Material.BOWL,(byte)0,ChatColor.WHITE + "老司机","开局获得速度I,持续一段时间."),"oldDriver",OldDriver.class,"持续时间为%level%分钟"),
    //berserke(ItemFactory.create(Material.GOLD_AXE,(byte)0,ChatColor.WHITE + "狂战士","开局获得额外生命值."),"berserke",Berserke.class,"增加额外%4 * level%生命值"),
    //tntKing(ItemFactory.create(Material.TNT,(byte)0,ChatColor.WHITE + "熊孩子","开局获得一定数量的TNT."),"tntKing",TntKing.class,"获得4个TNT"),
    //philosopher(ItemFactory.create(Material.ENCHANTMENT_TABLE,(byte)0,ChatColor.WHITE + "贤者","开局获得附魔台."),"philosopher",Philosopher.class,"获得1个附魔台"),
    //appleFans(ItemFactory.create(Material.GOLDEN_APPLE,(byte)0,ChatColor.WHITE + "果粉","开局获得一定数量的苹果."),"appleFans",AppleFans.class,"获得4个苹果");


    private Class<? extends KitBasic> clazz;
    private String DBName;
    private ItemStack item;
    private SkillType[] skills;

    KitType(ItemStack item, String DBName, Class<? extends KitBasic> clazz , SkillType ...type){
        this.item = item;
        this.DBName = DBName;
        this.clazz = clazz;
        this.skills = type;
    }

    public static KitType getType(String dbName){
        for(KitType k : KitType.values()){
            if(k.getDBName().equals(dbName)){
                return k;
            }
        }
        return null;
    }

    public String getDisplayName(){
        return ChatColor.stripColor(item.getItemMeta().getDisplayName());
    }

    public String getDisplayNameWithColor(){
        return item.getItemMeta().getDisplayName();
    }

    public List<String> getLore(){
        return item.getItemMeta().getLore();
    }

    public Material getMaterial(){
        return item.getType();
    }

    public ItemStack getItem(){
        return item.clone();
    }

    public SkillType[] getSkillTypes(){
        return skills;
    }

    public String getDBName(){
        return DBName;
    }

    public KitBasic getKit(Player owner,int level){
        KitBasic basic = null;
        try{
            basic = clazz.getDeclaredConstructor(UUID.class,int.class).newInstance(owner.getUniqueId(),level);
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return basic;
    }

    public List<String> getDescription(int level){
        ArrayList<String> string = new ArrayList<>();
        for(SkillType basic : skills){
            if(basic.getUnlockLevel() > level){
                continue;
            }
            basic.getDescription(level,string);
        }
        return string;
    }



}
