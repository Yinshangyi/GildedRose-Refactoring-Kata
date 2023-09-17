package com.gildedrose;

import com.gildedrose.items.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void standard_items_sell_in_value_should_decrease_each_day() {
        var gildedRose = createGildedRoseAppWithOneItem("standard item", 0, 0);
        gildedRose.updateQuality();
        Assertions.assertEquals(-1, getItemSellIn(gildedRose));
    }

    @Test
    void brie_items_sell_in_value_should_decrease_each_day() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BRIE, 0, 0);
        gildedRose.updateQuality();
        Assertions.assertEquals(-1, getItemSellIn(gildedRose));
    }

    @Test
    void backstage_items_sell_in_value_should_decrease_each_day() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BACKSTAGE_PASSES, 0, 0);
        gildedRose.updateQuality();
        Assertions.assertEquals(-1, getItemSellIn(gildedRose));
    }

    @Test
    void brie_items_quality_should_increase_each_day() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BRIE, 1, 1);
        gildedRose.updateQuality();
        Assertions.assertEquals(2, getItemQuality(gildedRose));
    }

    @Test
    void brie_quality_should_not_go_above_fifty() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BRIE, 1, 49);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Assertions.assertEquals(50, getItemQuality(gildedRose));
    }

    @Test
    public void backstage_item_decreases_quality_by_one_if_sell_by_day_more_than_eleven() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BACKSTAGE_PASSES, 12, 1);
        gildedRose.updateQuality();
        assertEquals(2, getItemQuality(gildedRose));
    }

    @Test
    public void backstage_item_decreases_quality_by_two_if_sell_by_day_is_more_than_six() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BACKSTAGE_PASSES, 10, 1);
        gildedRose.updateQuality();
        assertEquals(3, getItemQuality(gildedRose));
    }

    @Test
    public void backstage_item_decreases_quality_by_three_if_sell_by_day_is_more_than_zero() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BACKSTAGE_PASSES, 5, 1);
        gildedRose.updateQuality();
        assertEquals(4, getItemQuality(gildedRose));
    }

    @Test
    public void backstage_item_quality_drops_to_zero_if_sell_by_day_is_zero_or_less() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BACKSTAGE_PASSES, 0, 50);
        gildedRose.updateQuality();
        assertEquals(0, getItemQuality(gildedRose));
    }

    @Test
    public void backstage_item_quality_cannot_go_above_fifty_when_increasing() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.BACKSTAGE_PASSES, 5, 50);
        gildedRose.updateQuality();
        assertEquals(50, getItemQuality(gildedRose));
    }

    @Test
    public void standard_item_decreases_quality_by_one_if_sell_by_day_is_above_zero() {
        var gildedRose = createGildedRoseAppWithOneItem("foo", 2, 1);
        gildedRose.updateQuality();
        assertEquals(0, getItemQuality(gildedRose));
    }

    @Test
    public void standard_item_decreases_quality_by_two_once_sell_by_day_is_zero_or_less() {
        var gildedRose = createGildedRoseAppWithOneItem("foo", 0, 5);
        gildedRose.updateQuality();
        assertEquals(3, getItemQuality(gildedRose));
    }

    @Test
    public void standard_item_cannot_have_quality_below_zero() {
        var gildedRose = createGildedRoseAppWithOneItem("foo", 0, 0);
        gildedRose.updateQuality();
        assertEquals(0, getItemQuality(gildedRose));
    }

    @Test
    public void sulfurasHasQualityEighty() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.SULFURAS, 1, 80);
        assertEquals(80, getItemQuality(gildedRose));
    }

    @Test
    public void sulfuras_has_quality_eighty() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.SULFURAS, 1, 80);
        gildedRose.updateQuality();
        assertEquals(80, getItemQuality(gildedRose));
        assertEquals(1, getItemSellIn(gildedRose));
    }

    @Test
    public void conjured_item_decreases_quality_by_two_if_sell_by_day_is_above_zero() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.CONJURED_ITEM, 2, 5);
        gildedRose.updateQuality();
        assertEquals(3, getItemQuality(gildedRose));
    }

    @Test
    public void conjured_item_decreases_quality_by_four_once_sell_by_day_is_zero_or_less() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.CONJURED_ITEM, 0, 5);
        gildedRose.updateQuality();
        assertEquals(1, getItemQuality(gildedRose));
    }

    @Test
    public void conjured_item_cannot_have_quality_below_zero() {
        var gildedRose = createGildedRoseAppWithOneItem(ItemFactory.CONJURED_ITEM, 0, 0);
        gildedRose.updateQuality();
        assertEquals(0, getItemQuality(gildedRose));
    }

    private GildedRose createGildedRoseAppWithOneItem(String itemName, int itemSellIn, int itemQuality) {
        var items = new Item[]{new Item(itemName, itemSellIn, itemQuality)};
        return new GildedRose(items);
    }

    private int getItemSellIn(GildedRose gRose) {
        return gRose.items[0].sellIn;
    }

    private int getItemQuality(GildedRose gRose) {
        return gRose.items[0].quality;
    }


}
