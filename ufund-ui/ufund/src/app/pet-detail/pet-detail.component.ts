import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Pet } from '../Pet';
import { PetService } from '../pet.service';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { BehaviorSubject } from 'rxjs';
import { User } from '../User';
import { FavoritePetsService } from '../favorite-pets.service';

@Component({
  selector: 'app-needs-detail',
  templateUrl: './pet-detail.component.html',
  styleUrls: ['./pet-detail.component.css']
})
export class PetDetailComponent implements OnInit {

  pet!:Pet; // Initialize the property to null

  constructor(
    private route: ActivatedRoute,
    private petService: PetService,
    private location: Location,
    private favoritePetsService:FavoritePetsService,
    private currentUser:CurrentUserService
  ) { 

  }
  username!:string;

  user!: BehaviorSubject<User|null>; 

  ngOnInit(): void {
    this.getPet();

    
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.username = user.getUsername();
      }
    })

  }

  getPet(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')!);
    this.petService.getPetbyId(id)
      .subscribe(pet => this.pet = pet);
  }

  goBack(): void {
    this.location.back();
  }

  functionAddPet(pet: Pet): void{
    this.favoritePetsService.addPetToFavoritePets(this.username,pet).subscribe(user =>{
      console.log(user);
    })
  }
}
