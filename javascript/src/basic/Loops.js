const list = ['a', 'b', 'c']
for (let i = 0; i < list.length; i++) {
    console.log(list[i]) //value
    console.log(i) //index
}

console.log("--------------------")
list.forEach((item, index) => {
    console.log(item) //value
    console.log(index) //index
})

console.log("--------------------")
let i = 0
do {
    console.log(list[i]) //value
    console.log(i) //index
    i = i + 1
} while (i < list.length)


console.log("-------------------")
 i = 0
while (i < list.length) {
    console.log(list[i]) //value
    console.log(i) //index
    i = i + 1
}

console.log("-----------For in--------");
let object={
    firstName: "Mayank",
    secondName:"Shukla"
    
}
for (let property in object) {
    console.log(property) //property name
    console.log(object[property]) //property value
}


console.log("--------------for of-----")
for (const value of ['a', 'b', 'c']) {
    console.log(value) //value
}

for (const [index, value] of ['a', 'b', 'c'].entries()) {
    console.log(index) //index
    console.log(value) //value
}