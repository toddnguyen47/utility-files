class Nested:
  def func1(self):
    def func2():
      def func3():
        print("func3")

      print("func2")
      func3()
    
    def invoke():
      print("func1")
      func2()
      return True
    
    return invoke()
  
  def func5(self):
    '''
    This function will not run
    '''
    self.func2()

n = Nested()
blah = n.func1()
print("Blah is " + str(blah))
n.func5()

