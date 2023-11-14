import { Component,OnInit } from '@angular/core';
import {Need} from '../Need';
import { NeedsService } from '../needs.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { BehaviorSubject } from 'rxjs';
import { FundingBasket } from '../FundingBasket';
import { FundingBasketService } from '../funding-basket.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-browse-needs',
  templateUrl: './browse-needs.component.html',
  styleUrls: ['./browse-needs.component.css']
})
export class BrowseNeedsComponent implements OnInit{
  needs: Need[] = [];
 p:number = 1;

  disableAddNeed:boolean = false;

  constructor(
    private needsService: NeedsService,
    private fundingBasketService:FundingBasketService,
    private currentUser:CurrentUserService,
    ){}

  userName!:string;


  getNeeds(): void{
    this.needsService.getEntireNeedsCupboard().subscribe(needs => {
      this.needs = needs;
    }
    )
    console.log(this.disableAddNeed);
    
  }


  ngOnInit(): void{
    this.getNeeds();
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.userName = user.getUsername();
      }
    })  
    
  }

 
  functionAddNeed(need: Need): void{
    this.fundingBasketService.addNeedToBasket(this.userName,need).subscribe(user =>{
      console.log(user);
  })
    if(need.quantity == 0){
      Swal.fire({
        title: "This need has been fullfilled",
        icon: "error"
      });
    }
    Swal.fire({
      title: "Added to basket",
      icon: "success"
    });
  }

}
