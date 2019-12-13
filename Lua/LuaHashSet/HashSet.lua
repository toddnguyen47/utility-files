-- HashSet implementation is based on Java's HashSet implementation
-- https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
local HashSet = {}

function HashSet:new()
  self.__index = self
  local newInstance = setmetatable({}, self)

  newInstance.sizeOfStorage = 0
  newInstance.storage = {}

  return newInstance
end

function HashSet:add(elem)
  if false == self:contains(elem) then
    self.storage[elem] = elem
    self.sizeOfStorage = self.sizeOfStorage + 1
    return true
  end
  return false
end

function HashSet:clear()
  self.storage = {}
  self.sizeOfStorage = 0
end

function HashSet:contains(elem)
  return self.storage[elem] ~= nil
end

function HashSet:isEmpty()
  return self.sizeOfStorage == 0
end

function HashSet:iterator()
  -- Ref: https://www.lua.org/pil/7.1.html
  local i = 1
  local n = #self.storage
  return function ()
    i = i + 1
    if i <= n then return self.storage[i] end
  end
end

function HashSet:remove(elem)
  if self:contains(elem) then
    self.sizeOfStorage = self.sizeOfStorage - 1
    self.storage[elem] = nil
    return true
  end
  return false
end

function HashSet:size()
  return self.sizeOfStorage
end

return HashSet
