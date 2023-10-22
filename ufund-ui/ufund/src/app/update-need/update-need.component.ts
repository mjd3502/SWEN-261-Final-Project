import { Component } from '@angular/core';
import { NeedsService } from '../needs.service';
import { Need } from '../Need';

@Component({
  selector: 'app-update-need',
  templateUrl: './update-need.component.html',
  styleUrls: ['./update-need.component.css']
})
export class UpdateNeedComponent {


  constructor(private cupboardService:NeedsService){
  }

  updateNeed(need:Need):void{
    
  }



}
