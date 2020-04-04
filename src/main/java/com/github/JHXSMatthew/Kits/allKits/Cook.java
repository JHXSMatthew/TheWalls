package com.github.JHXSMatthew.Kits.allKits;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;
import org.bukkit.event.Listener;

import java.util.UUID;

/**
 * Created by Matthew on 2016/5/21.
 */
public class Cook extends KitBasic implements Listener {

    public Cook(UUID uuid, int level) {
        super(uuid, level, KitType.cook);
    }

}
