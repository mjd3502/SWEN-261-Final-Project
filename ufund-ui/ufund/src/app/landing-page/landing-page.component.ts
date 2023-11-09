import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faDog, faThumbTack } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

  faDog = faDog

  isAdmin = true

  constructor(private router:Router){

  }

  redirectToLogin(){
    this.router.navigate(["/login"])
  }
}
