import { Component ,OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { faBasketShopping } from '@fortawesome/free-solid-svg-icons';
import { faArrowRightFromBracket } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';
import { CurrentUserService } from '../current-user.service';

@Component({
  selector: 'app-helper-dashboard',
  templateUrl: './helper-dashboard.component.html',
  styleUrls: ['./helper-dashboard.component.css']
})
export class HelperDashboardComponent implements OnInit{

  username!:string;
  constructor(
    private router:Router,
    private currentUser:CurrentUserService
    ){}
  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.username = user.getUserName();
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
