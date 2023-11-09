import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {

  private currentUser = new BehaviorSubject<User|null >(null);

  setCurrentUser(user:User):void{
    this.currentUser.next(user);
  }

  getCurrentUser():BehaviorSubject<User|null>{
    return this.currentUser;
  }
  constructor() { }
}
