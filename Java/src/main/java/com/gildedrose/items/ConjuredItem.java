package com.gildedrose.items;

import static com.gildedrose.items.CommonCalculation.applyLimits;

public class ConjuredItem implements SpecializedItem {
    private Item item;

    public ConjuredItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateState() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        if (hasSellByDatePassed())
            item.quality -= 4;
        else item.quality -= 2;
        applyLimits(item);
    }

    private void updateSellIn() {
        item.sellIn--;
    }

    private boolean hasSellByDatePassed() {
        return item.sellIn <= 0;
    }
}
