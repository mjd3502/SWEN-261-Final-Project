import { Component } from '@angular/core';
import { Need } from '../Need';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent {
  basket: Need[] = [];

  calculateTotal(): void{
    
    let total = 0;
    for (let needs of this.basket) {
      total += needs.cost;
    }
  }

  addQuantity(need: Need): void{
    need.quantity += 1;
  }

  subtractQuantity(need: Need): void{
    if (need.quantity > 0){ 
      need.quantity -= 1;
    }
  }

  deleteNeed(need: Need): void{
   
    this.basket = this.basket.filter(n => n !== need);
  }

}


