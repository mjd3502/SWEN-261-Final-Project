import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faBasketShopping } from '@fortawesome/free-solid-svg-icons';
import { faArrowRightFromBracket } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-helper-dashboard',
  templateUrl: './helper-dashboard.component.html',
  styleUrls: ['./helper-dashboard.component.css']
})
export class HelperDashboardComponent {

  constructor(private router:Router){}


  shoppingBasket= faBasketShopping;
  logOutLogo = faArrowRightFromBracket;

  iconClicked(){
    this.router.navigate(["/checkout"])
    console.log("checkbox clicked")
  }

  redirectToPage(){
    this.router.navigate(["login"])
  }

  logOut(){
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
