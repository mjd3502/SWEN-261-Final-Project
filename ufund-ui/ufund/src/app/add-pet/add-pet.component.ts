import { Component } from '@angular/core';
import { PetService } from '../pet.service';
import { Router } from '@angular/router';
import { Pet } from '../Pet';

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
        this.router.navigate(['/adminPetPage'])
        console.log(response);
      })
    }
  }
}
