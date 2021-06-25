from src.convert_beginning_tabs import ConvertBeginningTabs

import pytest


@pytest.fixture
def convert_beginning_tabs():
    return ConvertBeginningTabs()


def test_true(convert_beginning_tabs):
    assert convert_beginning_tabs.return_two() == 2
