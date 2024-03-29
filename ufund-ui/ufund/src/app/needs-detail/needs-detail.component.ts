import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Need } from '../Need';
import { NeedsService } from '../needs.service';
import { UserHelperService } from '../user-helper.service';
import { CurrentUserService } from '../current-user.service';
import { BehaviorSubject } from 'rxjs';
import { User } from '../User';
import { FundingBasketService } from '../funding-basket.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-needs-detail',
  templateUrl: './needs-detail.component.html',
  styleUrls: ['./needs-detail.component.css']
})
export class NeedsDetailComponent implements OnInit {

  need!:Need; // Initialize the property to null
  userDetail = false

  constructor(
    private route: ActivatedRoute,
    private needsService: NeedsService,
    private location: Location,
    private fundingBasketService:FundingBasketService,
    private currentUser:CurrentUserService,
  ) { 

  }
  username!:string;

  user!: BehaviorSubject<User|null>; 

  ngOnInit(): void {
    this.getNeed();

    
    this.currentUser.getCurrentUser().subscribe(user =>{
      if (user) {
        this.username = user.getUsername();
      }
      console.log(user)
    })
    if(this.username === 'admin'){
      this.userDetail = true;
    }
    console.log(this.username)
    
  }

  getNeed(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')!);
    this.needsService.getNeedbyId(id)
      .subscribe(need => this.need = need);
  }

  goBack(): void {
    this.location.back();
  }

  functionAddNeed(need: Need): void{
    this.fundingBasketService.addNeedToBasket(this.username,need).subscribe(user =>{
      console.log(user);
    })

    Swal.fire({
      title: "Added to basket",
      icon: "success"
    });
  }


}
