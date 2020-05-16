#pragma once

#include <chrono>
#include <iostream>
#include "nlohmann/json.hpp"
#include <iomanip>
#include <fstream>

using json = nlohmann::json;
using namespace std;

class Robocopy2
{
private:
  string filename;

  json get_json_object();
  void execute_batch_file(const string srcDirectory, const string destDirectory);
  void print_elapsed_time(
      chrono::steady_clock::time_point begin,
      chrono::steady_clock::time_point end);

public:
  Robocopy2(const string fileNameInput);
  ~Robocopy2();
  void execute(const string srcDirectory, const string destDirectory);
};
