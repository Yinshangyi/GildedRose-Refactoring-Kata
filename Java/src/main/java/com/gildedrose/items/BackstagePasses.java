package com.gildedrose.items;

import static com.gildedrose.items.CommonCalculation.applyLimits;

public class BackstagePasses implements SpecializedItem {
    private Item item;

    public BackstagePasses(Item item) {
        this.item = item;
    }

    @Override
    public void updateState() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        if (item.sellIn > 10)
            item.quality += 1;
        else if (isSellInBetweenRange(item.sellIn, 6, 10))
            item.quality += 2;
        else if (isSellInBetweenRange(item.sellIn, 1, 5))
            item.quality += 3;
        else item.quality = 0;
        applyLimits(item);
    }

    private void updateSellIn() {
        item.sellIn--;
    }

    private boolean isSellInBetweenRange(int sellIn, int minValue, int maxValue) {
        return sellIn >= minValue && sellIn <= maxValue;
    }
}
