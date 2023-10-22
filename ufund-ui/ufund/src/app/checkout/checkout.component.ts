import { ChangeDetectorRef, Component,OnInit } from '@angular/core';
import { Need } from '../Need';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { Location } from '@angular/common';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent implements OnInit{
  basket: Need[] = [];
  username!:string

  user!:User;


  constructor(
    private userService:UserHelperService,
    private currentUser:CurrentUserService,
    private location:Location
    // private cd:ChangeDetectorRef
  ){}

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.user = user;
        this.username = user.getUserName();
      }
    })

    this.getFundingBasket(this.username);

  }

  goBack():void{
    return this.location.back()
  }


  getFundingBasket(name:string):void{
    this.userService.getFundingBasket(name).subscribe(needs => this.basket = needs);
  }


  calculateTotal(): void{ 
    let total = 0;
    for (let needs of this.basket) {
      total += needs.cost;
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

  deleteNeed(needId: number): void{
    this.basket = this.basket.filter(need => need .id != needId)
    this.userService.removeNeedFromBasket(this.username,needId).subscribe(user =>{
      
      // this.cd.detectChanges();
      console.log(user);
    })
  }

}


