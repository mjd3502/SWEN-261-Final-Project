import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Pet } from '../Pet';
import { PetService } from '../pet.service';

@Component({
  selector: 'app-update-pet',
  templateUrl: './update-pet.component.html',
  styleUrls: ['./update-pet.component.css']
})
export class UpdatePetComponent implements OnInit {
  pet!:Pet;

  constructor(private petService:PetService,
    private route:ActivatedRoute,
    private router:Router
    ){}
  
  ngOnInit(): void {
    this.getPet();
  }

  getPet():void{
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.petService.getPetbyId(id).subscribe(pet =>
      this.pet = pet
    )
  }

  onSubmit(){
    if(this.pet){
      this.petService.updatePet(this.pet).subscribe(response =>{
        this.router.navigate(['/adminPetPage'])
        console.log(response);
      })
    }
    
  }
}