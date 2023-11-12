import { Pet } from "./Pet";

export class FavoritePets{
    
    private  username:string;
    private favorites:Pet[];

    constructor(){
        this.username = "";
        this.favorites = [];
    }
    

    getUsername():string{
        return this.username;
    }

    getFavoritePets():Pet[]{
        return this.favorites;
    }

    setUsername(username:string){
        this.username = username;
    }
    

}