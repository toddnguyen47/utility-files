"""read in environment variables using Go"""

import argparse


class Main:
    """main class"""

    def main(self):
        """main 'main' function"""
        parser = argparse.ArgumentParser(
            description="quickly generate environment variables"
        )
        parser.add_argument(
            "--delim", required=True, help="environment variable delimiter"
        )
        parser.add_argument(
            "envvars", nargs="+", help="environment variables to generate template for"
        )
        args = parser.parse_args()

        self._generate(args.delim, args.envvars)

    def _generate(self, delim: str, envvars: list[str]):
        """generate env vars templates now"""
        for envvar in envvars:
            tokens = envvar.split(delim)
            lower_tokens = []
            for token in tokens:
                l_1 = token.lower().capitalize()
                lower_tokens.append(l_1)
            variable = "".join(lower_tokens)
            private_variable = f"_{variable[0].lower()}{variable[1:]}"
            print(f"{private_variable} string")
            print("")
            print(f'{private_variable} = registry.GetString("{envvar}")')
            print("")
            print(
                f"func {variable}() string"
                + " { "
                + f"return {private_variable}"
                + " }"
            )
            print("")
            print("// -----")
            print("")


if __name__ == "__main__":
    main = Main()
    main.main()
