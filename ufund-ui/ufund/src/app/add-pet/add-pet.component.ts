import { Component } from '@angular/core';
import { PetService } from '../pet.service';
import { Router } from '@angular/router';
import { Pet } from '../Pet';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-pet',
  templateUrl: './add-pet.component.html',
  styleUrls: ['./add-pet.component.css']
})

export class AddPetComponent {
 
  pet: Pet =  new Pet();

  constructor(private petService: PetService, private router: Router){}
  
  onSubmit(){
    if(this.pet){
      this.petService.createPet(this.pet).subscribe(response =>{
        console.log(response);

        if(response.id){
          Swal.fire({
            title: "Listing created :)",
            icon: "success"
          });

          this.router.navigate(['/upload/pet/' + (String)(response.id)])
        
        }
                
      },(error)=>{
        Swal.fire({
          title: "Please enter valid information",
          icon: "error"
        });
      });
    }

  }
}
