from multiprocessing import Pool
import time

def do_work(int_input: int):
    # Emulate some work by sleeping for x seconds
    time.sleep(0.01)


if __name__ == "__main__":
    # Ref: https://stackoverflow.com/a/5668200
    # Ref: https://stackoverflow.com/a/22171593
    num_processes = 2
    num_tasks = 10000
    p = Pool(num_processes)
    rs = p.imap_unordered(do_work, range(num_tasks))

    for count, elem in enumerate(rs, 1):
        # Print every 10
        if count % 10 == 0 or count == num_tasks:
            s = "Progress: {0}/{1} [{2:.2%}]".format(count, num_tasks, count / num_tasks)
            print(s, end="\r")

    print("")

    p.close() # No more work
    p.join() # Wait for completion

    print("FINISHED")
