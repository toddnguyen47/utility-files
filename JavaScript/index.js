// number of iterations
const iterations = 5_000_000
// add an empty array size equal to the number of iterations
const arraySize = [];
for (let i = 0; i < iterations; i++) {
  arraySize[i] = i;
}

console.log("Starting speed/execution test performance with  arraySize iterations" , iterations);
console.log("arraySize", + arraySize.length);

let newArr = [];

// for
newArr = [];
console.time("for");
for (let i1 = 0; i1 <iterations; i1++) {
  newArr.push(arraySize[i1]);
}
console.timeEnd("for");
console.log(newArr.length);

// while
newArr = [];
console.time("while");
let i2 = 0;
while (i2 < iterations) {
  newArr.push(arraySize[i2]);
  i2++;
}
console.timeEnd("while");
console.log(newArr.length);

// do...while
newArr = [];
console.time("do...while");
let ie = 0;
do {
  newArr.push(arraySize[ie]);
  ie++;
}
while (ie < iterations);
console.timeEnd("do...while");
console.log(newArr.length);

// for...in
newArr = [];
console.time("for...in");
for (let ele in arraySize) {
  newArr.push(ele);
}
console.timeEnd("for...in");
console.log(newArr.length);

// for...of
console.time("for...of");
for (let ele of arraySize){
  newArr.push(ele);
}
console.timeEnd("for...of");
console.log(newArr.length);

// for...each
newArr = [];
console.time("for...each");
arraySize.forEach((value) => {
  newArr.push(value);
});
console.timeEnd("for...each");
console.log(newArr.length);

