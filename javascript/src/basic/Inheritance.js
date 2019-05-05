class Vehicle{
    constructor(vehicleNumber){
        this.vehicleNumber=vehicleNumber;
    }
    
    get vehicleNumber(){
        return this._number;
    }
    
    set vehicleNumber(vehicleNumber){
        if(!vehicleNumber){
            alert('vehicleNumber is too short')
        }
        this._number=vehicleNumber;
    }
    
    getVehicleNumber(){
        return this.vehicleNumber;   
    }
}


class TwoWheeler extends Vehicle{
        
} 

alert('My 2 wheeler Number is '+(new TwoWheeler('887')).getVehicleNumber());
