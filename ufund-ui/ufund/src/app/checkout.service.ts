import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Need } from './Need';


@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  private needCheckout = new BehaviorSubject<Need|null>(null);

  setCurrentUser(user:Need):void{
    this.needCheckout.next(user);
  }

  getCurrentUser():BehaviorSubject<Need|null>{
    return this.needCheckout;
  }
  constructor() { }
}
