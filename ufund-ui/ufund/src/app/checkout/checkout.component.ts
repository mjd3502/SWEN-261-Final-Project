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

  constructor(private needsService: NeedsService){}

  calculateTotal(): void{

  }

  addQuantity(): void{

  }

  removeQuantity(): void{

  }

  deleteNeed(need: Need): void{

  }
}


