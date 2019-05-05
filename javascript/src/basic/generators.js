let array=[1,2,3,4,5];
let counter=0;
function* printArray(){
    if(counter<array.length){
        yield array[counter++];
    }else{
        return;
    }
}
let prArray=printArray();
let next=printArray().next();
console.log(next.value)
next=printArray().next();
console.log(next.value)
next=printArray().next();
console.log(next.value)
next=printArray().next();
console.log(next.value)