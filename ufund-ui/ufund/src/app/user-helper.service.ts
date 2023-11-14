import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { User } from './User';
import { Need } from './Need';

@Injectable({
  providedIn: 'root'
})
export class UserHelperService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private userURL = 'http://localhost:8080/user';
  
  constructor(private http:HttpClient) { 

  }

  createUser(user:User):Observable<User> {
    return this.http.post<User>(this.userURL,user,this.httpOptions)
    .pipe(
      catchError(this.handleError<User>('createUser'))
    );
  }


  getUserName(username:string):Observable<string>{
    const url = `${this.userURL}/userName/${username}`
    return this.http.get<string>(url,this.httpOptions)
    .pipe(
      catchError(this.handleError<string>('createUser'))
    );
  }

  /**Get a single user by its name */
  getUserByName(username:string):Observable<User>{
    const url = `${this.userURL}/${username}`
    return this.http.get<User>(url,this.httpOptions)
    .pipe(
      catchError(this.handleError<User>('createUser'))
    );
  }
  
  doesUserExist(username:string):Observable<Boolean>{
    const url = `${this.userURL}/exists/${username}`
    return this.http.get<Boolean>(url,this.httpOptions)
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
