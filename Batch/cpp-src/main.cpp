#include "robocopy2.hpp"

int main(int argc, char *argv[])
{
  if (argc < 3)
  {
    std::cerr << "Please supply a source and destination directory.";
    return 1;
  }

  Robocopy2 robocopy2("config.json");
  robocopy2.execute(argv[1], argv[2]);

  return 0;
}
