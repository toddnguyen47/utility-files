#include "robocopy2.hpp"

Robocopy2::Robocopy2(const string filenameInput) : filename(filenameInput) {}
Robocopy2::~Robocopy2() {}

void Robocopy2::execute(const string srcDirectory, const string destDirectory)
{
  chrono::steady_clock::time_point begin = chrono::steady_clock::now();
  this->execute_batch_file(srcDirectory, destDirectory);
  chrono::steady_clock::time_point end = chrono::steady_clock::now();
  this->print_elapsed_time(begin, end);
}

void Robocopy2::execute_batch_file(const string srcDirectory, const string destDirectory)
{
  json j = this->get_json_object();
  string batchFilePath = j["batchFilePath"];
  string command = batchFilePath + " " + srcDirectory + " " + destDirectory;
  system(command.c_str());
}

void Robocopy2::print_elapsed_time(
    chrono::steady_clock::time_point begin,
    chrono::steady_clock::time_point end)
{
  // chrono::seconds duration = chrono::duration_cast<chrono::seconds>(end - begin);
  chrono::duration<double> duration = end - begin;
  cout << "*****" << endl;
  cout << "Time Elapsed: " << duration.count() << " seconds" << endl;
}

json Robocopy2::get_json_object()
{
  std::ifstream ifs(this->filename);
  json j;
  ifs >> j;
  return j;
}
