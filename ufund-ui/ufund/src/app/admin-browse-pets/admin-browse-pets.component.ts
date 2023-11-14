import { Component } from '@angular/core';
import { Pet } from '../Pet';
import { PetService } from '../pet.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-browse-pets',
  templateUrl: './admin-browse-pets.component.html',
  styleUrls: ['./admin-browse-pets.component.css']
})
export class AdminBrowsePetsComponent {
  pets: Pet[] = [];
  p:number = 1

  constructor(private petService: PetService,
    private route:Router
    ){}

  getPets(): void{
    this.petService.getAllPets().subscribe(pets => this.pets = pets)
  }

  ngOnInit(): void{
    this.getPets();
  }

  deletePet(petId: number): void{
    this.pets = this.pets.filter(pet => pet.id != petId)
    this.petService.deletePetbyId(petId).subscribe(user => {
      console.log(user);
    })
  }
}
