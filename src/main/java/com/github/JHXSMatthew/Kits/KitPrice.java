package com.github.JHXSMatthew.Kits;

import java.util.HashMap;

/**
 * Created by Matthew on 2016/5/21.
 */
public class KitPrice {
    public static HashMap<KitType,int[]> map = null;
    public static int level = 11;

    public static int query(KitType type, int level){
        if(map == null)
            register();

        try {
            return map.get(type)[level];
        }catch(Exception e){
            return -1;
        }
    }

    public static int getMax(KitType kitType){
        return level;
        /*
        if(map == null)
            register();
        return map.get(kitType).length;
        */
    }

    public static void register(){
        map =  new HashMap<KitType,int[]>();
        /*
        add(KitType.bloodSeeker,1000,3000,5000);
        add(KitType.archer,1000,2800,5000);
        add(KitType.enderman,3000,5000,8000);

        add(KitType.proRedstone,1000,3000);
        add(KitType.lazeman,1500,2500);
        add(KitType.alchemist,1000,3000,5000);
        add(KitType.assassin,1000,2400);
        add(KitType.oldDriver,1000);
        add(KitType.berserke,2000);
        add(KitType.tntKing,1000);
        add(KitType.appleFans,1000);
        add(KitType.philosopher,3000);
        */
        add(KitType.cook,600,1000);

    }

    public static void add(KitType type,int ...value){
        map.put(type,value);
    }

}
