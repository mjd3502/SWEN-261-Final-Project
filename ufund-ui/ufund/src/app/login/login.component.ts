import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserHelperService } from '../user-helper.service';
import { User } from '../User';
import { CurrentUserService } from '../current-user.service';
import { faDog, faThumbTack } from '@fortawesome/free-solid-svg-icons';
import { FundingBasketService } from '../funding-basket.service';
import { FundingBasket } from '../FundingBasket';
import { FavoritePetsService } from '../favorite-pets.service';
import { FavoritePets } from '../FavoritePets';

//import { User } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  user!:User;
  fundingBasket:FundingBasket = new FundingBasket();
  favoritesList: FavoritePets = new FavoritePets();
  
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
    private fundingBasketService:FundingBasketService,
    private favPetsService :FavoritePetsService
    
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

    }else if(username && typeof username === 'string'){
        this.user = new User(username);
        this.userService.createUser(this.user).subscribe(us=>{
        this.currentUser.setCurrentUser(this.user);
      });

      //creates a fundingbasket for a user of a given name
      //allows user to enter items into basic and have it saved
      this.fundingBasket.setUsername(username);
      this.fundingBasketService.createFundingBasket(this.fundingBasket).subscribe(basket =>{
        console.log(basket);
      })

      //creates a favorites list for the user with their name so it
      //can be accessed next time they log in
      this.favoritesList.setUsername(username);
      this.favPetsService.createFavoritePets(this.favoritesList).subscribe(response =>{
        console.log(response);
      })

      this.changeRoute('/helperDashboard')
    }

   
  }
}
