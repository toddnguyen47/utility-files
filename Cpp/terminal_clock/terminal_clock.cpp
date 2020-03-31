#include "terminal_clock.hpp"

/**
 * Ref: https://stackoverflow.com/a/27856440
 * Ref: https://stackoverflow.com/a/17223443
 */
void TerminalClock::display_clock()
{
  auto current_time = std::chrono::system_clock::now();
  auto in_time_t = std::chrono::system_clock::to_time_t(current_time);
  // Mon, Mar 30 | 16:39
  const char *time_format = "%a, %b %d  |  %H:%M:%S";

  std::stringstream ss;
  ss << std::put_time(std::localtime(&in_time_t), time_format);
  std::cout << "|  " << ss.str() << "  |" << std::flush;
}

/**
 * Ref: https://stackoverflow.com/a/10613664
 */
void TerminalClock::infinite_display_clock()
{
  std::cout << "Use Ctrl + C to stop the clock"
            << std::endl
            << "o---------------o------------o"
            << std::endl;
  while (true)
  {
    this->display_clock();
    std::this_thread::sleep_for(std::chrono::seconds(1));
    std::cout << "\r";
  }
}
