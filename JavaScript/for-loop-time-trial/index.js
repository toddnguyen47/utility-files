// number of iterations
const iterations = 5_000_000;
const times = 30;

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

function runTests() {
  console.log("Starting speed/execution test performance with arraySize iterations" , iterations);
  console.log("arraySize", + arraySize.length);
  console.log('Times ran: ' + times);

  let newArr = [];
  let timesArr = []

  // for
  timesArr = []
  console.log('for loop');
  for (let time = 0; time < times; time++) {
    newArr = [];
    const startTime = performance.now();
    for (let i1 = 0; i1 < iterations; i1++) {
      // Do nothing
    }
    const endTime = performance.now();
    timesArr.push(endTime - startTime);
  }
  console.log('Average time: ' + average(timesArr) + ' ms');
  console.log(newArr.length, timesArr.length);

  // while
  timesArr = []
  console.log('while loop');
  for (let time = 0; time < times; time++) {
    newArr = [];
    const startTime = performance.now();
    let i2 = 0;
    while (i2 < iterations) {
      // Do nothing
      i2++;
    }
    const endTime = performance.now();
    timesArr.push(endTime - startTime);
  }
  console.log('Average time: ' + average(timesArr) + ' ms');
  console.log(newArr.length, timesArr.length);

  // do...while
  timesArr = [];
  console.log('do...while');
  for (let time = 0; time < times; time++) {
    newArr = [];
    const startTime = performance.now();
    let ie = 0;
    do {
      // Do nothing
      ie++;
    }
    while (ie < iterations);
    const endTime = performance.now();
    timesArr.push(endTime - startTime);
  }
  console.log('Average time: ' + average(timesArr) + ' ms');
  console.log(newArr.length, timesArr.length);

  // for...of
  timesArr = [];
  console.log('for...of');
  for (let time = 0; time < times; time++) {
    newArr = []
    const startTime = performance.now();
    for (const ele of arraySize){
      // Do nothing
    }
    const endTime = performance.now();
    timesArr.push(endTime - startTime);
  }
  console.log('Average time: ' + average(timesArr) + ' ms');
  console.log(newArr.length, timesArr.length);

  // for...each
  timesArr = [];
  console.log('for...each');
  for (let time = 0; time < times; time++) {
    newArr = [];
    const startTime = performance.now();
    arraySize.forEach((value) => {
      // Do nothing
    });
    const endTime = performance.now();
    timesArr.push(endTime - startTime);
  }
  console.log('Average time: ' + average(timesArr) + ' ms');
  console.log(newArr.length, timesArr.length);

  // for...in
  console.log('for...in is the slowest by far. We\'ll only run this once');
  newArr = [];
  const startTimeForIn = performance.now();
  for (let ele in arraySize) {
    // Do nothing
  }
  const endTimeForIn = performance.now();
  console.log('Average time: ' + (endTimeForIn - startTimeForIn) + 'ms');
  console.log(newArr.length);

  /*
  Output with 5_000_000 elements, 30 times on Firefox 91.0:

  Starting speed/execution test performance with arraySize iterations 5000000 index.js:22:11
  arraySize 5000000 index.js:23:11
  Times ran: 30 index.js:24:11
  for loop index.js:31:11
  Average time: 2.7333333333333334 ms index.js:41:11
  0 30 index.js:42:11
  while loop index.js:46:11
  Average time: 3.533333333333333 ms index.js:58:11
  0 30 index.js:59:11
  do...while index.js:63:11
  Average time: 3.7 ms index.js:76:11
  0 30 index.js:77:11
  for...of index.js:81:11
  Average time: 31.533333333333335 ms index.js:91:11
  0 30 index.js:92:11
  for...each index.js:96:11
  Average time: 24.566666666666666 ms index.js:106:11
  0 30 index.js:107:11
  for...in is the slowest by far. We'll only run this once index.js:110:11
  Average time: 7802ms index.js:117:11
  0
  */
}
