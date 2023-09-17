from src.item import Item
from src.models.common_utils import has_sell_by_date_passed, apply_limits
from src.models.specialized_item import SpecializedItem


class StandardItem(SpecializedItem):

    def __init__(self, item: Item):
        self.item = item

    def update_state(self):
        self.update_quality()
        self.update_sell_in()

    def update_quality(self):
        if has_sell_by_date_passed(self.item.sell_in):
            self.item.quality -= 2
        else:
            self.item.quality -= 1
        self.item.quality = apply_limits(self.item.quality)

    def update_sell_in(self):
        self.item.sell_in -= 1
