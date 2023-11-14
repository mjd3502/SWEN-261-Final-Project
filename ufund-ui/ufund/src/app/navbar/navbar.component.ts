import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUserService } from '../current-user.service';
import { faArrowRightFromBracket, faBasketShopping, faHeart } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  username!:string;
  constructor(
    private router:Router,
    private currentUser:CurrentUserService
    ){}
    
  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.username = user.getUsername();
      }
    })
    if(this.username == null){  
      this.router.navigate(['/home']);
    }
    
  }

  shoppingBasket= faBasketShopping;
  logOutLogo = faArrowRightFromBracket;
  favoritesLogo = faHeart;

  iconClicked(){
    this.router.navigate(["/funding-basket"])
    console.log("checkbox clicked")
  }

  favoritesClicked(){
    this.router.navigate(["/favorite-pets-page"])
    console.log("favorites clicked")
  }

  redirectToPage(){
    this.router.navigate(["/home"])
  
  }

  logOut() {
    
    Swal.fire({
      title:"Ready to leave?",
      text:"Click below if you are ready to end your current session.",
      showCancelButton:true,
      showConfirmButton:true,
      confirmButtonText:"Log out",

    }).then((result =>{

      if(result.isConfirmed){
        this.redirectToPage();
        console.log("log out")
      }
    
    }))
    
    console.log("button working")
    
  }
}
