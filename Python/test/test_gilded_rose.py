from src.gilded_rose import GildedRose
from src.item import Item
from src.item_factory import ItemType


def test_standard_items_sell_in_value_should_decrease_each_day():
    gilded_rose = create_gilded_rose_app_with_one_item("standard item", 0, 0)
    gilded_rose.update_quality()
    assert get_item_sell_in(gilded_rose) == -1


def test_brie_items_sell_in_value_should_decrease_each_day():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BRIE, 0, 0)
    gilded_rose.update_quality()
    assert get_item_sell_in(gilded_rose) == -1


def test_backstage_items_sell_in_value_should_decrease_each_day():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BACKSTAGE_PASSES, 0, 0)
    gilded_rose.update_quality()
    assert get_item_sell_in(gilded_rose) == -1


def test_brie_items_quality_should_increase_each_day():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BRIE, 1, 1)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 2


def test_brie_quality_should_not_go_above_fifty():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BRIE, 1, 49)
    gilded_rose.update_quality()
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 50


def test_backstage_item_decreases_quality_by_one_if_sell_by_day_more_than_eleven():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BACKSTAGE_PASSES, 12, 1)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 2


def test_backstage_item_decreases_quality_by_two_if_sell_by_day_is_more_than_six():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BACKSTAGE_PASSES, 10, 1)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 3


def test_backstage_item_decreases_quality_by_three_if_sell_by_day_is_more_than_zero():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BACKSTAGE_PASSES, 5, 1)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 4


def test_backstage_item_quality_drops_to_zero_if_sell_by_day_is_zero_or_less():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BACKSTAGE_PASSES, 0, 50)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 0


def test_backstage_item_quality_cannot_go_above_fifty_when_increasing():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.BACKSTAGE_PASSES, 5, 50)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 50


def test_standard_item_decreases_quality_by_one_if_sell_by_day_is_above_zero():
    gilded_rose = create_gilded_rose_app_with_one_item("foo", 2, 1)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 0


def test_standard_item_decreases_quality_by_two_once_sell_by_day_is_zero_or_less():
    gilded_rose = create_gilded_rose_app_with_one_item("foo", 0, 5)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 3


def test_standard_item_cannot_have_quality_below_zero():
    gilded_rose = create_gilded_rose_app_with_one_item("foo", 0, 0)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 0


def test_sulfuras_has_quality_eighty():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.SULFURAS, 1, 80)
    assert get_item_quality(gilded_rose) == 80


def test_sulfuras_has_quality_eighty_after_update():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.SULFURAS, 1, 80)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 80
    assert get_item_sell_in(gilded_rose) == 1


def test_conjured_item_decreases_quality_by_two_if_sell_by_day_is_above_zero():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.CONJURED_ITEM, 2, 5)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 3


def test_conjured_item_decreases_quality_by_four_once_sell_by_day_is_zero_or_less():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.CONJURED_ITEM, 0, 5)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 1


def test_conjured_item_cannot_have_quality_below_zero():
    gilded_rose = create_gilded_rose_app_with_one_item(ItemType.CONJURED_ITEM, 0, 0)
    gilded_rose.update_quality()
    assert get_item_quality(gilded_rose) == 0


def create_gilded_rose_app_with_one_item(item_name, item_sell_in, item_quality):
    items = [Item(item_name, item_sell_in, item_quality)]
    return GildedRose(items)


def get_item_sell_in(g_rose):
    return g_rose.items[0].sell_in


def get_item_quality(g_rose):
    return g_rose.items[0].quality
