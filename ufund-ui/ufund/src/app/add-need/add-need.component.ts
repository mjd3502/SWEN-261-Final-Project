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
 
  Need: Need =  new Need();

  errorMesage = " ";


  constructor(private needsService: NeedsService, private router: Router){
    
  }
  

  onSubmit(){
    if(this.Need){
      this.needsService.createNeed(this.Need).subscribe(response =>{
        this.router.navigate(['/adminDashboard'])
        console.log(response);
      },(error) =>{
        console.error('An error occurred:', error);

        if(error.status == 400){
          this.errorMesage = "Please enter a valid type of need";
        }else{
          this.errorMesage = "Please fill out every input";
        }

      }
      )
    }
    
  }

}
