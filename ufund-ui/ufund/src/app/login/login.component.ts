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


  
  login(){
    const username = this.logInSection.get("username")?.value;
    console.log(username);
    

    if(username === 'admin'){
      
      this.changeRoute('/adminDashboard')

    }else if(username  && typeof username === 'string'){
      /** 
        this.user = new User(username);
        this.userService.createUser(this.user).subscribe(us=>{
        this.currentUser.setCurrentUser(this.user);
      });
      this.fundingBasket.setUsername(username);
      this.fundingBasketService.createFundingBasket(this.fundingBasket).subscribe(basket =>{
        console.log(basket);
      })
      
     var exists;
     this.userService.doesUserExist(username)
        .subscribe(doesexists => exists = doesexists)
        */
     console.log("test call of usereExists::");
     console.log(this.userExists(username));
     console.log("if::");
     if(this.userExists(username)){
        this.userService.getUserByName(username)
          .subscribe(user => {
            console.log("user fetched:");
            console.log(user);
            this.user = user;
            this.currentUser.setCurrentUser(this.user);
          });
        
        this.fundingBasketService.getFundingBasketObject(this.user.getUsername())
          .subscribe(fundingBask => this.fundingBasket = fundingBask);
          
        console.log(this.fundingBasket);
          
        this.changeRoute('/helperDashboard');
     } else {
      this.signUpRedirect();
     }
    }

    
  }
  userExists(username:string): boolean{
    console.log("userExists:");
    console.log(username);
    
       var exists;
      this.userService.doesUserExist(username).subscribe(doesexist => {
        console.log("userExists: does exist before(inside subscribe)");
        console.log(doesexist);

        exists = doesexist;

        console.log(exists);

        return exists;
      })
      console.log("exists after subscribe:")
      console.log(exists);

      if(exists){return exists;}
      console.log('didnt work')
      return false;
  }
  signUpRedirect(){
    this.changeRoute('/signup')
  }
}
