import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faDog, faThumbTack } from '@fortawesome/free-solid-svg-icons';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

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
