import pytest
from src.slimify import Slimify


@pytest.fixture(scope="function")
def slimify_object():
    slimify = Slimify()
    slimify.data_ = {
        "Elements": [
            {
                "__type": "KeyboardKey",
                "Id": 0,
                "Boundaries": [
                    {
                        "X": 9,
                        "Y": 53
                    },
                    {
                        "X": 76,
                        "Y": 53
                    },
                    {
                        "X": 76,
                        "Y": 96
                    },
                    {
                        "X": 9,
                        "Y": 96
                    }
                ],
                "KeyCodes": [
                    9
                ],
                "Text": "Tab",
                "TextPosition": {
                    "X": 42,
                    "Y": 74
                },
                "ChangeOnCaps": True,
                "ShiftText": "TAB"
            },
            {
                "__type": "KeyboardKey",
                "Id": 12,
                "Boundaries": [
                    {
                        "X": 77,
                        "Y": 53
                    },
                    {
                        "X": 120,
                        "Y": 53
                    },
                    {
                        "X": 120,
                        "Y": 96
                    },
                    {
                        "X": 77,
                        "Y": 96
                    }
                ],
                "KeyCodes": [
                    81
                ],
                "Text": "q",
                "TextPosition": {
                    "X": 98,
                    "Y": 74
                },
                "ChangeOnCaps": True,
                "ShiftText": "Q"
            },
            {
                "__type": "KeyboardKey",
                "Id": 34,
                "Boundaries": [
                    {
                        "X": 9,
                        "Y": 9
                    },
                    {
                        "X": 52,
                        "Y": 9
                    },
                    {
                        "X": 52,
                        "Y": 52
                    },
                    {
                        "X": 9,
                        "Y": 52
                    }
                ],
                "KeyCodes": [
                    192
                ],
                "Text": "`",
                "TextPosition": {
                    "X": 30,
                    "Y": 30
                },
                "ChangeOnCaps": False,
                "ShiftText": "~"
            }
        ],
        "Height": 237,
        "Version": 2,
        "Width": 380
    }
    yield slimify


def mult_and_floor(input1: float, input2: float) -> float:
    return round(input1 * input2)


class TestSlimify:
    def test_get_pos_size(self, slimify_object):
        assert slimify_object._get_pos_size_(100) == 75

    def test_change_width_height(self, slimify_object):
        slimify_object.change_width_height()
        boundaries = slimify_object.data_["Elements"][0]["Boundaries"]

        slimify_object.x_offset = 2
        slimify_object.y_offset = 2

        assert boundaries[0]["X"] \
            == mult_and_floor(9, slimify_object.percent_shrink_) + slimify_object.x_offset
        assert boundaries[0]["Y"] \
            == mult_and_floor(53, slimify_object.percent_shrink_) + slimify_object.y_offset

        assert boundaries[1]["X"] \
            == mult_and_floor(76, slimify_object.percent_shrink_) + slimify_object.x_offset
        assert boundaries[1]["Y"] \
            == mult_and_floor(53, slimify_object.percent_shrink_) + slimify_object.y_offset

        assert boundaries[2]["X"] \
            == mult_and_floor(76, slimify_object.percent_shrink_) + slimify_object.x_offset
        assert boundaries[2]["Y"] \
            == mult_and_floor(96, slimify_object.percent_shrink_) + slimify_object.y_offset

        assert boundaries[3]["X"] \
            == mult_and_floor(9, slimify_object.percent_shrink_) + slimify_object.x_offset
        assert boundaries[3]["Y"] \
            == mult_and_floor(96, slimify_object.percent_shrink_) + slimify_object.y_offset

    def test_set_old_min_x_and_min_y(self, slimify_object):
        slimify_object.change_width_height()
        assert slimify_object.old_top_left_x_ == 9
        assert slimify_object.old_top_left_y_ == 9

    def test_set_new_min_x_and_min_y(self, slimify_object):
        slimify_object.change_width_height()
        assert slimify_object.new_top_left_x_ == round(
            9 * slimify_object.percent_shrink_)
        assert slimify_object.new_top_left_y_ == round(
            9 * slimify_object.percent_shrink_)
