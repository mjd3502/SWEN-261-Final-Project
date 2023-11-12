import { Component,OnInit } from '@angular/core';
import { Pet } from '../Pet';
import { PetService } from '../pet.service';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { BehaviorSubject } from 'rxjs';
import { FavoritePets } from '../FavoritePets';
import { FavoritePetsService } from '../favorite-pets.service';

@Component({
  selector: 'app-browse-pets',
  templateUrl: './browse-pets.component.html',
  styleUrls: ['./browse-pets.component.css']
})
export class BrowsePetsComponent implements OnInit{
  pet: Pet[] = [];

  constructor(
    private petService: PetService,
    private favoritePetsService:FavoritePetsService,
    private currentUser:CurrentUserService
  ){}

  userName!:string;

  getPets(): void{
    this.petService.getAllPets().subscribe(pet => this.pet = pet)
    
  }

  ngOnInit(): void{
    this.getPets();
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.userName = user.getUsername();
      }
    })
    
  }
  
  functionAddPet(pet: Pet): void{
    this.favoritePetsService.addPetToFavoritePets(this.userName,pet).subscribe(user =>{
      console.log(user);
    })
  }

}
