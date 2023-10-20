import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, catchError, of, tap } from 'rxjs';

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
    
    createNeed(need:Need):Observable<Need>{
      return this.http.post<Need>(this.cupBoardURL,need,this.httpOptions)
      .pipe(
        catchError(this.handleError<Need>('addNeed'))
      );
    }

    deleteNeedByName(name:string):Observable<Need>{
      const url = `${this.cupBoardURL}/${name}`
      return this.http.delete<Need>(url,this.httpOptions).pipe(
        catchError(this.handleError<Need>('deleteNeedbyName')))
    }

    deleteNeedbyId(id:number):Observable<Need>{
      const url = `${this.cupBoardURL}/${id}`
      return this.http.delete<Need>(url,this.httpOptions).pipe(
        catchError(this.handleError<Need>('deleteNeedbyId')))
    }

    getEntireNeedsCupboard():Observable<Need[]>{
      return this.http.get<Need[]>(this.cupBoardURL,this.httpOptions)
      .pipe(
        catchError(this.handleError<Need[]>('getCupboard', []))
      );
    }

    updateNeed(need:Need):Observable<Need>{
      return this.http.put<Need>(this.cupBoardURL,need,this.httpOptions)
      .pipe(
        catchError(this.handleError<Need>('updateNeed')))
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
