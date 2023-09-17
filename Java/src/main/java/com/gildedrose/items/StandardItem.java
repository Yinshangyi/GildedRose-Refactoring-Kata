package com.gildedrose.items;

import static com.gildedrose.items.CommonCalculation.*;

public class StandardItem implements SpecializedItem {
    private Item item;

    public StandardItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateState() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        if (hasSellByDatePassed())
            item.quality -= 2;
        else item.quality -= 1;
        applyLimits(item);
    }

    private void updateSellIn() {
        item.sellIn--;
    }

    private boolean hasSellByDatePassed() {
        return item.sellIn <= 0;
    }
}
