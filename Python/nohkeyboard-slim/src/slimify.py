import json
import math


class Slimify:
    def __init__(self):
        self.json_file_ = "game_fps1/keyboard.json"
        self.output_file_ = "keyboard-output.json"
        self.percent_shrink_ = 0.75
        self.old_top_left_x_ = 1 << 15
        self.old_top_left_y_ = 1 << 15
        self.new_top_left_x_ = 1 << 15
        self.new_top_left_y_ = 1 << 15
        self.x_offset = 0
        self.y_offset = 0
        self.data_: dict = None

        self._min_val_ = 10

    def execute(self):
        self.set_data()
        self.change_width_height()
        self.output()

    def set_data(self):
        self._read_json()

    def change_width_height(self):
        self._set_width_and_height()
        self._set_x_y_size()

    def output(self):
        with open(self.output_file_, "w") as file_pointer:
            json.dump(obj=self.data_, fp=file_pointer, indent=2)

        print("Finished writing to {}".format(self.output_file_))

    def round_to_nearest_integer(self, float_input: float) -> int:
        return math.floor(float_input + 0.5)

    #######################################################
    #  PRIVATE FUNCTIONS
    #######################################################

    def _read_json(self):
        with open(self.json_file_, "r") as file:
            self.data_: dict = json.load(file)

    def _set_width_and_height(self):
        self._change_amount("Width")
        self._change_amount("Height")

    def _set_x_y_size(self):
        self._set_x_y_percent()
        self._set_offset()

    def _set_x_y_percent(self):
        for elem in self.data_["Elements"]:
            for boundary in elem["Boundaries"]:
                self.old_top_left_x_ = min(self.old_top_left_x_, boundary["X"])
                self.old_top_left_y_ = min(self.old_top_left_y_, boundary["Y"])
                boundary["X"] = self.round_to_nearest_integer(
                    boundary["X"] * self.percent_shrink_)
                boundary["Y"] = self.round_to_nearest_integer(
                    boundary["Y"] * self.percent_shrink_)

                self.new_top_left_x_ = min(self.new_top_left_x_, boundary["X"])
                self.new_top_left_y_ = min(self.new_top_left_y_, boundary["Y"])

    def _set_offset(self):
        self.x_offset = self.old_top_left_x_ - self.new_top_left_x_
        self.y_offset = self.old_top_left_y_ - self.new_top_left_y_
        for elem in self.data_["Elements"]:
            for boundary in elem["Boundaries"]:
                boundary["X"] += self.x_offset
                boundary["Y"] += self.y_offset
            min_x = elem["Boundaries"][0]["X"]
            min_y = elem["Boundaries"][0]["Y"]
            max_x = elem["Boundaries"][2]["X"]
            max_y = elem["Boundaries"][2]["Y"]
            elem["TextPosition"]["X"] = self.round_to_nearest_integer(
                (min_x + max_x) / 2)
            elem["TextPosition"]["Y"] = self.round_to_nearest_integer(
                (min_y + max_y) / 2)

    def _change_amount(self, key: str):
        self.data_[key] = self.round_to_nearest_integer(
            self.data_[key] * self.percent_shrink_)


if __name__ == "__main__":
    slimify = Slimify()
    slimify.execute()
