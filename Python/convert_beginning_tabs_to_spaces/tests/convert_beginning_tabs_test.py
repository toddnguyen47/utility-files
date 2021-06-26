from src.convert_beginning_tabs import ConvertBeginningTabs

import pytest


@pytest.fixture
def convert_beginning_tabs() -> ConvertBeginningTabs:
    return ConvertBeginningTabs()


def test_given_beginning_tabs_when_converting_then_return_spaces(
    convert_beginning_tabs: ConvertBeginningTabs,
):
    test_str = "		Hello World"
    actual_str = convert_beginning_tabs.convert(test_str, 4)
    assert "        Hello World" == actual_str


# test no tabs at the beginning
# test tabs within tab should not be converted
