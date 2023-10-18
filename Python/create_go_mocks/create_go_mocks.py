"""create go mocks"""
import argparse


def _main():
    """main function. use local variables instead of globals to run faster"""

    def _main_helper():
        """main 'main' function"""
        parser = argparse.ArgumentParser(description="quickly generate go mocks")
        parser.add_argument("interfaces_to_mock", nargs="+", help="classes to generate mocks for")
        args = parser.parse_args()

        _generate(args.interfaces_to_mock)

    def _generate(interfaces: list[str]):
        """generate mocks now"""
        for i_1 in interfaces:
            s_1 = i_1.split(".")
            name = s_1[len(s_1) - 1]
            mock_name = f"mock{name}"
            fn_name = f"newMock{name}()"
            print("")
            print("// /----------------------------------------------------------\\")
            print(f"// #region {mock_name}")
            print("// ------------------------------------------------------------")
            print("")

            print(f"type {mock_name} struct" + "{}")
            print("")
            print(f"func {fn_name} {i_1}" + " {")
            print(f"\ti1 := {mock_name}" + "{}")
            print("\treturn &i1")
            print("}")
            print("")
            print(f"func (m *{mock_name}) ReplaceFunctionName()" + "{")
            print("}")

            print("")
            print("// ------------------------------------------------------------")
            print(f"// #endregion {mock_name}")
            print("// \\----------------------------------------------------------/")
            print("")

    # run function now
    _main_helper()


if __name__ == "__main__":
    _main()
