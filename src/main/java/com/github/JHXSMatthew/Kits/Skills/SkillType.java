package com.github.JHXSMatthew.Kits.Skills;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.archer.SK_Archer_1;
import com.github.JHXSMatthew.Kits.Skills.archer.SK_Archer_4;
import com.github.JHXSMatthew.Kits.Skills.archer.SK_Archer_6;
import com.github.JHXSMatthew.Kits.Skills.archer.SK_Archer_8;
import com.github.JHXSMatthew.Kits.Skills.assassin.SK_Assassin_1;
import com.github.JHXSMatthew.Kits.Skills.assassin.SK_Assassin_4;
import com.github.JHXSMatthew.Kits.Skills.assassin.SK_Assassin_6;
import com.github.JHXSMatthew.Kits.Skills.assassin.SK_Assassin_8;
import com.github.JHXSMatthew.Kits.Skills.bloodSeeker.SK_BloodSeeker_1;
import com.github.JHXSMatthew.Kits.Skills.bloodSeeker.SK_BloodSeeker_4;
import com.github.JHXSMatthew.Kits.Skills.bloodSeeker.SK_BloodSeeker_6;
import com.github.JHXSMatthew.Kits.Skills.bloodSeeker.SK_BloodSeeker_8;
import com.github.JHXSMatthew.Kits.Skills.cook.SK_Cook_1;
import com.github.JHXSMatthew.Kits.Skills.cook.SK_Cook_4;
import com.github.JHXSMatthew.Kits.Skills.cook.SK_Cook_6;
import com.github.JHXSMatthew.Kits.Skills.cook.SK_Cook_8;
import com.github.JHXSMatthew.Kits.Skills.enderMan.SK_Enderman_1;
import com.github.JHXSMatthew.Kits.Skills.enderMan.SK_Enderman_4;
import com.github.JHXSMatthew.Kits.Skills.enderMan.SK_Enderman_6;
import com.github.JHXSMatthew.Kits.Skills.enderMan.SK_Enderman_8;
import com.github.JHXSMatthew.Kits.Skills.fish.SK_Fish_1;
import com.github.JHXSMatthew.Kits.Skills.fish.SK_Fish_4;
import com.github.JHXSMatthew.Kits.Skills.fish.SK_Fish_6;
import com.github.JHXSMatthew.Kits.Skills.fish.SK_Fish_8;
import com.github.JHXSMatthew.Kits.Skills.knight.SK_Knight_1;
import com.github.JHXSMatthew.Kits.Skills.knight.SK_Knight_4;
import com.github.JHXSMatthew.Kits.Skills.knight.SK_Knight_6;
import com.github.JHXSMatthew.Kits.Skills.knight.SK_Knight_8;
import com.github.JHXSMatthew.Kits.Skills.orc.SK_Orc_1;
import com.github.JHXSMatthew.Kits.Skills.orc.SK_Orc_4;
import com.github.JHXSMatthew.Kits.Skills.orc.SK_Orc_6;
import com.github.JHXSMatthew.Kits.Skills.orc.SK_Orc_8;
import com.github.JHXSMatthew.Utils.StringUtils;
import org.bukkit.ChatColor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Matthew on 23/05/2016.
 */
public enum SkillType {

    // ------------------------- cook --------------------------------
    Cook_1(1, "小厨", "开局获得@level * 3@块面包和@level@个蛋糕.", SK_Cook_1.class),
    Cook_4(4, "厨艺精通", "制作食物时有30%的概率获取@level@倍的食物.", SK_Cook_4.class),
    Cook_6(6, "神农", "使用锄右击作物可以直接催熟,消耗@100 / level@%的耐久度.", SK_Cook_6.class),
    Cook_8(8, "食神", "在食用食物时有@10 * level@%概率恢复周围@level@数友军的等量饱食度", SK_Cook_8.class),

    // ------------------------- archer --------------------------------
    Archer_1(1, "弓不离手", "开局获取一把单弓和@level * 5@个弓箭.", SK_Archer_1.class),
    Archer_4(4, "爆破射手", "开局获得@level + 0@个TNT,当背包内含有TNT时,弓箭附带爆炸效果并消耗TNT.", SK_Archer_4.class),
    Archer_6(6, "灵魂一击", "使用远程武器造成的伤害将提升@10 * level@%.", SK_Archer_6.class),
    Archer_8(8, "涂毒", "弓箭有@5 * level@%的概率附加中毒效果", SK_Archer_8.class),

    // -------------------------  assassin --------------------------------
    Assassin_1(1, "急速", "击杀敌人后速度II 状态持续@level@秒.", SK_Assassin_1.class),
    Assassin_4(4, "背刺", "在身后攻击敌人时有@10 * level@%的概率使其进入反胃状态..", SK_Assassin_4.class),
    Assassin_6(6, "致命一击", "发动致命一击时会额外造成@8 * level@%的伤害.", SK_Assassin_6.class),
    Assassin_8(8, "盗贼天赋", "右键<隐身斗篷>后进入@level@秒隐身效果，造成伤害隐身消失", SK_Assassin_8.class),

    // -------------------------  Enderman --------------------------------
    Enderman_1(1, "粒子破碎", "获得物品右击记录当前位置在@1 + level * 2@秒内再次右键可回到记录位置,在回到该位置后2秒内免疫箭的伤害.", SK_Enderman_1.class),
    Enderman_4(4, "粒子护甲", "如果你没有穿胸甲，且在7秒内没有受到伤害，那么你会免疫接下来的@level@次伤害.", SK_Enderman_4.class),
    Enderman_6(6, "粒子武器", "攻击时如果你使用铁剑有@5 + 3 * level@%概率使敌人定身.", SK_Enderman_6.class),
    Enderman_8(8, "粒子感知", "获得指南针，右击后将指向随机一个敌人的位置.", SK_Enderman_8.class),

    // -------------------------  bloodSeeker --------------------------------
    BloodSeeker_1(1, "嗜血狂魔", "杀死任意实体时恢复@level * 2@点生命值.", SK_BloodSeeker_1.class),
    BloodSeeker_4(4, "吸血之斧", "使用石斧武器攻击时可以恢复@5 * level@%的流失生命值,但不会造成伤害.", SK_BloodSeeker_4.class),
    BloodSeeker_6(6, "撕裂伤口", "攻击时有@10 * level@%的概率附加流血效果 持续1秒.", SK_BloodSeeker_6.class),
    BloodSeeker_8(8, "秒杀", "敌人生命值在@2 + level@以下时，造成任意伤害将直接秒杀敌人.", SK_BloodSeeker_8.class),

    // -------------------------  fish --------------------------------
    Fish_1(1, "咸鱼王", "出生获得10个用来吃的生鱼.", SK_Fish_1.class),
    Fish_4(4, "咸鱼突刺", "使用任意生鱼攻击敌人时，有@10 + level@%几率造成击退1效果.", SK_Fish_4.class),
    Fish_6(6, "波纹疾走", "右键任意生鱼获得@1 * level@秒速度1BUFF.冷却100秒.", SK_Fish_6.class),
    Fish_8(8, "咸鱼打挺", "在生命值低于4时获得@level + 0@秒生命恢复2效果..", SK_Fish_8.class),

    // -------------------------  knight --------------------------------
    Knight_1(1, "随从召唤", "右键马鞍召唤一匹具有@2 * level + 20@生命值的战马,马被击杀或主动下马它将会消失,冷却300秒.", SK_Knight_1.class),
    Knight_4(4, "防御", "在马上作战时,受到伤害降低@5 * level@%.", SK_Knight_4.class),
    Knight_6(6, "进攻", "在马上作战时,造成额外@5 * level@%攻击力.", SK_Knight_6.class),
    Knight_8(8, "坚不可摧", "可以免疫一次致死伤害 并获得@0 + level@秒无敌,一局游戏只可触发一次.", SK_Knight_8.class),

    // -------------------------  orc --------------------------------
    Orc_1(1, "兽人天赋", "出生血量为@40 + 3 * level@点.", SK_Orc_1.class),
    Orc_4(4, "生存至上", "出生获得1个金苹果,@level + 0@个苹果.", SK_Orc_4.class),
    Orc_6(6, "愤怒稿击", "用稿子攻击时造成额外@10 * level@%的伤害.", SK_Orc_6.class),
    Orc_8(8, "兽人皮肤", "被近战攻击时反弹@level + 5@%伤害.", SK_Orc_8.class),

    // -------------------------  Paladin --------------------------------
    Paladin_1(1, "圣愈光剑", "升级职业会提升圣治光剑的效果.", SK_Orc_1.class),
    Paladin_4(4, "治愈", "使用圣愈光剑左键友军恢复@3 + level@点生命值.学习此技能后,你只能使用圣愈光剑造成伤害.", SK_Orc_4.class),
    Paladin_6(6, "神圣打击", "使用圣愈光剑右键召唤闪电劈向目标地点,造成@level + 1@点范围伤害.", SK_Orc_6.class),
    Paladin_8(8, "生命源泉", "自身死亡时,为所有存活队友增加生命恢复效果.", SK_Orc_8.class);


    public static int MAX_STEP = 3;

    private int unlockLevel;
    private String des;
    private Class<? extends SkillBasic> clazz;
    private String name;

    SkillType(int unlock_level, String name, String des, Class<? extends SkillBasic> clazz) {
        this.des = name + ": " + des;
        this.unlockLevel = unlock_level;
        this.clazz = clazz;
        this.name = name;

    }

    public String getSkillName() {
        return name;
    }

    public int getUnlockLevel() {
        return unlockLevel;
    }

    public String getDescription() {
        return des;
    }
   /*
    public String getDescription(int level){
        return ChatColor.GREEN + StringUtils.calSkillPlaceHolders(des,level - unlockLevel >= MAX_STEP ? MAX_STEP : (level - unlockLevel+1));
    }
    */

    public String getDescriptionOneline(int level) {
        return ChatColor.YELLOW + getSkillName() + ": " + ChatColor.GOLD + StringUtils.calSkillPlaceHolders(des, level - unlockLevel >= MAX_STEP ? MAX_STEP : (level - unlockLevel + 1));
    }

    public void getDescription(int level, List<String> list) {
        String whole = StringUtils.calSkillPlaceHolders(des, level - unlockLevel >= MAX_STEP ? MAX_STEP : (level - unlockLevel + 1));
        if (whole.length() < 23) {
            list.add(ChatColor.GOLD + whole);
        } else {
            boolean isFirst = true;
            int count = 0;
            int last = 0;
            boolean shouldSet = false;
            while (count < whole.length()) {
                if ((count % 23 == 0 && count != 0) || count == whole.length() - 1 || shouldSet) {
                    if (whole.charAt(count) == ChatColor.COLOR_CHAR || whole.charAt(count - 1) == ChatColor.COLOR_CHAR) {
                        shouldSet = true;

                    } else {
                        shouldSet = false;
                        if (isFirst) {
                            list.add(ChatColor.GOLD + whole.substring(last, count));
                            last = count;
                            isFirst = false;
                        } else {
                            list.add(ChatColor.GOLD + " " + whole.substring(last, count));
                            last = count;
                        }
                    }
                }
                count++;
            }
        }
    }

    public SkillBasic getSkill(KitBasic owner, int level) {
        SkillBasic basic = null;
        try {
            basic = clazz.getDeclaredConstructor(KitBasic.class, int.class).newInstance(owner, level - unlockLevel >= MAX_STEP ? MAX_STEP : (level - unlockLevel + 1));
        } catch (NoSuchMethodException e) {
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
}
