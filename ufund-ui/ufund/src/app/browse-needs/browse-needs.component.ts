import { Component,OnInit } from '@angular/core';
import {Need} from '../Need';
import { NeedsService } from '../needs.service';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-browse-needs',
  templateUrl: './browse-needs.component.html',
  styleUrls: ['./browse-needs.component.css']
})
export class BrowseNeedsComponent implements OnInit{
  needs: Need[] = [];

  constructor(
    private needsService: NeedsService,
    private userService:UserHelperService,
    private currentUser:CurrentUserService
    ){}

  userName!:string ;


  user:BehaviorSubject<User | null> = this.currentUser.getCurrentUser();


  //Retrieves all needs using the service
  getNeeds(): void{
    this.needsService.getEntireNeedsCupboard().subscribe(needs => this.needs = needs)
  }

  //On intialization, create proper list of needs
  ngOnInit(): void{
    this.getNeeds();

    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.userName = user.getUserName();
      }
    })
  }

  number = 7

  // adds the need to user's basket
  functionAddNeed(need: Need): void{
    this.userService.addNeedToBasket(this.userName,need).subscribe(user =>{
      console.log(user);
    })
  }

}
