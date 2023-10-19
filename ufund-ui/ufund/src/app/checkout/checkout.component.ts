import { Component } from '@angular/core';
import { Need } from '../Need';
import { NeedsService } from '../needs.service'

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  needs: Need[] = [];
  basket: Need[] = [];

  constructor(
    private needsService: NeedsService
  ){}

  calculateTotal(): void{
    //add up total of checkout basket
    let total = 0;
    for (let needs of this.needs) {
      total += needs.cost; // add up costs of needs
    }
  }

  addQuantity(need: Need): void{
    //increment item quantity by 1
    need.quantity += 1;
  }

  subtractQuantity(need: Need): void{
    //increment item quantity by -1
    if (need.quantity > 0){ //check for invalid quantity number in basket
      need.quantity -= 1;
    }
  }

  deleteNeed(need: Need): void{
    //remove need from checkout basket
    
  }

}


