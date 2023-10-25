import { Need } from "./Need";

export class User{
    
    private username:string;

    constructor(username:string){
        this.username = username;
    }

    getUsername(){
        return this.username;
    }

}