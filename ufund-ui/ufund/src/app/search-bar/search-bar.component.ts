import { Component,OnInit } from '@angular/core';
import { NeedsService } from '../needs.service';
import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { Need } from '../Need';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit{
  
  needs$!:Observable<Need[]>;
  private searchNeeds = new Subject<string>();

  constructor(private needsService:NeedsService){}

  search(value:string){
    this.searchNeeds.next(value);
  }
  

  ngOnInit(): void {
    this.needs$ = this.searchNeeds.pipe(
      debounceTime(300),

      distinctUntilChanged(),

      switchMap((term: string) => this.needsService.searchCupboardByName(term)),
    );
  }

}

