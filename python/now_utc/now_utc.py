"""Get now in UTC with the minutes set to previous hour"""

from datetime import datetime, timedelta


def _main():
    """Main function"""
    now = datetime.utcnow()
    minutes = _get_minute(now)
    start = datetime(now.year, now.month, now.day, now.hour, minutes)
    end = start + timedelta(minutes=30)

    _print_results("now", now)

    print("local time")
    print(datetime.now().astimezone().replace(microsecond=0).isoformat())

    _print_results("start", start)
    _print_results("end", end)


def _print_results(label: str, input_time: datetime):
    """print results"""
    print(label)
    print(_get_timeformat_millis(input_time))
    seconds = _get_timeformat_seconds(input_time)
    seconds_files = seconds.replace(":", "-")
    print(seconds)
    print(seconds_files)


def _get_timeformat_seconds(input_time: datetime) -> str:
    """Get time format"""
    return input_time.isoformat(timespec="seconds") + "Z"


def _get_timeformat_millis(input_time: datetime) -> str:
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
