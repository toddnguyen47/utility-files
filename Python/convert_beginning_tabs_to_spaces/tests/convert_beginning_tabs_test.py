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


def test_given_no_tabs_at_beginning_when_converting_then_return_same_input(
    convert_beginning_tabs: ConvertBeginningTabs,
):
    test_str = "Hello World"
    actual_str = convert_beginning_tabs.convert(test_str, 4)
    assert "Hello World" == test_str


def test_given_tabs_within_tab_when_converting_then_inside_tabs_should_not_be_converted(
    convert_beginning_tabs: ConvertBeginningTabs,
):
    test_str = "		Hello 	there	World"
    actual_str = convert_beginning_tabs.convert(test_str, 4)
    assert "        Hello 	there	World" == actual_str
