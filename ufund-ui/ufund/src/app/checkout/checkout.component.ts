import { Component } from '@angular/core';
import { Need } from '../Need';
import { FundingBasketService } from '../funding-basket.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { NeedsService } from '../needs.service';
import { FlagService } from '../flag.service';
import { map } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  basket: Map<number,Need> = new Map<number, Need>();
  updatedNeeds!:Need[]
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
      }   
     
    })

  }
  

  submitOrder():void{
    this.updatedNeeds = this.updateQuantity.getUpdateQuantity();
    for(var need of this.updatedNeeds){
      this.needsService.updateNeed(need).subscribe(
        (need) =>{
          console.log(need)
        }
      )
    }
  }


  checkoutNeeds():void{
    this.fundingBasketService.clearBasket(this.username).subscribe(
      fundingbasket => this.basket = fundingbasket
    )
    this.submitOrder()
  
    Swal.fire({
      title: "Order completed!",
      text: "We appreciate your contribution",
      icon: "success",
      color:"cornflowerblue"
    });
  }

  

}
