import { Injectable } from '@angular/core';
import { User } from './User';
import { Observable, catchError, of } from 'rxjs';
import { Pet } from './Pet';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FavoritePets } from './FavoritePets';

@Injectable({
  providedIn: 'root'
})
export class FavoritePetsService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private favoritePetsURL = 'http://localhost:8080/favoritePets';
  
  constructor(private http:HttpClient) { 

  }

  createFavoritePets(favoritePets:FavoritePets):Observable<FavoritePets> {
    return this.http.post<FavoritePets>(this.favoritePetsURL,favoritePets,this.httpOptions)
    .pipe(
      catchError(this.handleError<FavoritePets>('createFavoritePets'))
    );
  }


  getFavoritePets(name:string):Observable<Pet[]>{
    const url = `${this.favoritePetsURL}/${name}`
    return this.http.get<Pet[]>(url,this.httpOptions)
    .pipe(
      catchError(this.handleError<Pet[]>('favoritePets'))
    );
  }

  addPetToFavoritePets(name:string,pet:Pet):Observable<FavoritePets>{
    const url = `${this.favoritePetsURL}/addPet/${name}`
    return this.http.put<FavoritePets>(url,pet,this.httpOptions).pipe(
      catchError(this.handleError<FavoritePets>('addPetToFavoritePets'))
    );
  }
  
  removePetFromFavoritePets(name:string,petId:number):Observable<FavoritePets>{
    const url = `${this.favoritePetsURL}/${name}/petId/${petId}`
    return this.http.delete<FavoritePets>(url,this.httpOptions).pipe(
      catchError(this.handleError<FavoritePets>('removePetFromFavoritePets'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
