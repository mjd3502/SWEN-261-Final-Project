import { Need } from "./Need";

export class FundingBasket{
    
    private  userName:string;
    private fundingBasket:Map<number,Need>;

    constructor(){
        this.userName = "";
        this.fundingBasket = new Map();
    }
    

    getUserName():string{
        return this.userName;
    }

    getFundingBasket():Map<number,Need>{
        return this.fundingBasket;
    }

    setUsername(userName:string){
        this.userName = userName;
    }
    

}