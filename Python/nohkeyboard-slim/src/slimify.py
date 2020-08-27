import json
import math


class Slimify:
    def __init__(self):
        self._json_file_ = self._read_config_json()
        self.output_file_ = "keyboard-output.json"
        self.percent_shrink_ = 0.75

        self.old_top_left_x_ = 1 << 15
        self.old_top_left_y_ = 1 << 15
        self.x_offset = 0
        self.y_offset = 0
        self.data_: dict = None

        self._min_val_ = 10
        self._coords = self._Coords()
        self._prev_coords = self._PrevCoords()

    def execute(self):
        self.set_data()
        self.sort_key_data()
        self.slim_x_y_values()
        self.output()

    def sort_key_data(self):
        self.data_["Elements"].sort(key=lambda elem: (
            elem["Boundaries"][0]["Y"], elem["Boundaries"][0]["X"]))

    def set_data(self):
        self._read_json()

    def slim_x_y_values(self):
        self._set_width_and_height()
        self._set_each_key_x_y_size()

    def output(self):
        with open(self.output_file_, "w") as file_pointer:
            json.dump(obj=self.data_, fp=file_pointer, indent=2)
        print("Finished writing to {}".format(self.output_file_))

    def round_to_nearest_integer(self, float_input: float) -> int:
        return math.floor(float_input + 0.5)

    #######################################################
    #  PRIVATE FUNCTIONS
    #######################################################
    class _Coords:
        def __init__(self):
            self.top_left_x = 0
            self.top_left_y = 0
            self.bot_right_x = 0
            self.bot_right_y = 0
            self.diff_x = 0
            self.diff_y = 0

    class _PrevCoords:
        def __init__(self):
            self.x_after_changed = 0
            self.y_before_changed = 0
            self.y_after_changed = 0

    def _read_config_json(self) -> str:
        with open("config.json", "r") as file:
            data = json.load(file)
        return data["keyboard_filepath"]

    def _read_json(self):
        with open(self._json_file_, "r") as file:
            self.data_: dict = json.load(file)

    def _set_width_and_height(self):
        self._change_amount("Width")
        self._change_amount("Height")

    def _change_amount(self, key: str):
        self.data_[key] = self.round_to_nearest_integer(
            self.data_[key] * self.percent_shrink_)

    def _set_each_key_x_y_size(self):
        # Now all the data should already be sorted, first by y values, then by x values
        self._init_prev_coords()
        self._coords = self._Coords()
        first_time = True
        for elem in self.data_["Elements"]:
            boundary = elem["Boundaries"]
            self._set_cur_coords(boundary)
            self._set_coords_diff()

            if first_time:
                first_time = False
                self._prev_coords.y_before_changed = self._coords.top_left_y - 1
                self._prev_coords.x_after_changed = self._coords.top_left_x - 1
                self._prev_coords.y_after_changed = self._coords.top_left_y - 1

            self._check_and_set_new_coords()
            self._set_new_x_y_values(boundary)
            self._set_text_pos(elem["TextPosition"])

    def _init_prev_coords(self):
        self._prev_coords.y_before_changed = 0
        self._prev_coords.x_after_changed = 0
        self._prev_coords.y_after_changed = 0

    def _check_and_set_new_coords(self):
        if self._coords.top_left_y != self._prev_coords.y_before_changed:
            self._handle_new_row()
        elif self._coords.top_left_x != self._prev_coords.x_after_changed:
            self._handle_new_col()
        else:
            raise ValueError("Should never reach here!")

    def _set_coords_diff(self):
        self._coords.diff_x = self.round_to_nearest_integer(
            (self._coords.bot_right_x - self._coords.top_left_x) * self.percent_shrink_)
        self._coords.diff_y = self.round_to_nearest_integer(
            (self._coords.bot_right_y - self._coords.top_left_y) * self.percent_shrink_)

    def _set_cur_coords(self, boundary):
        self._coords.top_left_x = boundary[0]["X"]
        self._coords.top_left_y = boundary[0]["Y"]
        self._coords.bot_right_x = boundary[2]["X"]
        self._coords.bot_right_y = boundary[2]["Y"]

    def _set_text_pos(self, text_position: dict):
        # Center text
        text_position["X"] = (self._coords.bot_right_x + self._coords.top_left_x) >> 1
        text_position["Y"] = (self._coords.bot_right_y + self._coords.top_left_y) >> 1

    def _set_new_x_y_values(self, boundary):
        boundary[0]["X"] = self._coords.top_left_x
        boundary[0]["Y"] = self._coords.top_left_y
        boundary[1]["X"] = self._coords.bot_right_x
        boundary[1]["Y"] = self._coords.top_left_y
        boundary[2]["X"] = self._coords.bot_right_x
        boundary[2]["Y"] = self._coords.bot_right_y
        boundary[3]["X"] = self._coords.top_left_x
        boundary[3]["Y"] = self._coords.bot_right_y

    def _handle_new_col(self):
        # New col
        self._coords.top_left_x = self._prev_coords.x_after_changed + 1
        self._coords.bot_right_x = self._coords.top_left_x + self._coords.diff_x
        self._prev_coords.x_after_changed = self._coords.bot_right_x
        self._coords.top_left_y = self._prev_coords.y_after_changed - self._coords.diff_y
        self._coords.bot_right_y = self._prev_coords.y_after_changed

    def _handle_new_row(self):
        # New row
        self._prev_coords.y_before_changed = self._coords.top_left_y
        self._coords.top_left_y = self._prev_coords.y_after_changed + 1
        self._coords.bot_right_y = self._coords.top_left_y + self._coords.diff_y
        self._prev_coords.y_after_changed = self._coords.bot_right_y
        self._coords.bot_right_x = self._coords.top_left_x + self._coords.diff_x
        self._prev_coords.x_after_changed = self._coords.bot_right_x


if __name__ == "__main__":
    print("DISCLAIMER: This script will only work if the rows are horizontally straight.")

    slimify = Slimify()
    slimify.execute()
