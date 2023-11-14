import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

import { Observable, catchError, of, tap, throwError } from 'rxjs';

import { Pet } from './Pet';

@Injectable({ providedIn: 'root' })
export class PetService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private URL ='http://localhost:8080/pet'
  constructor(
    private http: HttpClient,
    ) 
    { }

    /** GET pets from the server */
    getPetbyId(id:number):Observable<Pet>{
      const url = `${this.URL}/${id}`
      return this.http.get<Pet>(url,this.httpOptions)
      .pipe(
        catchError(this.handleError<Pet>('getPetbyId')))
    }
    
    createPet(pet:Pet):Observable<Pet>{
      return this.http.post<Pet>(this.URL,pet,this.httpOptions)
      .pipe(
        // catchError(this.handleError<Pet>('addPet'))
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

    deletePetByName(name:string):Observable<Pet>{
      const url = `${this.URL}/petName/${name}`
      return this.http.delete<Pet>(url,this.httpOptions).pipe(
        catchError(this.handleError<Pet>('deletePetbyName')))
    }

    deletePetbyId(id:number):Observable<Pet>{
      const url = `${this.URL}/${id}`
      return this.http.delete<Pet>(url,this.httpOptions).pipe(
        catchError(this.handleError<Pet>('deletePetbyId')))
    }

    getAllPets():Observable<Pet[]>{
      return this.http.get<Pet[]>(this.URL,this.httpOptions)
      .pipe(
        catchError(this.handleError<Pet[]>('getAllPets', []))
      );
    }

    updatePet(pet:Pet):Observable<any>{
      return this.http.put<Pet>(this.URL,pet,this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('updatePet')))
    }

    searchPetsByName(name:string): Observable<Pet[]>{
      if(!name.trim()){
        return of([]);
      }

      const url = `${this.URL}/pets/?name=${name}`
      
      console.log('im working' + url)
      return this.http.get<Pet[]>(url).pipe(
        catchError(this.handleError<Pet[]>('searchPet', []))
        );

    }

  
    private handleError<T>(operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
        console.error(error);
        return of(result as T);
      };
    }
}
