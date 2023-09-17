LOWEST_QUALITY_VALUE = 0
HIGHEST_QUALITY_VALUE = 50


def has_sell_by_date_passed(sell_in: int) -> bool:
    return sell_in <= 0


def apply_limits(quality: int) -> int:
    if quality < LOWEST_QUALITY_VALUE:
        quality = LOWEST_QUALITY_VALUE
    elif quality > HIGHEST_QUALITY_VALUE:
        quality = HIGHEST_QUALITY_VALUE
    return quality
