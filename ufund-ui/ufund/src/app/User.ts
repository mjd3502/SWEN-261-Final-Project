import { Need } from "./Need";

export class User{

    private id:number;
    private  userName:string;
    private fundingBasket:Need[];

    constructor(){
        this.id = 0;
        this.userName = "";
        this.fundingBasket = [];
    }
    
    getId():number{
        return this.id
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