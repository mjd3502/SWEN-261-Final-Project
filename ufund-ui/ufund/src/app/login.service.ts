import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private isAdmin!:boolean

  setIsAdmin(isAdmin:boolean){
    this.isAdmin = isAdmin
  }

  getIsAdmin(){
    return this.isAdmin;
  }

  constructor() { }
}
