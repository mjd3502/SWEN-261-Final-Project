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


  getFundingBasket(id:number):Observable<Need[]>{
    const url = `${this.userURL}/fundingBasket/${id}`
    return this.http.get<Need[]>(url,this.httpOptions)
    .pipe(
      catchError(this.handleError<Need[]>('fundingBasket'))
    );
  }

  addNeedToBasket(id:number,need:Need):Observable<User>{
    const url = `${this.userURL}/addNeed/${id}`
    return this.http.put<User>(url,need,this.httpOptions).pipe(
      catchError(this.handleError<User>('createUser'))
    );
  }
  
  removeNeedFromBasket(id:number,needId:Number):Observable<User>{
    const url = `${this.userURL}/${id}/needId/${needId}`
    return this.http.delete<User>(url,this.httpOptions).pipe(
      catchError(this.handleError<User>('createUser'))
    );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
