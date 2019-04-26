let bark = dog => {
    const say = `${dog} barked!`
    ;(() => console.log(say))()
}
bark(`Roger`);


 let prepareBark = dog => {
    const say = `${dog} barked!`
    return () => console.log(say)
}
 bark = prepareBark(`Roger`)
bark();

const rogerBark = prepareBark(`Roger`)
const sydBark = prepareBark(`Syd`)
rogerBark()
sydBark()