import { Component,OnInit } from '@angular/core';
import { Need } from '../Need';
import { UserHelperService } from '../user-helper.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent implements OnInit{
  basket: Need[] = [];

  constructor(private userService:UserHelperService){}

  ngOnInit(): void {
    // this.getFundingBasket(2);
  }

  // getFundingBasket(id:number):void{
  //   this.userService.getFundingBasket(id).subscribe(needs => this.basket = needs);
  // }



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

  // deleteNeed(needId: number): void{
  //   this.userService.removeNeedFromBasket(2,needId).subscribe(user =>{
  //     console.log(user);
  //   })
  // }

}


