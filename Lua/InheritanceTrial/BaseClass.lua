local BaseClass = {}
BaseClass.__index = BaseClass

function BaseClass:new(val)
  local o = setmetatable({}, self)
  o.value = val
  return o
end

--setmetatable(BaseClass, {
--  __call = function (cls, ...)
--    local self = setmetatable({}, cls)
--    self:_init(...)
--    return self
--  end,
--})
--
--function BaseClass:_init(init)
--  self.value = init
--end

function BaseClass:set_value(newval)
  print("Setting `value` to: " .. newval)
  self.value = newval
end

function BaseClass:get_value()
  return self.value
end

return BaseClass

