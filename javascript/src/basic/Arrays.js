const array1 = []
const array2 = [1, 2, 3]
const array3 = Array.of(1, 2, 3)
const array4 = Array(6).fill(1) 

console.log("-------Array every---------")

array3.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array map---------")

const array5 = array3.map((value)=>{return value*2})
array5.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array filter---------")

const array6 = array5.filter((value)=>{return value%3==0})
array6.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array reduce---------")

let sum=array5.reduce((accumulator, currentValue, currentIndex, array)=>{
    accumulator=accumulator+currentValue
    return accumulator;
},0);

console.log(sum);

console.log("-------Array forEach---------")

array5.forEach(v => {
    console.log(v)
})

console.log("-------Array for of---------")

for (let v of array5) {
    console.log(v)
}

console.log("-------Array for---------")
for (let i = 0; i < array5.length; i += 1) {
    console.log(array5[i]);
}

console.log("-------Array for iterator---------")
let it = array5[Symbol.iterator]()
console.log(it.next().value)
console.log(it.next().value)
console.log(it.next().value)

console.log("-------Array for Push---------")

array1.push(2);
array1.push(10);
array1.every((value)=>{
    console.log(value)
    return true;
});

array1.pop()
console.log("-------Array for Pop---------")
array1.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for Add at the beginning---------")

array1.unshift(0);
array1.unshift(-2,-1);
array1.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for Add at the shift---------")
array1.shift();
array1.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for splice /Get a portion of an array (remove leaves the undefined values)--------")
let array7=array1.splice(1,2)
array7.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for splice  (remove and add)-------")
array1.push(2,10,1);
array7=array1.splice(1,2,2, 'a', 'b')
array1.every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for Concat------")
array1.concat(array2).every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for index of------")
console.log("index of 1 : "+array1.concat(array2).indexOf(1));
console.log("last index of 1 : "+array1.concat(array2).lastIndexOf(1));


console.log("-------Array for find------")
console.log(" is a present "+array1.concat(array2).find((element, index, array)  => element==='a'));

console.log("-------Array for find index------")
console.log(" index of a : "+array1.concat(array2).findIndex((element, index, array)  => element==='a'));

console.log("-------Array for includes------")
console.log(" index of a : "+array1.concat(array2).includes('a'));

console.log("-------Array for includes after------")
console.log(" index of a : "+array1.concat(array2).includes('a',3));

console.log("-------Array for sort------")
array1.concat(array2).sort().every((value)=>{
    console.log(value)
    return true;
});
console.log("-------Array for reverse------")
array1.concat(array2).reverse().every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for sort custom function------")
array1.concat(array2).sort((a,b)=>a-b).every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for join function------")
console.log(array1.concat(array2).join(','));

console.log("-------Array for copy 'from'  function------")
Array.from(array2).every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for copy  'of' function------")
Array.of(...array2).every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for copy 'from'  with condition function------")
Array.from(array2,value=>value%2==0).every((value)=>{
    console.log(value)
    return true;
});

console.log("-------Array for copy copy within function------")
array2.push(4);
console.log(array2.join(','));
console.log("-------Array after within start=0, copy from =2------")
console.log(array2.copyWithin(0,2).join(','));
array2.splice(0,2,1,2)
array2.push(5)
console.log(array2.join(','));
console.log(array2.copyWithin(0,2).join(','));
array2.pop();
array2.pop();
array2.unshift(1,2);
console.log(array2.join(','));
console.log(array2.copyWithin(0,2,4).join(','));
