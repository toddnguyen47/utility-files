import pytest
import convert_rgb_to_hex


def test_all_white():
    assert "#ffffff" == convert_rgb_to_hex.convert("255,255,255")


def test_yellow():
    assert "#fcba03" == convert_rgb_to_hex.convert("252, 186, 3")


def test_green():
    assert "#39b362" == convert_rgb_to_hex.convert("57, 179, 98")


def test_convert_list():
    delim_char = ":"
    assert delim_char.join(("#ffffff", "#fcba03", "#39b362")) == \
        convert_rgb_to_hex.convert_list(
        ["255,255,255", "252, 186, 3", "57, 179, 98"], delim_char)


def test_different_delimiter():
    delim_char = "\n"
    assert delim_char.join(("#ffffff", "#fcba03", "#39b362")) == \
        convert_rgb_to_hex.convert_list(
        ["255,255,255", "252, 186, 3", "57, 179, 98"], delim_char)
