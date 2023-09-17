from abc import ABC, abstractmethod


class SpecializedItem(ABC):

    @abstractmethod
    def update_state(self):
        pass
