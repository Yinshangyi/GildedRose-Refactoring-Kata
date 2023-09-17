from enum import Enum

from src.item import Item
from src.models.aged_brie import AgedBrie
from src.models.backstage_pass import BackstagePass
from src.models.conjured_item import ConjuredItem
from src.models.specialized_item import SpecializedItem
from src.models.standard_item import StandardItem
from src.models.sulfuras import Sulfuras


class ItemType(Enum):
    BRIE = "Aged Brie"
    BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
    SULFURAS = "Sulfuras, Hand of Ragnaros"
    CONJURED_ITEM = "Conjured"


def get_specific_item_from_name(item: Item) -> SpecializedItem:
    match item.name:
        case ItemType.BRIE:
            return AgedBrie(item)
        case ItemType.BACKSTAGE_PASSES:
            return BackstagePass(item)
        case ItemType.SULFURAS:
            return Sulfuras(item)
        case ItemType.CONJURED_ITEM:
            return ConjuredItem(item)
        case _:
            return StandardItem(item)
