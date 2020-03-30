#include "terminal_clock.hpp"

/**
 * Ref: https://stackoverflow.com/a/27856440
 * Ref: https://stackoverflow.com/a/17223443
 */
void TerminalClock::display_clock()
{
  auto current_time = std::chrono::system_clock::now();
  auto in_time_t = std::chrono::system_clock::to_time_t(current_time);

  std::stringstream ss;
  ss << std::put_time(std::localtime(&in_time_t), "%Y-%m-%d %X");
  std::cout << ss.str() << "\n";
}
