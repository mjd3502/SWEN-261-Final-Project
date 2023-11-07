import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserHelperService } from '../user-helper.service';
import { User } from '../User';
import { CurrentUserService } from '../current-user.service';
import { faDog, faThumbTack } from '@fortawesome/free-solid-svg-icons';
import { FundingBasketService } from '../funding-basket.service';
import { FundingBasket } from '../FundingBasket';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  user!:User;
  fundingBasket:FundingBasket = new FundingBasket();

  signUpSection = new FormGroup(
    {username: new FormControl('',[Validators.required])}
  )
  faDog = faDog;
  constructor(
    private router:Router,
    private userService:UserHelperService,
    private currentUser:CurrentUserService,
    private fundingBasketService:FundingBasketService
    ){
  }
  changeRoute(url:string){
    this.router.navigate([url])
  }
  signup(){
    const username = this.signUpSection.get("username")?.value;
    console.log(username);

    //check given username against all current and admin
    if(username && typeof username === 'string'){
      if(!this.userExists(username)){
        this.user = new User(username);
        this.userService.createUser(this.user).subscribe(us=>{
          this.currentUser.setCurrentUser(us);
        });
        this.fundingBasket.setUsername(username);
        this.fundingBasketService.createFundingBasket(this.fundingBasket).subscribe(basket =>{
        console.log(basket);
        })
        this.changeRoute('/helperDashboard')
      }
    }
    //if not new, clear box
  }
  userExists(username:string): boolean{
    this.userService.doesUserExist(username).subscribe(doesexist => {
      return doesexist
    })
    return false;
}
}