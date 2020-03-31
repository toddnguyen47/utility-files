#pragma once

#include <iostream>
#include <ctime> // localtime
#include <chrono>
#include <sstream>
#include <iomanip> // put_time
#include <thread>
#include <string>

class TerminalClock
{
private:
public:
  void display_clock();
  void infinite_display_clock();
};
