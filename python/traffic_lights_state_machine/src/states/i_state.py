class State:
    def get_next_state(self):
        raise NotImplementedError()

    def set_next_state(self, next_state):
        raise NotImplementedError()

    def to_string(self) -> str:
        raise NotImplementedError()

    def execute(self):
        raise NotImplementedError()
