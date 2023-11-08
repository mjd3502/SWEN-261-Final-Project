import { Component } from '@angular/core';

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
    private fundingBasketService:FundingBasketService,
    private currentUser:CurrentUserService,
    private location:Location
  ){}

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{
      console.log(user  + " user1")
      if (user) {
        this.user = user;
        this.username = user.getUsername();
        console.log(this.user.getUsername())
        console.log("helloooooo")

        this.getFundingBasket(this.username);
      }
      console.log("no user")
    })
   

  }

  goBack():void{
    return this.location.back()
  }

  
  getFundingBasket(name:string):void{
    this.fundingBasketService.getFundingBasket(name).subscribe(needs => this.basket = needs);
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
    this.fundingBasketService.removeNeedFromBasket(this.username,needId).subscribe(user =>{
      console.log(user);
    })
  }


}
