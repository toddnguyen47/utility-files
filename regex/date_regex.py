"""Generate Date Regex for Expenses"""

# Old regex:
# ^
# (((0[13578])|(1[02]))
# \/(0[1-9]|[12]\d|3[01])|(0[469]|1[1])
# \/(0[1-9]|[12]\d|3[0])|02
# \/(0[1-9]|1\d|2\d))\/(\d{4})
# $


def _main():
    """main function"""
    str_list = []
    str_list.append("^")

    month_regex = _generate_month_regex()
    str_list.append(month_regex)
    str_list.append(r"\/")

    date_regex = _generate_date_regex()
    str_list.append(date_regex)
    str_list.append(r"\/")

    year_regex = _generate_year_regex()
    str_list.append(year_regex)

    str_list.append("$")

    final_str = "".join(str_list)
    print(final_str)


def _generate_month_regex() -> str:
    """generate month regex"""
    # First option: starts with 1-9 with optional 0
    jan_to_sep = "0*[1-9]"
    # 10-12 requires 1 followed by either 0, 1, or 2
    oct_to_dec = "1[0-2]"
    combined = f"({jan_to_sep}|{oct_to_dec})"
    return combined


def _generate_date_regex() -> str:
    """generate date regex"""
    # First: 1 - 9 with optional 0
    one_to_nine = "0*[1-9]"
    # Second, 1 or 2 followed by 0-9
    tens_and_twenties = "(1|2)[0-9]"
    # Third, 3 followed by 0-1
    thirties = "3[0-1]"

    combined = f"({one_to_nine}|{tens_and_twenties}|{thirties})"
    return combined


def _generate_year_regex() -> str:
    """generate year regex"""
    # Just make sure it's 4 digits
    four_digits = r"(\d{4})"
    return four_digits


if __name__ == "__main__":
    _main()
