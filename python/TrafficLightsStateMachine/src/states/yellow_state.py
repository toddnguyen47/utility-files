from src.states.i_state import State


class YellowState(State):
    def __init__(self):
        self.DELAY_TO_NEXT_STATE_SECONDS_ = 2
        self._next_state_: State = None

    def get_next_state(self):
        return self._next_state_

    def set_next_state(self, next_state: State):
        self._next_state_ = next_state

    def to_string(self) -> str:
        return "Yellow"

    def execute(self):
        print("Cars should slow down.")
