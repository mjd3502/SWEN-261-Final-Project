import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

//import { User } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  
  logInSection = new FormGroup(
    {
      username: new FormControl('',[Validators.required]),
    }
  )

  constructor(private router:Router){

  }


  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }



  changeRoute(url:string){
    this.router.navigate([url])
  }

  login(){
    const username = this.logInSection.get("username")?.value;
    console.log(username);
    
    if(username === 'admin'){
      this.changeRoute('/adminrDashboard')
    }else{

      this.changeRoute('/helperDashboard')
    }

  }
}
