import { Component,OnInit } from '@angular/core';
import { Pet } from '../Pet';
import { PetService } from '../pet.service';
import { CurrentUserService } from '../current-user.service';
import { FavoritePetsService } from '../favorite-pets.service';
import { faHeart } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-browse-pets',
  templateUrl: './browse-pets.component.html',
  styleUrls: ['./browse-pets.component.css']
})
export class BrowsePetsComponent implements OnInit{
  pets: Pet[] = [];

  favoritesLogo = faHeart;

  constructor(
    private petService: PetService,
    private favoritePetsService:FavoritePetsService,
    private currentUser:CurrentUserService
  ){}

  userName!:string;

  getPets(): void{
    this.petService.getAllPets().subscribe(pets => this.pets = pets)
    
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
