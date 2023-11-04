import { ChangeDetectorRef, Component,OnInit } from '@angular/core';
import { Need } from '../Need';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { Location } from '@angular/common';
import { FundingBasketService } from '../funding-basket.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent implements OnInit{
  basket: Map<number,Need> = new Map<number, Need>();
  username!:string;
  user!:User;
  

  constructor(
    private fundingBasketService:FundingBasketService,
    private currentUser:CurrentUserService,
    private location:Location
    
  ){}

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.user = user;
        this.username = user.getUsername();
      }
    })
    this.getFundingBasket(this.username);

  }

  
  getFundingBasket(name:string):void{
    this.fundingBasketService.getFundingBasket(name).subscribe(fundingBasket => 
    this.basket = fundingBasket
    );

  }

  deleteNeed(needId: number): void{
    this.fundingBasketService.removeNeedFromBasket(this.username,needId).subscribe(fundingBasket =>
      this.basket = fundingBasket
    );
  }

  updateBasket(needId:number):void{
    if(this.basket.has(needId)){
      console.log(this.basket.get(needId))
      this.basket.delete(needId);
    }else {
      console.error(`Item with ID ${needId} not found in the basket.`);
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

  checkoutNeeds():void{
    this.fundingBasketService.clearBasket(this.username).subscribe(
      fundingbasket => this.basket = fundingbasket
    )
  }

 

}


