package com.gildedrose.items;

import static com.gildedrose.items.CommonCalculation.applyLimits;

public class AgedBrie implements SpecializedItem {
    private Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void updateState() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        item.quality++;
        applyLimits(item);
    }

    private void updateSellIn() {
        item.sellIn--;
    }
}
