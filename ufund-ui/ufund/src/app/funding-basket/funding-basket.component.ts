import { ChangeDetectorRef, Component,OnInit } from '@angular/core';
import { Need } from '../Need';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { Location } from '@angular/common';
import { FundingBasketService } from '../funding-basket.service';
import { Router } from '@angular/router';
import { NeedsService } from '../needs.service';
import { FlagService } from '../flag.service';


@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrls: ['./funding-basket.component.css']
})


export class FundingBasketComponent implements OnInit{
  basket: Map<number,Need> = new Map<number, Need>();
  username!:string;
  user!:User;
  donationValues: { [key: string]: number } = {};
  // newQuantities:Need[] = []
  needsToCheckOut:Need[] =[]
  totalCostOfEachNeed:number[] = []
  
  total = 0;

  constructor(
    private fundingBasketService:FundingBasketService,
    private currentUser:CurrentUserService,
    private router:Router,
    private donationService: FlagService,
  ){}

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{
      
    if (user) {
        this.user = user;
        this.username = user.getUsername();
        console.log(this.user.getUsername())
        this.getFundingBasket(this.username);
        console.log(this.basket)
      }
    })

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

  onDonationChange(needKey: number, event: any) {
    if (event.target instanceof HTMLInputElement) {
      this.donationValues[needKey] = Number(event.target.value);
    }
  }


  proceedToCheckOut(){
    this.donationService.setUpdateQuantity(this.donationValues);
    console.log("need new quantities")
    console.log(this.donationValues);
    this.router.navigate(['/checkout'])
  }


  totalQuantityOfNeed():void{
    var total = 0;
    for(var need of this.needsToCheckOut){
      total = need.cost * need.quantity
    }
    this.totalCostOfEachNeed.push(total)
  }

  basketTotal():void{
    this.total = 0;
    for(var totalofNeed of this.totalCostOfEachNeed){
      this.total += totalofNeed;
    }
    console.log(this.total)
  }


  checkoutNeeds():void{
    this.router.navigate(['/checkout'])
  }
}


