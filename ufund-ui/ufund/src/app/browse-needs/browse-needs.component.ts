import { Component } from '@angular/core';
import {Need} from '../Need';
import { NeedsService } from '../needs.service';
import { UserHelperService } from '../user-helper.service';

@Component({
  selector: 'app-browse-needs',
  templateUrl: './browse-needs.component.html',
  styleUrls: ['./browse-needs.component.css']
})
export class BrowseNeedsComponent {
  needs: Need[] = [];

  constructor(private needsService: NeedsService,private userService:UserHelperService){}

  //Retrieves all needs using the service
  getNeeds(): void{
    this.needsService.getEntireNeedsCupboard().subscribe(needs => this.needs = needs)
  }

  //On intialization, create proper list of needs
  ngOnInit(): void{
    this.getNeeds();
  }

  number = 7

  //adds the need to user's basket
  fucntion_add(need: Need): void{
    this.userService.addNeedToBasket(2,need).subscribe(user =>{
      console.log(user);
    })
  }

}
