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

  getNeeds(): void {
    this.needsService.getEntireNeedsCupboard().subscribe(needs => this.needs = needs);
  }

  ngOnInit(): void {
    this.getNeeds();
  }

  calculateTotal(): void{
    //add up total of funding basket
  }

  addQuantity(): void{
    //increment item quantity by 1
  }

  subtractQuantity(): void{
    //increment item quantity by -1
  }

  deleteNeed(need: Need): void{
    //remove need from checkout basket
  }

}


