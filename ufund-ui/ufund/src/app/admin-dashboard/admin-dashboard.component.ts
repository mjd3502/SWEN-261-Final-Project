import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { faArrowRightFromBracket, faBasketShopping } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {

  logOutLogo = faArrowRightFromBracket;

  constructor(private router:Router){

  }


  redirectToPage(url:string){
    this.router.navigate([url])
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
        this.redirectToPage('login');
        console.log("log out")
      }
    
    }))
    
    console.log("button working")


  }

}
