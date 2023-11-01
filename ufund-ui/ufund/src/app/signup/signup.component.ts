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
  signUpSection = new FormGroup(
    {username: new FormControl('',[Validators.required])}
  )
  faDog = faDog;
  constructor(
    private router:Router,
    private userService:UserHelperService,
    private currentUser:CurrentUserService
  ){}
  
  signup(){
    const username = this.signUpSection.get("username")?.value;
    console.log(username);

    //check given username against all current and admin

    //if new name, create new user

    //if not new, clear box
  }
}
