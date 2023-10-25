import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUserService } from '../current-user.service';
import { faArrowRightFromBracket, faBasketShopping } from '@fortawesome/free-solid-svg-icons';
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
    
  }

  shoppingBasket= faBasketShopping;
  logOutLogo = faArrowRightFromBracket;

  iconClicked(){
    this.router.navigate(["/checkout"])
    console.log("checkbox clicked")
  }

  redirectToPage(){
    this.router.navigate(["/login"])
  
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
