import { Component } from '@angular/core';
import {Need} from '../Need';
import { NeedsService } from '../needs.service';

@Component({
  selector: 'app-browse-needs',
  templateUrl: './browse-needs.component.html',
  styleUrls: ['./browse-needs.component.css']
})
export class BrowseNeedsComponent {
  needs: Need[] = [];

  constructor(private needsService: NeedsService){}

  //Retrieves all needs using the service
  getNeeds(): void{
    this.needsService.getEntireNeedsCupboard().subscribe(needs => this.needs = needs)
  }

  //On intialization, create proper list of needs
  ngOnInit(): void{
    this.getNeeds();
  }

  //adds the need to user's basket
  addToBasket(need: Need): void{
    //add given need to the checkout basket 
  }
}
