"""create go mocks"""
import argparse


class Main:
    """main class"""

    def main(self):
        """main 'main' function"""
        parser = argparse.ArgumentParser(description="quickly generate go mocks")
        parser.add_argument(
            "interfaces_to_mock", nargs="+", help="classes to generate mocks for"
        )
        args = parser.parse_args()

        self._generate(args.interfaces_to_mock)

    def _generate(self, interfaces: list[str]):
        """generate mocks now"""
        for i_1 in interfaces:
            s_1 = i_1.split(".")
            name = s_1[len(s_1) - 1]
            name = name[0].upper() + name[1:]
            mock_name = f"mock{name}"
            fn_name = f"newMock{name}()"
            print("")
            print("// /----------------------------------------------------------\\")
            print(f"// #region {mock_name}")
            print("// ------------------------------------------------------------")
            print("")

            print(f"var _ {i_1} = {fn_name}")
            print("")
            print(f"type {mock_name} struct" + "{}")
            print("")
            print(f"func {fn_name} *{mock_name}" + " {")
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


if __name__ == "__main__":
    main = Main()
    main.main()
