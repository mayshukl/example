// To clear already defined Classes
window.clear();

class Exception{
    constructor(message){
        this.message=message;
    }
}

class InvalidArgumentException extends Exception{
    
}


class Vehicle{
    constructor(vehicleNumber){
        this.vehicleNumber=vehicleNumber;
    }

    get vehicleNumber(){
        return this._number;
    }

    set vehicleNumber(vehicleNumber){
        if(!vehicleNumber){
           throw new InvalidArgumentException('Input vehicle number is too short');
        }
        this._number=vehicleNumber;
    }

    getVehicleNumber(){
        return this.vehicleNumber;
    }
}


class TwoWheeler extends Vehicle{

}



try{
    let myTwoWheeler=new TwoWheeler();
}catch (e) {
    alert(e.message);
}finally {
    console.log("In Finally block");
}
