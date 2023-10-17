import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Need } from '../Need';
import { NeedsService } from '../needs.service';

@Component({
  selector: 'app-needs-detail',
  templateUrl: './needs-detail.component.html',
  styleUrls: ['./needs-detail.component.css']
})
export class NeedsDetailComponent implements OnInit {
  need: Need | undefined; // Initialize the property to null

  constructor(
    private route: ActivatedRoute,
    private needsService: NeedsService,
    private location: Location
  ) { }

  ngOnInit(): void {
    // Assuming getNeed() returns a Need object or fetches it from an API
    this.getNeed();
  }

  getNeed(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needsService.getNeedbyId(id)
      .subscribe(need => this.need = need);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.need) {
      this.needsService.updateNeed(this.need)
        .subscribe(() => this.goBack());
    }
  }

}
