# References
- https://stackoverflow.com/a/23332517
- https://www.ics.uci.edu/~pattis/common/modules46/googletestpc.html

# Overview
- Download and compile Google Test Framework
- Download Eclipse CDT
- Create C++ project
- Compile into object (.o) files
- Create a Google Test Framework project
- Link compiled object files to our test project
- Link Google Test libraries to our test project
- Run Tests. Voila!

# Downloads
- [Eclipse CDT](https://www.eclipse.org/downloads/packages/release/2019-09/r/eclipse-ide-cc-developers)
- [Google Test Framework](https://github.com/google/googletest/releases)

# Compile Google Test Framework
- Install some prerequisite programs
```
sudo apt update
sudo apt install cmake build-essential
```
- Unzip the Google Test zipped file somewhere.
- Open a terminal and change your directory (cd) into where you unzipped the Google Test library framework. Type the following commands into a terminal:
```
cmake CMakeLists.txt
make
```
- You're done!

# Eclipse CDT
For the sake of this instruction, I will have 2 projects: a `Number` project and a `TestNumber` project.

## Setting Up the Main Project
- Download either the zipped or tar Google Test Framework release. Unzip it somewhere.
- Install Eclipse CDT.
- Open Eclipse CDT.
- Click on `File` -->  `New` --> `C/C++ Project` --> `C++ Managed Build`
- Fill out your Project Name (e.g. I will enter `Number`). Under `Project Type`, select `Empty Project`. Under `Toolchains`, choose `Linux GCC`. Click on `Finish`
- *OPTIONAL*: Create a `src` folder in your `Number` project by right-clicking on the `Number` project --> `New` --> `Folder`
- Create a new class called `Number` (or whatever you want to name it). Include a simple function that returns a simple integer.
- Remember to create a `main.cpp` file, and include a `int main() {}` statement in there, otherwise the project will not build!

## Setting Up the Test Project
- Make a new project. `File` -->  `New` --> `C/C++ Project` --> `C++ Managed Build` --> `Test Number` for the name --> `Empty Project` && `Linux GCC` --> `Finish`
- Right click on your test project name and then click on `Properties`
- On the left side, click on `C/C++ General` --> `Paths and Symbols`. Under the `Includes` tab, under `Languages` click on `GNU C++`. Then, click on the `Add` button on the right. Click on `Workspace...` and click on your `Number` project. Now you can `#include "src/"` in your test files!
- In other to build what you included, you'll need to link the object files. To do this, go to the `Source Location` tab (We are still in `Paths and Symbols`), click on `Link Folder`, check `Link to folder in the file system`, then underneath the checkbox, type in:
```
PROJECT_LOC/../Number/src
```
Change the `Number` folder to whatever name you named your project earlier. `PROJECT_LOC` is an Eclipse keyword that contains the location of the Test project.
- Now we will link the Google Test framework! Go to `Library Paths` tab, and click on `Add`. Find where you unzipped the `googletest` folder. You want the `/googletest/include` folder. For example, I unzipped my google test folder to `/media/LinuxData/todd/usr/googletest-release-1.10.0/`. Thus, the path that I will enter is:
```
/media/LinuxData/todd/usr/googletest-release-1.10.0/googletest/include
```
- Click on the `Libraries` tab. Add the following 3 libraries:
```
pthread
gtest
gtest_main
```
- In the test project, create a new source file. `Right Click` --> `New` --> `Source File`. I will name my file `TestNumber.cpp`.
- To include the Google Test framework, make sure you add this include line:
```
#include "gtest/gtest.h"
```

## Eclipse C/C++ Unit Test Launch Configuration
To enable Eclipse's built-in C/C++ Unit Test GUI, we will need to make a new Launch Configuration.
- Right click on our `Test` project --> `Run As` --> `Run Configurations...`
- On the left side, click on `C/C++ Unit`. Then click on the `New Configuration` button which looks like this: ![New Configuration Button](images/runConfig.png). Name this configuration whatever you like. I will name it `TestNumber Unit Tests`
- Go to the `C/C++ Testing` tab. For `Tests Runner`, choose `Google Tests Runner`. Click `Apply`, then `Run`.
- From now on, you can run tests by selecting the Unit Test run configuration you just set up on the top bar, and then click on the `Run` button! ![Run Button](images/runButton.png)
- You're all set!
