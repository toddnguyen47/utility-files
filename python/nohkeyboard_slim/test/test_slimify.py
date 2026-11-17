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
            },
            {
                "__type": "KeyboardKey",
                "Id": 2,
                "Boundaries": [
                    {
                        "X": 53,
                        "Y": 9
                    },
                    {
                        "X": 96,
                        "Y": 9
                    },
                    {
                        "X": 96,
                        "Y": 52
                    },
                    {
                        "X": 53,
                        "Y": 52
                    }
                ],
                "KeyCodes": [
                    49
                ],
                "Text": "1",
                "TextPosition": {
                    "X": 74,
                    "Y": 30
                },
                "ChangeOnCaps": False,
                "ShiftText": "!"
            },
        ],
        "Height": 237,
        "Version": 2,
        "Width": 380
    }
    yield slimify


def mult_and_floor(slimify_object: Slimify, input1: float, input2: float) -> float:
    return slimify_object.round_to_nearest_integer(input1 * input2)


class TestSlimify:
    def test_two_by_two_column(self, slimify_object):
        slimify_object.sort_key_data()
        assert slimify_object.data_["Elements"][0]["Boundaries"][0]["X"] == 9
        assert slimify_object.data_["Elements"][0]["Boundaries"][0]["Y"] == 9

        assert slimify_object.data_["Elements"][1]["Boundaries"][0]["X"] == 53
        assert slimify_object.data_["Elements"][1]["Boundaries"][0]["Y"] == 9

        assert slimify_object.data_["Elements"][2]["Boundaries"][0]["X"] == 9
        assert slimify_object.data_["Elements"][2]["Boundaries"][0]["Y"] == 53

        assert slimify_object.data_["Elements"][3]["Boundaries"][0]["X"] == 77
        assert slimify_object.data_["Elements"][3]["Boundaries"][0]["Y"] == 53

    def test_slimming(self, slimify_object):
        slimify_object.sort_key_data()
        slimify_object.slim_x_y_values()

        # '`' key
        assert slimify_object.data_[
            "Elements"][0]["Boundaries"][0]["X"] == 9
        assert slimify_object.data_[
            "Elements"][0]["Boundaries"][0]["Y"] == 9

        # '1' key
        # 32 = 43 * 0.75
        assert slimify_object.data_[
            "Elements"][1]["Boundaries"][0]["X"] == 9 + 32 + 1
        assert slimify_object.data_[
            "Elements"][1]["Boundaries"][0]["Y"] == 9

        # 'Tab' key
        assert slimify_object.data_[
            "Elements"][2]["Boundaries"][0]["X"] == 9
        assert slimify_object.data_[
            "Elements"][2]["Boundaries"][0]["Y"] == 9 + 32 + 1

        # 'Q' key
        # Tab size is 76 - 9 = 67, 67 * 0.75 = 50
        assert slimify_object.data_[
            "Elements"][3]["Boundaries"][0]["X"] == 9 + 50 + 1
        assert slimify_object.data_[
            "Elements"][3]["Boundaries"][0]["Y"] == 9 + 32 + 1

    def test_text_after_slimming(self, slimify_object):
        slimify_object.sort_key_data()
        slimify_object.slim_x_y_values()
        # '`' should be at (25, 25)
        assert slimify_object.data_["Elements"][0]["TextPosition"]["X"] == 25
        assert slimify_object.data_["Elements"][0]["TextPosition"]["Y"] == 25
        # 'Q' should be at (58, 25)
        assert slimify_object.data_["Elements"][1]["TextPosition"]["X"] == 58
        assert slimify_object.data_["Elements"][1]["TextPosition"]["Y"] == 25
        # 'Tab' should be at (34, 58)
        assert slimify_object.data_["Elements"][2]["TextPosition"]["X"] == 34
        assert slimify_object.data_["Elements"][2]["TextPosition"]["Y"] == 58
        # 'Q' should be at (76, 58)
        assert slimify_object.data_["Elements"][3]["TextPosition"]["X"] == 76
        assert slimify_object.data_["Elements"][3]["TextPosition"]["Y"] == 58

    def test_keyboard_size(self, slimify_object):
        old_width = slimify_object.data_["Width"]
        old_height = slimify_object.data_["Height"]
        slimify_object.slim_x_y_values()
        assert slimify_object.data_["Width"] == slimify_object.round_to_nearest_integer(
            old_width * slimify_object.percent_shrink_)
        assert slimify_object.data_["Height"] == slimify_object.round_to_nearest_integer(
            old_height * slimify_object.percent_shrink_)
