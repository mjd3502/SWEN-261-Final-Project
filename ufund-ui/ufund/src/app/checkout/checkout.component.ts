import { Component } from '@angular/core';
import { Need } from '../Need';
import { FundingBasketService } from '../funding-basket.service';
import { CurrentUserService } from '../current-user.service';
import { Router } from '@angular/router';
import { CheckoutService } from '../checkout.service';
import { User } from '../User';
import { NeedsService } from '../needs.service';
import { FlagService } from '../flag.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  
  basket: Map<number,Need> = new Map<number, Need>();
  username!:string;
  user!:User

  constructor(
    private fundingBasketService:FundingBasketService,
    private currentUser:CurrentUserService,
    private router:Router,
    private updateQuantity: FlagService,
    private needsService:NeedsService
  ){}

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{  
      if (user) {
        this.user = user;
        this.username = user.getUsername();
        console.log(this.user.getUsername())
      }   
      this.getFundingBasket(this.username)
      // console.log(this.updateQuantity.getUpdateQuantity() + " new quantities ");   
    })

  }
  
  getFundingBasket(username:string):void{
    this.fundingBasketService.getFundingBasket(username).subscribe(fundingBasket => {
      console.log('Funding Basket data:', fundingBasket);
      let updateNeedQuantity = this.updateQuantity.getUpdateQuantity();
      var i = 0;
      for (var need of fundingBasket.values()) {
        need.quantity = updateNeedQuantity[i];
        console.log(need.quantity);
        i++;
      }
      this.basket = fundingBasket
    });
  }

  submitOrder():void{
    for (var need of this.basket.values()) {
      this.needsService.updateNeed(need).subscribe(
        need =>{
          console.log(need)
        }
      )
    }
  }
  checkoutNeeds():void{
    this.fundingBasketService.clearBasket(this.username).subscribe(
      fundingbasket => this.basket = fundingbasket
    )
  }


}
