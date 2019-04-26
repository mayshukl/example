let arrowFunction = ()=> 'test';

/**
 * Implicit Return works with only one liner function 
 * */
console.log(arrowFunction());

arrowFunction = ()=> {
    let test='abc';
    let test2='def';
}

console.log(arrowFunction());

let car = {
    brand: 'Ford',
    model: 'Fiesta',
    start: function() {
        console.log(`Started ${this.brand} ${this.model}`)
    },
    stop: () => {
        console.log(`Stopped ${this.brand} ${this.model}`)
    }
}

car.start();
car.stop();


class Car{
    constructor(){
        this.company="Audi"
        this.number="0007"
    }

    startCar(){
        console.log(`${this.company}-${this.number} has started` )
    }

    stopCar=()=>{
        console.log(`${this.company}-${this.number} has stopped` )
    }
}

 car=new Car();
car.startCar();
car.stopCar();// In this case enclosing function will be class Car function