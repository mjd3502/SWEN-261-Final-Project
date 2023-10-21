import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserHelperService } from '../user-helper.service';
import { User } from '../User';

//import { User } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  user:User = new User();
  
  logInSection = new FormGroup(
    {
      username: new FormControl('',[Validators.required]),
    }
  )

  constructor(private router:Router,private userService:UserHelperService){
  }


  // ngOnInit(): void {
  //   throw new Error('Method not implemented.');
  // }



  changeRoute(url:string){
    this.router.navigate([url])
  }


  
  login(){
    const username = this.logInSection.get("username")?.value;
    console.log(username);
    
    if(username === 'admin'){
      this.changeRoute('/adminDashboard')
    }else if(username && typeof username === 'string'){
      this.user.setUsername(username);
      this.userService.createUser(this.user).subscribe(us=>{
        this.changeRoute('/helperDashboard')
        return us;
      }); 
    }

  }
}
