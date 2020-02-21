local DurstenfeldShuffle = {}
DurstenfeldShuffle.__index = DurstenfeldShuffle

function DurstenfeldShuffle.new(self)
  local newInstance = setmetatable({}, self)
  newInstance.__init__()
  return newInstance
end

function DurstenfeldShuffle.__init__(self)

end

function DurstenfeldShuffle.swap(self, tableInput, key1, key2)
  local temp = tableInput[key1]
  tableInput[key1] = tableInput[key2]
  tableInput[key2] = temp
end

function DurstenfeldShuffle.deepCopy(self, tableInput)
  local t1 = {}
  for key, val in pairs(tableInput) do t1[key] = val end
  return t1
end

-- Ref: https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
function DurstenfeldShuffle.shuffle(self, tableInput)
  math.randomseed(os.time())
  local len1 = #tableInput
  local tableCopy = self.deepCopy(self, tableInput)
  for i = len1, 1, -1 do
    local random = math.random(1, i)
    self.swap(self, tableCopy, random, i)
  end
  return tableCopy
end

return DurstenfeldShuffle
