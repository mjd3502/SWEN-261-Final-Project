export class Need{
    id!: number;
    name!: string;
    quantity!: number;
    surplus!:number;
    description!: string;
    cost!: number;
    type!: string;

    setQuantity(Quantity:number){
        this.quantity = Quantity;
    }

    getQuantity(){
        return this.quantity;
    }

}