from src.item_factory import get_specific_item_from_name


class GildedRose(object):

    def __init__(self, items):
        self.items = items

    def update_quality(self):
        for item in self.items:
            specialized_item = get_specific_item_from_name(item)
            specialized_item.update_state()
