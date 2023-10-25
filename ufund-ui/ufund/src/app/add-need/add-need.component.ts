import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NeedsService } from '../needs.service';
import { Router } from '@angular/router';
import {Need} from '../Need';

@Component({
  selector: 'app-add-need',
  templateUrl: './add-need.component.html',
  styleUrls: ['./add-need.component.css']
})


export class AddNeedComponent {
  // Need: Need = {id:0, name:"null",quantity:0,description:"null",cost:0,type:"goods"};
  Need: Need =  new Need();
  //default values for all the criteria for need
  // id: Number = 0;
  // name: string = "null";
  // quantity: Number = 0;
  // description: string = "null";
  // cost: Number = 0;
  // type: string = "null"
  // need: Need = {id:10,name:"Carla",quantity:10,description:"example",cost:10,type:"goods"}

  constructor(private needsService: NeedsService, private router: Router){
    
  }
  
  // submitValues(id: HTMLInputElement,name:string,quantity:number,description:string,cost:number,type:string){
  //   //let newNeed: Need = {id:id.value,name:name, quantity:quantity, description:description,cost:cost,type:type}
    
  //   this.needsService.createNeed(newNeed).subscribe(user => {
  //     console.log(user);
  //   })
  // }

  onSubmit(){
    if(this.Need){
      this.needsService.createNeed(this.Need).subscribe(response =>{
        this.router.navigate(['/admin-browse'])
        console.log(response);
      })
    }
    
  }

}
