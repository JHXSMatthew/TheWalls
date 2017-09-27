package com.github.JHXSMatthew.Utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Matthew on 27/05/2016.
 */
public class StringUtils {

    public static String calSkillPlaceHolders(String string, int level){
        Pattern pattern = Pattern.compile("(@.+?@)");
        string = string.replaceAll("level",String.valueOf(level));
        Matcher matcher = pattern.matcher(string);
        try {
            while (matcher.find()) {
                String ori = matcher.group();
                int count = MathUtils.evaluate(ori.replaceAll("@", ""));

                string = string.replace(ori, ChatColor.GREEN + String.valueOf(count) + ChatColor.GOLD);
                //System.out.println("ori=" + ori + "count=" + count + "str=" + string);
            }
            string = string.replace(ChatColor.GOLD + "%" , "%" + ChatColor.GOLD);
        }catch(Exception e){
            e.printStackTrace();
        }
        return ChatColor.GOLD + string ;
    }
}
