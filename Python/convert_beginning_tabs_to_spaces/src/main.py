from convert_beginning_tabs import ConvertBeginningTabs

_DIR_PATH = "tests/resources"
_NUM_SPACES = 4
_EXTENSION = ""

if __name__ == "__main__":
    convert_beginning_tabs = ConvertBeginningTabs()
    convert_beginning_tabs.convert_on_files_with_ext(_DIR_PATH, _NUM_SPACES, _EXTENSION)
