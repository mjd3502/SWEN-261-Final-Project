import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FlagService {

  private updateQuantity: number[] = [];

  setUpdateQuantity(newQuantites:number[]):void{
    this.updateQuantity = newQuantites;
  }

  getUpdateQuantity():number[]{
    return this.updateQuantity
  }

  constructor() { }
}
