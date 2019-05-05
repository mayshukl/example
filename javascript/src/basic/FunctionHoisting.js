dosomething()
function dosomething() {
    console.log('did something')
}

/***
 * 
 * Upper code will get converted to below code as per js rules
 * 
 * function dosomething() {
        console.log('did something')
    }
    dosomething()
 * 
 * */

dosomethingWithConst()
const dosomethingWithConst=function dosomethingWithConst() {
    console.log('did something')
}

/***
 *
 * Upper code will get converted to below code as per js rules
 *
 const dosomethingWithConst
 dosomethingWithConst()
 dosomethingWithConst = function dosomethingWithConst() {
    console.log('did something')
}
 *
 * */


