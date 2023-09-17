from src.item import Item
from src.models.common_utils import apply_limits
from src.models.specialized_item import SpecializedItem


class BackstagePass(SpecializedItem):

    def __init__(self, item: Item):
        self.item = item

    def update_state(self):
        self.update_quality()
        self.update_sell_in()

    def update_quality(self):
        if self.item.sell_in > 10:
            self.item.quality += 1
        elif self.is_sell_in_between_range(6, 10):
            self.item.quality += 2
        elif self.is_sell_in_between_range(1, 5):
            self.item.quality += 3
        else:
            self.item.quality = 0
        self.item.quality = apply_limits(self.item.quality)

    def update_sell_in(self):
        self.item.sell_in -= 1

    def is_sell_in_between_range(self, min_value, max_value):
        return min_value <= self.item.sell_in <= max_value
