-- Good documentation:
-- https://www.quora.com/What-does-the-line-self-__index-self-do-in-OOP-in-Lua
-- https://www.lua.org/pil/16.2.html
-- https://www.lua.org/pil/13.4.1.html

local DerivedClass = {}

function DerivedClass:new(val1, val2)
  local parentClass = require("BaseClass")
  setmetatable(self, {__index = parentClass:new(val1)})

  local o = setmetatable({}, {
    __index = self
  })
  o.value2 = val2
  return o
end


function DerivedClass:get_value()
  return self.value + self.value2
end

local i = DerivedClass:new(1, 2)
print(i:get_value()) --> 3
i:set_value(3)
print(i:get_value()) --> 5

return DerivedClass

