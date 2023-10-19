import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  
  constructor(private router:Router){

  }

  changeRoute(url:string){
    this.router.navigate([url])
  }

  login(username:string){

    if(username === 'admin'){
      this.changeRoute('/helperDashboard')
    }else{
      
      this.changeRoute('/checkout')
    }

  }

}
