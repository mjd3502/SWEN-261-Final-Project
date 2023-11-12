import { Injectable } from '@angular/core';
import { Need } from './Need';

@Injectable({
  providedIn: 'root'
})
export class FlagService {

  private donationValues: { [key: string]: number } = {};

  setUpdateQuantity(newQuantites:{ [key: string]: number }):void{
    this.donationValues = newQuantites;
  }

  getUpdateQuantity():{ [key: string]: number;}{
    return this.donationValues
  }

  constructor() { }
}
