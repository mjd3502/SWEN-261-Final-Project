import { Need } from "./Need";

export interface User{
    id:number,
    username:string,
    fundingBasket:Need[],
    
}