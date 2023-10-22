import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Need } from '../Need';
import { NeedsService } from '../needs.service';
import { UserHelperService } from '../user-helper.service';

@Component({
  selector: 'app-needs-detail',
  templateUrl: './needs-detail.component.html',
  styleUrls: ['./needs-detail.component.css']
})
export class NeedsDetailComponent implements OnInit {

  need!:Need; // Initialize the property to null

  constructor(
    private route: ActivatedRoute,
    private needsService: NeedsService,
    private location: Location,
    private userService:UserHelperService
  ) { 

  }

  ngOnInit(): void {
    this.getNeed();
  }

  getNeed(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')!);
    this.needsService.getNeedbyId(id)
      .subscribe(need => this.need = need);
  }

  goBack(): void {
    this.location.back();
  }

  // functionAddNeed(need: Need): void{
  //   this.userService.addNeedToBasket(2,need).subscribe(user =>{
  //     console.log(user);
  //   })
  // }

  // save(): void {
  //   if (this.need) {
  //     this.needsService.updateNeed(this.need)
  //       .subscribe(() => this.goBack());
  //   }
  // }

}
