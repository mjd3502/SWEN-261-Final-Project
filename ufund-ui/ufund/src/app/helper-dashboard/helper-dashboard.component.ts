import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faBasketShopping } from '@fortawesome/free-solid-svg-icons';
import { faArrowRightFromBracket } from '@fortawesome/free-solid-svg-icons';

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

  logOut(){
    console.log("button working")
  }
}
