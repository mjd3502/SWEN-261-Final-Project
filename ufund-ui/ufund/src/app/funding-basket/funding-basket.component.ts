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
  updateNeedQuantity!:boolean
  newQuantities:Need[] = []
  

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


  quantityArray(need: Need): number[]{
    let quantity = need.quantity;
    const array = [];
    for(var i=0;i<=quantity;i++){
      array.push(i);
    }
    return array
  }

  ChooseQuantity(e:any,need:Need){
    const selectedValue = e.target.value;
    const newQuantity = need.quantity - selectedValue;
    let newNeed = new Need()
    newNeed.id = need.id
    newNeed.name = need.name
    newNeed.description = need.description
    newNeed.quantity = newQuantity
    newNeed.cost = need.cost
    newNeed.type = need.type
    this.newQuantities.push(newNeed)
    this.updateQuantity.setUpdateQuantity(this.newQuantities);
  }


  checkoutNeeds():void{
    this.router.navigate(['/checkout'])
  }
}


