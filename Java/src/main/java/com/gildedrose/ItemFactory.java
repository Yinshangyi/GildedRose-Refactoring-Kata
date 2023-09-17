package com.gildedrose;

import com.gildedrose.items.*;

import java.util.HashMap;
import java.util.Map;

public class ItemFactory {
    public final static String BRIE = "Aged Brie";
    public final static String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public final static String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public final static String CONJURED_ITEM = "Conjured";
    private final static Map<String, SpecializedItem> itemsMap = new HashMap<>();

    public ItemFactory(Item item) {
        itemsMap.put(BRIE, new AgedBrie(item));
        itemsMap.put(BRIE, new AgedBrie(item));
        itemsMap.put(BACKSTAGE_PASSES, new BackstagePasses(item));
        itemsMap.put(SULFURAS, new Sulfuras(item));
        itemsMap.put(CONJURED_ITEM, new ConjuredItem(item));
    }

    public SpecializedItem getSpecificItemFromName(Item item) {
        if (isStandardItem(item))
            return new StandardItem(item);
        return itemsMap.get(item.name);
    }

    private boolean isStandardItem(Item item) {
        return !itemsMap.keySet().contains(item.name);
    }
}
