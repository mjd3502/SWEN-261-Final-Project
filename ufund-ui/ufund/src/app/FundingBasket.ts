import { Need } from "./Need";

export class FundingBasket{
    
    private  userName:string;
    private fundingBasket:Need[];

    constructor(){
        this.userName = "";
        this.fundingBasket = [];
    }
    

    getUserName():string{
        return this.userName;
    }

    getFundingBasket():Need[]{
        return this.fundingBasket;
    }

    setUsername(userName:string){
        this.userName = userName;
    }
    

}