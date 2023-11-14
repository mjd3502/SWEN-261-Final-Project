import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserHelperService } from '../user-helper.service';
import { User } from '../User';
import { CurrentUserService } from '../current-user.service';
import { faDog, faThumbTack } from '@fortawesome/free-solid-svg-icons';
import { FundingBasketService } from '../funding-basket.service';
import { FundingBasket } from '../FundingBasket';
import Swal from 'sweetalert2';
import { FavoritePets } from '../FavoritePets';
import { FavoritePetsService } from '../favorite-pets.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  user!:User;
  fundingBasket:FundingBasket = new FundingBasket();
  favoriteList:FavoritePets = new FavoritePets();
  exists!:Boolean | undefined;

  signUpSection = new FormGroup(
    {username: new FormControl('',[Validators.required])}
  )
  
  faDog = faDog;
  constructor(
    private router:Router,
    private userService:UserHelperService,
    private currentUser:CurrentUserService,
    private fundingBasketService:FundingBasketService,
    private favoritePets:FavoritePetsService
    ){
  }
  changeRoute(url:string){
    this.router.navigate([url])
  }

  async userExists(username:string): Promise<void>{
    const userexist = await this.userService.doesUserExist(username).toPromise()
    this.exists =userexist;
    console.log(this.exists)
  }


  async signup(){
    const username = this.signUpSection.get("username")?.value;
    console.log(username);
    if(username && typeof username === 'string'){
        await this.userExists(username);
        console.log(this.exists)

        if(this.exists){
          Swal.fire({
            title: "Username is already taken",
            text:"Please choose a new username",
            icon: "error"
        });
        
        }else{
          this.user = new User(username);
          this.userService.createUser(this.user).subscribe(us=>{
            this.currentUser.setCurrentUser(this.user);
            console.log("creat user carla ")
            
          })
          this.fundingBasket.setUsername(username);
          this.fundingBasketService.createFundingBasket(this.fundingBasket).subscribe(basket =>{
          console.log(basket);
          })
          this.favoriteList.setUsername(username);
          this.favoritePets.createFavoritePets(this.favoriteList).subscribe(favoriteList =>{
            console.log(favoriteList)
          })
          Swal.fire({
            title: "Account created",
            text:"Log into your account now",
            icon: "success"
          });
          this.changeRoute('/login')
        }
    }
  }
}
