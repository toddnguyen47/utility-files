from src.states.red_state import RedState
from src.states.yellow_state import YellowState
from src.states.green_state import GreenState
import time
import datetime

if __name__ == "__main__":
    red_state = RedState()
    green_state = GreenState()
    yellow_state = YellowState()

    red_state.set_next_state(green_state)
    green_state.set_next_state(yellow_state)
    yellow_state.set_next_state(red_state)
    current_state = red_state

    while True:
        start_time = time.time()
        print("[{0}] Current State: {1}".format(
            datetime.datetime.now().ctime(), current_state.to_string()))
        while (time.time() - start_time) < current_state.DELAY_TO_NEXT_STATE_SECONDS_:
            current_state.execute()
            time.sleep(0.5)
        current_state = current_state.get_next_state()
