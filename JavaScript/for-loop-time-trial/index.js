// number of iterations
const iterations = 5_000_000
const times = 5;

// add an empty array size equal to the number of iterations
const arraySize = [];
for (let i = 0; i < iterations; i++) {
  arraySize[i] = i;
}

/**
 * Get the average of the array
 * Ref: https://stackoverflow.com/a/41452260/6323360
 * @param {Array} arrInput 
 * @returns {Number}
 */
function average(arrInput) {
  return arrInput.reduce((curSum, cur) => curSum + cur) / arrInput.length;
}

console.log("Starting speed/execution test performance with arraySize iterations" , iterations);
console.log("arraySize", + arraySize.length);

let newArr = [];
let timesArr = []

// for
timesArr = []
console.log('for loop');
for (let time = 0; time < times; time++) {
  newArr = [];
  const startTime = performance.now();
  for (let i1 = 0; i1 <iterations; i1++) {
    newArr.push(arraySize[i1]);
  }
  const endTime = performance.now();
  timesArr.push(endTime - startTime);
}
console.log(newArr.length);
console.log('Average time: ' + average(timesArr) + ' ms');

// while
timesArr = []
console.log('while loop');
for (let time = 0; time < times; time++) {
  newArr = [];
  const startTime = performance.now();
  let i2 = 0;
  while (i2 < iterations) {
    newArr.push(arraySize[i2]);
    i2++;
  }
  const endTime = performance.now();
  timesArr.push(endTime - startTime);
}
console.log(newArr.length);
console.log('Average time: ' + average(timesArr) + ' ms');

// do...while
timesArr = [];
console.log('do...while');
for (let time = 0; time < times; time++) {
  newArr = [];
  const startTime = performance.now();
  let ie = 0;
  do {
    newArr.push(arraySize[ie]);
    ie++;
  }
  while (ie < iterations);
  const endTime = performance.now();
  timesArr.push(endTime - startTime);
}
console.log(newArr.length);
console.log('Average time: ' + average(timesArr) + ' ms');

// for...of
timesArr = [];
console.log('for...of');
for (let time = 0; time < times; time++) {
  newArr = []
  const startTime = performance.now();
  for (let ele of arraySize){
    newArr.push(ele);
  }
  const endTime = performance.now();
  timesArr.push(endTime - startTime);
}
console.log(newArr.length);
console.log('Average time: ' + average(timesArr) + ' ms');

// for...each
timesArr = [];
console.log('for...each');
for (let time = 0; time < times; time++) {
  newArr = [];
  const startTime = performance.now();
  arraySize.forEach((value) => {
    newArr.push(value);
  });
  const endTime = performance.now();
  timesArr.push(endTime - startTime);
}
console.log(newArr.length);
console.log('Average time: ' + average(timesArr) + ' ms');

// for...in
console.log('for...in is the slowest by far. We\'ll only run this once');
newArr = [];
const startTimeForIn = performance.now();
for (let ele in arraySize) {
  newArr.push(ele);
}
const endTimeForIn = performance.now();
console.log(newArr.length);
console.log('Average time: ' + (endTimeForIn - startTimeForIn) + 'ms');

/*
Output with 5_000_000 elements:

Starting speed/execution test performance with  arraySize iterations 5000000 index.js:9:9
arraySize 5000000 index.js:10:9
for: 72ms - timer ended index.js:20:9
5000000 index.js:21:9
while: 139ms - timer ended index.js:31:9
5000000 index.js:32:9
do...while: 96ms - timer ended index.js:43:9
5000000 index.js:44:9
for...in: 5019ms - timer ended index.js:52:9
5000000 index.js:53:9
for...of: 257ms - timer ended index.js:60:9
10000000 index.js:61:9
for...each: 89ms - timer ended index.js:69:9
5000000
 */

