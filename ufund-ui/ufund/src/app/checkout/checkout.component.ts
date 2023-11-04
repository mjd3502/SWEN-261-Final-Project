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
  basket: Map<number,Need> = new Map();
  username!:string;
  fundingBasket!: Need[];

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

  goBack():void{
    return this.location.back()
  }

  
  getFundingBasket(name:string):void{
    this.fundingBasketService.getFundingBasket(name).subscribe(needs => 
    this.basket = needs
    );
    console.log(this.fundingBasket);
  }

  


  // calculateTotal(): void{ 
  //   let total = 0;
  //   for (let needs of this.basket) {
  //     total += needs.cost;
  //   }
  // }

  addQuantity(need: Need): void{
    need.quantity += 1;
  }

  subtractQuantity(need: Need): void{
    if (need.quantity > 0){ 
      need.quantity -= 1;
    }
  }

  deleteNeed(needId: number): void{
    this.basket.delete(needId);
    this.fundingBasketService.removeNeedFromBasket(this.username,needId).subscribe(user =>{
      console.log(user);
    })
  }

}


