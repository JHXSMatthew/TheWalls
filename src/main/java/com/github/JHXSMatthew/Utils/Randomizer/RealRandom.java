package com.github.JHXSMatthew.Utils.Randomizer;

import java.util.Random;

/**
 * Created by Matthew on 24/05/2016.
 */
public class RealRandom implements IRandomizer {

    Random r = new Random();
    @Override
    public boolean random(int max, int chance) {
        return r.nextInt(max) < chance;
    }
}
