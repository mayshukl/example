console.log('-----------------From Promise.js-----------------------');

let firstPromise=new Promise((resolve ,reject)=>{
    console.log('Executing Promise');
    console.log("promise will start executing just after declaration");
    setTimeout(()=>{
        resolve();
    },10)
})

firstPromise.then((resolve)=>{
    console.log('promise resolved');
})


firstPromise.then((resolve)=>{
    console.log('You can have more than one resolve function');
})


let promise10Sec=new Promise((resolve ,reject)=>{
    setTimeout(()=>{
        console.log("resolved in 100 sec");
        resolve('firstPromise');
    },1000)
})
let promise20Sec=new Promise((resolve ,reject)=>{
    setTimeout(()=>{
        console.log("resolved in 200 sec");
        resolve('secondPromise');
    },2000)
})


Promise.all([promise10Sec,promise20Sec]).then(([res1, res2])=>{
    console.log("both promises resolved");
});

Promise.race([promise10Sec,promise20Sec]).then((resolve)=>{
    console.log(`race won by ${resolve}`);
});
