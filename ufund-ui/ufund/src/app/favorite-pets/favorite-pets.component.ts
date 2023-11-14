import { Component,OnInit } from '@angular/core';
import { Pet } from '../Pet';
import { PetService } from '../pet.service';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { User } from '../User';
import { BehaviorSubject } from 'rxjs';
import { FavoritePets } from '../FavoritePets';
import { FavoritePetsService } from '../favorite-pets.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-favorite-pets',
  templateUrl: './favorite-pets.component.html',
  styleUrls: ['./favorite-pets.component.css']
})
export class FavoritePetsComponent implements OnInit{
  favorites: Map<number,Pet> = new Map<number, Pet>();
  username!:string;
  user!:User;
  p:number = 1;

  constructor(
    private petService: PetService,
    private favoritePetsService:FavoritePetsService,
    private currentUser:CurrentUserService,
    private location:Location,
    private router: Router
  ){}

  goBack():void{
    return this.location.back()
  }

  ngOnInit(): void {
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.user = user;
        this.username = user.getUsername();
      }
    })

    this.getFavoritePets(this.username);

  }

  getFavoritePets(name:string):void{
    this.favoritePetsService.getFavoritePets(name).subscribe(pets => this.favorites = pets);
  }
  
  functionAddPet(pet: Pet): void{
    this.favoritePetsService.addPetToFavoritePets(this.username,pet).subscribe(user =>{
      console.log(user);
    })
  }

  deletePet(petId: number): void{
    this.favoritePetsService.removePetFromFavoritePets(this.username,petId).subscribe(user =>{
      console.log(user);
      this.getFavoritePets(this.username);
    })
  }

}
