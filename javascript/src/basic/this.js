console.log(`----------------------this=${this}------------------`)

const car = {
    maker: 'Ford',
    model: 'Fiesta',
    drive() {
        console.log(`Driving a ${this.maker} ${this.model} car!`)
    }
}
car.drive()

const newCar = {
    maker: 'Ford',
    model: 'Fiesta'
}
newCar.drive = function() {
    console.log(`Driving a ${this.maker} ${this.model} car!`)
}
newCar.drive()

const carWithArrowFunction = {
    maker: 'Ford',
    model: 'Fiesta',
    drive: () => {
        console.log(`----------------------this=${this}------------------`)
        console.log(`Driving a ${this.maker} ${this.model} car!`)
    }
}

carWithArrowFunction.drive();

function carEnclosedInfunction(){
        return {
            maker: 'Ford',
            model: 'Fiesta',
            drive: () => {
                console.log(`----------------------this=${this}------------------`)
                console.log(`Driving a ${this.maker} ${this.model} car!`)
            }
        }
}

(new carEnclosedInfunction()).drive();

const drive = function(kmh) {
    console.log(`Driving a ${this.maker} ${this.model} car at ${kmh} km/h!`)
}

const anotherCar = {
    maker: 'Audi',
    model: 'A4'
}

drive(2);
drive.bind(anotherCar)(2);

document.querySelector('#middle').addEventListener('click', function(e) {
    console.log(this) //HTMLElement
})

document.querySelector('#header').addEventListener('click', (function(e) {
    console.log(this) //HTMLElement
}.bind(this)))