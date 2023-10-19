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

  constructor(
    private needsService: NeedsService
  ){}

  // retreive all needs
  getNeeds(): void {
    this.needsService.getEntireNeedsCupboard().subscribe(needs => this.needs = needs);
  }

  //create list of needs on initialization
  ngOnInit(): void {
    this.getNeeds();
  }

  calculateTotal(): void{
    //add up total of funding basket
    let total = 0;
    for (let needs of this.needs) {
      total += needs.cost; // add up costs of needs
    }
  }

  addQuantity(): void{
    //increment item quantity by 1
  }

  subtractQuantity(): void{
    //increment item quantity by -1
  }

  deleteNeed(): void{
    //remove need from checkout basket
  }

}


