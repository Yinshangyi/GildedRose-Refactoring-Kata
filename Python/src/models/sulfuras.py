from src.item import Item
from src.models.specialized_item import SpecializedItem


class Sulfuras(SpecializedItem):

    def __init__(self, item: Item):
        self.item = item

    def update_state(self):
        pass
