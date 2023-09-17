package com.gildedrose;

import com.gildedrose.items.Item;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (var item : items) {
            var specializedItem = new ItemFactory(item).getSpecificItemFromName(item);
            specializedItem.updateState();
        }
    }
}
