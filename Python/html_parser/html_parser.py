import os

from bs4 import BeautifulSoup

_EXTENSION = ".html"
_FOLDER_DIR = "book"


class HtmlParser:
    def __init__(self) -> None:
        pass

    def execute(self):
        for root, _, files in os.walk(os.getcwd(), _FOLDER_DIR):
            for name in files:
                if name.endswith(_EXTENSION):
                    self._read_and_remove_p_classes(os.path.join(root, name))

    def _read_and_remove_p_classes(self, file_path: str):
        data_soup = None
        with open(file_path, "r", encoding="utf8") as file:
            print("Reading '{}'".format(file_path))
            data_soup = BeautifulSoup(file, "html.parser")

            for tag in ["p", "i"]:
                for paragraph in data_soup.find_all(tag):
                    for attr in ["class", "id"]:
                        del paragraph[attr]

                    # Add class to paragraphs
                    if tag == "p":
                        paragraph["class"] = "paragraph-indent"

        if data_soup is not None:
            print("Writing to: '{}'".format(file_path))
            with open(file_path, "w", encoding="utf8") as file:
                file.write(str(data_soup))


if __name__ == "__main__":
    html_parser = HtmlParser()
    html_parser.execute()
