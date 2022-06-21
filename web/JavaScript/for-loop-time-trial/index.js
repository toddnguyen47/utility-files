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
}
