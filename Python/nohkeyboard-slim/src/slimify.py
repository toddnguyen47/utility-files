import json


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
                boundary["X"] = round(
                    boundary["X"] * self.percent_shrink_)
                boundary["Y"] = round(
                    boundary["Y"] * self.percent_shrink_)

                self.new_top_left_x_ = min(self.new_top_left_x_, boundary["X"])
                self.new_top_left_y_ = min(self.new_top_left_y_, boundary["Y"])

            elem["TextPosition"]["X"] = round(
                elem["TextPosition"]["X"] * self.percent_shrink_)
            elem["TextPosition"]["Y"] = round(
                elem["TextPosition"]["Y"] * self.percent_shrink_)

    def _set_offset(self):
        self.x_offset = self.old_top_left_x_ - self.new_top_left_x_
        self.y_offset = self.old_top_left_y_ - self.new_top_left_y_
        for elem in self.data_["Elements"]:
            for boundary in elem["Boundaries"]:
                boundary["X"] += self.x_offset
                boundary["Y"] += self.y_offset
            elem["TextPosition"]["X"] += self.x_offset
            elem["TextPosition"]["Y"] += self.y_offset

    def _get_pos_size_(self, value: int) -> int:
        if value > self._min_val_:
            value = round(value * self.percent_shrink_)
        return value

    def _change_amount(self, key: str):
        self.data_[key] = round(self.data_[key] * self.percent_shrink_)


if __name__ == "__main__":
    slimify = Slimify()
    slimify.execute()
