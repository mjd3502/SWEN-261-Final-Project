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
  donationValues: { [key: string]: number } = {};
  username!:string;
  user!:User
  totalAmount = 0;


  constructor(
    private fundingBasketService:FundingBasketService,
    private currentUser:CurrentUserService,
    private donationService: FlagService,
    private needsService:NeedsService,
  ){}

  

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{  
      if (user) {
        this.user = user;
        this.username = user.getUsername();
      }   
     
    })
    // this.totalAmount = this.totalAmountService.getTotalAmount();

  }
  submitOrder():void{
    this.donationValues = this.donationService.getUpdateQuantity();
    for(const[key,value] of Object.entries(this.donationValues)){
      console.log("needs surplussss")
      let id = Number(key);
      this.needsService.helperDonation(id,value).subscribe(
        need =>{
          console.log(need);
        }
      )
    }
  }


  checkoutNeeds():void{
    this.submitOrder()
    this.fundingBasketService.clearBasket(this.username).subscribe(
      fundingbasket => this.basket = fundingbasket
    )
  
    Swal.fire({
      title: "Order completed!",
      text: "We appreciate your contribution",
      icon: "success",
      color:"cornflowerblue"
    });
  }

  

}
