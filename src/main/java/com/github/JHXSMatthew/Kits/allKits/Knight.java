package com.github.JHXSMatthew.Kits.allKits;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;

import java.util.UUID;

/**
 * Created by Matthew on 15/07/2016.
 */
public class Knight extends KitBasic {
    public Knight(UUID uuid, int level) {
        super(uuid, level, KitType.knight);
    }
}
