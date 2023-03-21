"""Get now in UTC with the minutes set to previous hour"""

from datetime import datetime, timedelta

_FILL_SPACES = 10


def _main():
    """Main function"""
    now = datetime.utcnow()
    minutes = _get_minute(now)
    start = datetime(now.year, now.month, now.day, now.hour, minutes)
    end = start + timedelta(minutes=30)

    print(f'{"now:": <{_FILL_SPACES}}{_get_timeformat(now)}')
    print(f'{"start:": <{_FILL_SPACES}}{_get_timeformat(start)}')
    print(f'{"end:" : <{_FILL_SPACES}}{_get_timeformat(end)}')


def _get_timeformat(input_time: datetime) -> str:
    """Get time format"""
    return input_time.isoformat(timespec="milliseconds") + "Z"


def _get_minute(input_time: datetime) -> int:
    """Get minute and round down to nearest half hour"""
    minutes = input_time.minute
    if minutes > 30:
        minutes = 30
    else:
        minutes = 0
    return minutes


if __name__ == "__main__":
    _main()
