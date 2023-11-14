import { ChangeDetectorRef, Component,OnInit } from '@angular/core';
import { Need } from '../Need';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { FundingBasketService } from '../funding-basket.service';
import { Router } from '@angular/router';
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
  needIndividualCost: { [key: string]: number } = {};
  p:number = 1;
  needsToCheckout: Map<string,number> = new Map<string, number>();
  totalAmount = 0;

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

  onDonationChange(Need:Need, event: any) {
    let needKey = String(Need.id);
    if (event.target instanceof HTMLInputElement) {
      this.donationValues[needKey] = Number(event.target.value);
      this.needsToCheckout.set(needKey,Need.cost);
    }

    this.calculateIndividualTotal(Need.cost,needKey);
    this.calculateTotalAmount();
  }

  calculateIndividualTotal(cost:number,needKey:string):void{
    let total = cost * this.donationValues[needKey]
    this.needIndividualCost[needKey] = total;
  }

  calculateTotalAmount():void{
    this.totalAmount = 0;
    for (const value of Object.values(this.needIndividualCost)) {
      this.totalAmount += value;
  }
  }




  proceedToCheckOut(){
    this.donationService.setUpdateQuantity(this.donationValues);
    this.router.navigate(['/checkout'])
  }


  checkoutNeeds():void{
    this.router.navigate(['/checkout'])
  }
}


