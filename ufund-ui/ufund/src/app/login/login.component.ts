import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserHelperService } from '../user-helper.service';
import { User } from '../User';
import { CurrentUserService } from '../current-user.service';
import { faDog, faThumbTack } from '@fortawesome/free-solid-svg-icons';
import { FundingBasketService } from '../funding-basket.service';
import { FundingBasket } from '../FundingBasket';

//import { User } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  user!:User;
  fundingBasket!:FundingBasket;
  exists!:Boolean | undefined;
  
  
  logInSection = new FormGroup(
    {
      username: new FormControl('',[Validators.required]),
    }
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

  async userExists(username:string): Promise<void>{
    console.log("userExists:");
    const userexist = await this.userService.doesUserExist(username).toPromise()
    this.exists =userexist;
    console.log(this.exists)
  }
  
  async login(){
    const username = this.logInSection.get("username")?.value;
    console.log(username);
    

    if(username === 'admin'){
      this.changeRoute('/adminDashboard')

    }else if(username  && typeof username === 'string'){
      console.log("test call of usereExists::");
      console.log("if::");
      await this.userExists(username);
      console.log(this.exists + "hello")
      if(this.exists){
          this.userService.getUserByName(username)
            .subscribe(user => {
              this.user = user;
              console.log(this.user)
              this.currentUser.setCurrentUser(this.user);
          });

          // this.fundingBasketService.getFundingBasketObject(this.user.getUsername())
          // .subscribe(fundingBask => this.fundingBasket = fundingBask);  
          // console.log(this.fundingBasket);
          this.changeRoute('/helperDashboard');


      } else {
        this.signUpRedirect();
      }
    }

    
  }

  
      // console.log("exists after subscribe:")
      // console.log(this.exists);


  signUpRedirect(){
    this.changeRoute('/signup')
  }
}
