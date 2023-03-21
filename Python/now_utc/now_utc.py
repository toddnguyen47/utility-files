"""Get now in UTC with the minutes set to previous hour"""

from datetime import datetime, timedelta


def _main():
    """Main function"""
    now = datetime.utcnow()
    start = datetime(now.year, now.month, now.day, now.hour)
    end = start + timedelta(minutes=30)

    print(f"now: {_get_timeformat(now)}")
    print(f"start: {_get_timeformat(start)}")
    print(f"end: {_get_timeformat(end)}")


def _get_timeformat(input_time: datetime) -> str:
    """Get time format"""
    return input_time.isoformat(timespec="milliseconds") + "Z"


if __name__ == "__main__":
    _main()
