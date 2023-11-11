import { Pet } from "./Pet";

export class FavoritePets{
    
    private  username:string;
    private fundingBasket:Pet[];

    constructor(){
        this.username = "";
        this.fundingBasket = [];
    }
    

    getUsername():string{
        return this.username;
    }

    getFundingBasket():Pet[]{
        return this.fundingBasket;
    }

    setUsername(username:string){
        this.username = username;
    }
    

}