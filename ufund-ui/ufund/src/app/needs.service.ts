import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,HttpErrorResponse } from '@angular/common/http';

import { Observable, catchError, of, tap, throwError } from 'rxjs';

import { Need } from './Need';

@Injectable({ providedIn: 'root' })
export class NeedsService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private cupBoardURL ='http://localhost:8080/cupboard'

  constructor(
    private http: HttpClient,
    ) 
    { }

    /** GET needs from the server */
    getNeedbyId(id:number):Observable<Need>{
      const url = `${this.cupBoardURL}/${id}`
      return this.http.get<Need>(url,this.httpOptions)
      .pipe(
        catchError(this.handleError<Need>('getNeedbyId')))
    }
    
    createNeed(need: Need): Observable<Need> {
      return this.http.post<Need>(this.cupBoardURL, need, this.httpOptions).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 400) {
            console.error("Enter valid data type",error.message);
          } else if (error.status > 400 && error.status < 500) {
            console.error("Enter valid valid input fields", error.message);
          }
          return throwError(error);
        })
      );
    }

    deleteNeedByName(name:string):Observable<Need>{
      const url = `${this.cupBoardURL}/${name}`
      return this.http.delete<Need>(url,this.httpOptions).pipe(
        catchError(this.handleError<Need>('deleteNeedbyName')))
    }

    deleteNeedbyId(id:number):Observable<Need>{
      const url = `${this.cupBoardURL}/${id}`
      
      this.deleteImage(id);
      
      return this.http.delete<Need>(url,this.httpOptions).pipe(
        catchError(this.handleError<Need>('deleteNeedbyId')))
    }

    deleteImage(id:number):Observable<Boolean>{
      const imgUrl = 'http://localhost:8080/imageDelete/needs'
      const url = `${imgUrl}/${id}`

      return this.http.delete<Boolean>(url,this.httpOptions).pipe(
        catchError(this.handleError<Boolean>('deleteNeedbyId')))
    }

    getEntireNeedsCupboard():Observable<Need[]>{
      return this.http.get<Need[]>(this.cupBoardURL,this.httpOptions)
      .pipe(
        catchError(this.handleError<Need[]>('getCupboard', []))
      );
    }

    updateNeed(need:Need):Observable<any>{
      return this.http.put<Need>(this.cupBoardURL,need,this.httpOptions)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 400) {
            console.error("Enter valid data type",error.message);
          } else if (error.status > 400 && error.status < 500) {
            console.error("Enter valid valid input fields", error.message);
          }
          return throwError(error);
        }));
    }


    helperDonation(id:number,donation:number):Observable<any>{
      const url = `${this.cupBoardURL}/helperDonation/${id}/donation/${donation}`
      return this.http.put<Need>(url,this.httpOptions)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 400) {
            console.error("Enter valid data type",error.message);
          } else if (error.status > 400 && error.status < 500) {
            console.error("Enter valid valid input fields", error.message);
          }
          return throwError(error);
        }));
    }

    searchCupboardByName(name:string): Observable<Need[]>{
      if(!name.trim()){
        return of([]);
      }

      const url = `${this.cupBoardURL}/needs/?name=${name}`
      
      console.log('im working' + url)
      return this.http.get<Need[]>(url).pipe(
        catchError(this.handleError<Need[]>('searcNeed', []))
        );

    }

  
    private handleError<T>(operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
        console.error(error);
        return of(result as T);
      };
    }
}
