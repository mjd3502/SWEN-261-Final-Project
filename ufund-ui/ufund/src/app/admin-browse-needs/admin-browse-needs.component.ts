import { Component } from '@angular/core';
import {Need} from '../Need';
import { NeedsService } from '../needs.service';

@Component({
  selector: 'app-admin-browse-needs',
  templateUrl: './admin-browse-needs.component.html',
  styleUrls: ['./admin-browse-needs.component.css']
})
export class AdminBrowseNeedsComponent {
  needs: Need[] = [];

  constructor(private needsService: NeedsService){}

  //retrieves all needs using the service
  getNeeds(): void{
    this.needsService.getEntireNeedsCupboard().subscribe(needs => this.needs = needs)
  }

  //on intialization creates a list of needs
  ngOnInit(): void{
    this.getNeeds();
  }

 
  //removes need from cupboard
  deleteNeed(needId: number): void{
    this.needs = this.needs.filter(need => need.id != needId)
    this.needsService.deleteNeedbyId(needId).subscribe(user => {
      console.log(user);
    })
  }

}
