import { Injectable } from '@angular/core';
import { Need } from './Need';

@Injectable({
  providedIn: 'root'
})
export class FlagService {

  private updateQuantity: Need[] = [];

  setUpdateQuantity(newQuantites:Need[]):void{
    this.updateQuantity = newQuantites;
  }

  getUpdateQuantity():Need[]{
    return this.updateQuantity
  }

  constructor() { }
}
