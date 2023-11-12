// update-need.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

import { Need } from '../Need';
import { NeedsService } from '../needs.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-need',
  templateUrl: './update-need.component.html',
  styleUrls: ['./update-need.component.css']
})
export class UpdateNeedComponent implements OnInit {
  Need!:Need;

  errorMessage = " ";

  constructor(private needsService:NeedsService,
    private route:ActivatedRoute,
    private router:Router
    ){
    
  }
  
  ngOnInit(): void {
    this.getNeed();
  }

  getNeed():void{
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needsService.getNeedbyId(id).subscribe(need =>
      this.Need = need
    )
  }

  onSubmit(){
    if(this.Need){
      this.needsService.updateNeed(this.Need).subscribe(response =>{
        console.log(response)
        this.router.navigate(['/adminDashboard'])
        console.log(response);
      },(error) =>{
        if(error.status >=400 || error.status < 500){
          this.errorMessage = "Please enter a valid need type"
        }
      }
      )
    Swal.fire({
        title: "Need Updated",
        icon: "success"
      });
    }
    
  }
}