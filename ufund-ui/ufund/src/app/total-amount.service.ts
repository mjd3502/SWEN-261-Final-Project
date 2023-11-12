import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TotalAmountService {

  private totalAmoount = 0
  constructor() { }

  setTotalAmount(totalAmount:number){
    this.totalAmoount = totalAmount
  }

  getTotalAmount():number{
    return this.totalAmoount;
  }
}
