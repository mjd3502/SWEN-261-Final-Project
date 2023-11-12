import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserHelperService } from '../user-helper.service';
import { User } from '../User';
import { CurrentUserService } from '../current-user.service';
import { faDog, faThumbTack } from '@fortawesome/free-solid-svg-icons';
import { FundingBasketService } from '../funding-basket.service';
import { FundingBasket } from '../FundingBasket';
import { LoginService } from '../login.service';
import Swal from 'sweetalert2';

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

  loginHeader!: string

  isAdminLogin = true;
  
  
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
    private isAdmin:LoginService
    ){
  }


  ngOnInit(): void {
    this.changeHeader()
  }

  changeHeader(){
    let admin = this.isAdmin.getIsAdmin();
    if(admin){
      this.loginHeader = "Admin Login"
      this.isAdminLogin = false;
    }else{
      this.loginHeader = "Helper Login"
      
    }
  }


  changeRoute(url:string){
    this.router.navigate([url])
  }

  async userExists(username:string): Promise<void>{
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
      await this.userExists(username);
      if(this.exists){
          this.user = new User(username);
          this.currentUser.setCurrentUser(this.user);
          this.changeRoute('/helperDashboard');
      } else {
        Swal.fire({
          title: "Username already taken",
          text:"Please create a new account",
          icon: "error"
        });

        this.signUpRedirect();
      }
    }
  }

  signUpRedirect(){
    this.changeRoute('/signup')
  }
}
