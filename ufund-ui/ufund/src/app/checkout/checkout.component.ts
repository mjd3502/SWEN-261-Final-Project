import { Component } from '@angular/core';
import { Need } from '../Need';
import { FundingBasketService } from '../funding-basket.service';
import { CurrentUserService } from '../current-user.service';
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
    private updateQuantity: FlagService,
    private needsService:NeedsService
  ){}

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{  
      if (user) {
        this.user = user;
        this.username = user.getUsername();
        // console.log(this.user.getUsername())
      }   
     
    })

  }
  
  getFundingBasket():void{
    this.fundingBasketService.getFundingBasket(this.username).subscribe((fundingBasket) => {
      console.log(fundingBasket);
      console.log(fundingBasket.get(12));
    });
  }

  submitOrder():void{
    
    this.getFundingBasket();
    // this.getFundingBasket()
    // console.log(this.basket);
    // for (var need of this.basket.values()) {
    //   this.needsService.updateNeed(need).subscribe(
    //     need =>{
    //       console.log(need)
    //     }
    //   )
    // }


  }
  checkoutNeeds():void{
    this.fundingBasketService.clearBasket(this.username).subscribe(
      fundingbasket => this.basket = fundingbasket
    )
  }


}
