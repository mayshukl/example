class Person{
    constructor(name){
        this.name=name;
    }
    
    sayHello(){
        return "Hello Word! This is "+this.name;
    }
}
alert((new Person("Mayank").sayHello()));