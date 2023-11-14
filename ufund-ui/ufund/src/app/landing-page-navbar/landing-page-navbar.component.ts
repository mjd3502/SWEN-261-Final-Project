import { Component } from '@angular/core';
import { faDog } from '@fortawesome/free-solid-svg-icons';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page-navbar',
  templateUrl: './landing-page-navbar.component.html',
  styleUrls: ['./landing-page-navbar.component.css']
})
export class LandingPageNavbarComponent {


  faDog = faDog


  constructor(private router:Router,
    private isaAdmin:LoginService
    ){

  }

  redirectToLogin(){
    this.router.navigate(["/login"])
    this.isaAdmin.setIsAdmin(false);
  }


  redirectToAdminLogin(){
    this.router.navigate(["/login"])
    this.isaAdmin.setIsAdmin(true);
  }
}

