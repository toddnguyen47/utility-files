#include <cmath>
#include <iostream>
#include <string>
#include "TutorialConfig.h"

#ifdef USE_MYMATH
#include "mysqrt.h"
#endif

using namespace std;

int calculateSqrt(const double inputValue);

int main(int argc, char **argv)
{
  if (argc < 2)
  {
    // report version
    std::cout << argv[0] << " Version " << InterfaceSample_VERSION_MAJOR << "."
              << InterfaceSample_VERSION_MINOR << std::endl;
    std::cout << "Usage: " << argv[0] << " number" << std::endl;
    return 1;
  }

  // convert input to double
  const double inputValue = std::stod(argv[1]);

  // calculate square root
  const double sqrtValue = calculateSqrt(inputValue);

  std::cout << "The square root of " << inputValue << " is " << sqrtValue
            << std::endl;
  return 0;
}

int calculateSqrt(const double inputValue)
{
  // calculate square root
#ifdef USE_MYMATH
  const double outputValue = mysqrt(inputValue);
#else
  const double outputValue = sqrt(inputValue);
#endif

  return outputValue;
}
