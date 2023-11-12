import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NeedsService } from '../needs.service';
import { Router } from '@angular/router';
import {Need} from '../Need';
import Swal from 'sweetalert2';

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

        

        Swal.fire({
          title: "Need created :)",
          icon: "success"
        });
        
        this.router.navigate(['/upload/need/' + (String)(response.id)])
        
        console.log(response);
      },(error) =>{
        console.error('An error occurred:', error);
        if(error.status == 400){
          this.errorMesage = "Please enter a valid type of need";
          Swal.fire({
            title: "Please enter a valid type of need",
            icon: "error"
          });
        }else{
          this.errorMesage = "Please fill out every input";
          Swal.fire({
            title: "Please fill out every input",
            icon: "error"
          });
        }
      }
      )
    }
    
  }

}
