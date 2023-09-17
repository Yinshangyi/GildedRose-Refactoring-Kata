package com.gildedrose.items;

public class CommonCalculation {

    private static final int LOWEST_QUALITY_VALUE = 0;
    private static final int HIGHEST_QUALITY_VALUE = 50;

    public static void applyLimits(Item item) {
        if (item.quality < LOWEST_QUALITY_VALUE) item.quality = LOWEST_QUALITY_VALUE;
        else if (item.quality > HIGHEST_QUALITY_VALUE) item.quality = HIGHEST_QUALITY_VALUE;
    }


}
