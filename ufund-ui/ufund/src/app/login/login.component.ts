import { Component } from '@angular/core';

//import { User } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  //constructor(private needsService: NeedsService) { }
  //model: user = new User('');
  loggedIn = false;
  login(): void{
      this.loggedIn = true;
  }
}
