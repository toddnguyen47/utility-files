from sys import platform


class ElapsedTime:
    def __init__(self) -> None:
        pass

    def execute(self):
        if platform.lower() == "win32":
            self._execute_batch()
        else:
            self._execute_bash()

    def _execute_batch(self):
        print("Executing batch!")

    def _execute_bash(self):
        print("Executing bash")


if __name__ == "__main__":
    elapsed_time = ElapsedTime()
    elapsed_time.execute()
