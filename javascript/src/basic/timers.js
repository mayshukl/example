console.log('---------------------print array without for loop-------------');

let array=[1,2,3,4];
let counter=0;

let printArray=()=>{
    if(counter!=array.length){
        console.log(array[counter++]);
        setTimeout(printArray,0);
    }
}
setTimeout(printArray,0);