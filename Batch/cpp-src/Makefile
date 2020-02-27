CC = g++
FLAGS = -std=gnu++11

SOURCEDIR = .
BUILDDIR = build

EXECUTABLE = robocopy2
SOURCES = $(wildcard $(SOURCEDIR)/*.cpp)
OBJECTS = $(patsubst $(SOURCEDIR)/%.cpp, $(BUILDDIR)/%.o, $(SOURCES))

# Ref: https://stackoverflow.com/q/16924333
all : $(EXECUTABLE)

dir : 
	mkdir -p $(BUILDDIR)

# Ref: https://www.gnu.org/software/make/manual/make.html#Automatic-Variables-1ss
$(EXECUTABLE) : $(OBJECTS)
	$(CC) $^ -o $@

$(OBJECTS) : $(BUILDDIR)/%.o : $(SOURCEDIR)/%.cpp
	$(CC) $(FLAGS) -c $< -o $@

.PHONY : clean
clean :
	rm -f $(BUILDDIR)/*.o $(EXECUTABLE)
