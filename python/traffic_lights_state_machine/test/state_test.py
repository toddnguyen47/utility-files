import unittest
import time
from src.states.red_state import RedState
from src.states.green_state import GreenState
from src.states.yellow_state import YellowState


class StateTest(unittest.TestCase):
    def setUp(self) -> None:
        self.red_state_ = RedState()
        self.green_state_ = GreenState()
        self.yellow_state_ = YellowState()

        self.red_state_.set_next_state(self.green_state_)
        self.green_state_.set_next_state(self.yellow_state_)
        self.yellow_state_.set_next_state(self.red_state_)

    def test_red_to_green(self):
        self.assertEqual(self.green_state_, self.red_state_.get_next_state())
        self.assertEqual(self.green_state_.to_string(), self.red_state_.get_next_state().to_string())

    def test_yellow_idle(self):
        start_time = time.time()
        time.sleep(self.yellow_state_.DELAY_TO_NEXT_STATE_SECONDS_)
        end_time = time.time()
        self.assertAlmostEqual(
            end_time - start_time,
            self.yellow_state_.DELAY_TO_NEXT_STATE_SECONDS_,
            delta=0.5
        )


if __name__ == '__main__':
    unittest.main()
