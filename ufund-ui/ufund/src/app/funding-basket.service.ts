import { Injectable } from '@angular/core';
import { User } from './User';
import { Observable, catchError, of } from 'rxjs';
import { Need } from './Need';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FundingBasket } from './FundingBasket';

@Injectable({
  providedIn: 'root'
})
export class FundingBasketService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private fundingBasketURL = 'http://localhost:8080/fundingBasket';
  
  constructor(private http:HttpClient) { 

  }

  createFundingBasket(fundingBasket:FundingBasket):Observable<FundingBasket> {
    return this.http.post<FundingBasket>(this.fundingBasketURL,fundingBasket,this.httpOptions)
    .pipe(
      catchError(this.handleError<FundingBasket>('createFundingBasket'))
    );
  }


  getFundingBasket(name:string):Observable<Map<number,Need>>{
    const url = `${this.fundingBasketURL}/${name}`
    return this.http.get<Map<number,Need>>(url,this.httpOptions)
    .pipe(
      catchError(this.handleError<Map<number,Need>>('fundingBasket'))
    );
  }

  addNeedToBasket(name:string,need:Need):Observable<FundingBasket>{
    const url = `${this.fundingBasketURL}/addNeed/${name}`
    return this.http.put<FundingBasket>(url,need,this.httpOptions).pipe(
      catchError(this.handleError<FundingBasket>('addneedtoFundingBasket'))
    );
  }
  
  removeNeedFromBasket(name:string,needId:number):Observable<Map<number,Need>>{
    const url = `${this.fundingBasketURL}/${name}/needId/${needId}`
    return this.http.delete<Map<number,Need>>(url,this.httpOptions).pipe(
      catchError(this.handleError<Map<number,Need>>('removeNeedFromBasket'))
    );
  }

  clearBasket(userName:string):Observable<Map<number,Need>>{
    const url = `${this.fundingBasketURL}/clearFundingBasket/${userName}`
    return this.http.delete<Map<number,Need>>(url,this.httpOptions).pipe(
      catchError(this.handleError<Map<number,Need>>('clearBasket'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
