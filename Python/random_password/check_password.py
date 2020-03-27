import uuid
import hashlib
import getpass
import argparse
import sys

hashed_password_file = "hashedPassword.txt"


def hash_password(password: str):
    # uuid is used to generate a random number
    salt = uuid.uuid4().hex
    return hashlib.sha256(salt.encode() + password.encode()).hexdigest() + ":" + salt


def check_password(hashed_password, user_password):
    password, salt = hashed_password.split(":")
    return password == hashlib.sha256(salt.encode() + user_password.encode()).hexdigest()


def handle_hash_password():
    pwd = getpass.getpass()
    hashed = hash_password(pwd)
    print("Exported to delete.txt")
    with open("delete.txt", "w") as file:
        file.write(hashed)


def handle_check_password():
    with open(hashed_password_file, "r") as file:
        hashed_password = file.read()
    if hashed_password.strip() == "":
        raise RuntimeError("`{}` file is empty or nonexistent".format(hashed_password_file))
    user_input = input("Please enter a password: ")

    if check_password(hashed_password, user_input):
        print('You entered the right password')
    else:
        print('ERROR: I am sorry but the password does not match')


# Create argparse
parser = argparse.ArgumentParser(
    description="Hash password or check hashed password")

# Create subparsers
subparsers = parser.add_subparsers(title="Valid commands", dest="command_name")

# Hash password
parser_hash_pwd = subparsers.add_parser("hash", help="Hash a password")

# Check password
parser_check_pwd = subparsers.add_parser("check", help="Check a password")

# Compile all the command line parser and subparsers
args = parser.parse_args()

# If no outputs are supplied, print help
if len(sys.argv) == 1:
    parser.print_help()
    sys.exit(0)

if args.command_name == "hash":
    handle_hash_password()
elif args.command_name == "check":
    handle_check_password()
