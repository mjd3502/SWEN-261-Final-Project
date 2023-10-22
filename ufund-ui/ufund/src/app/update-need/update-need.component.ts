// update-need.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Need } from '../Need';
import { NeedsService } from '../needs.service';

@Component({
  selector: 'app-update-need',
  templateUrl: './update-need.component.html',
  styleUrls: ['./update-need.component.css']
})
export class UpdateNeedComponent implements OnInit {
  need: Need = { id: 0, name: '', quantity: 0, description: '', cost: 0};
  oldNeed: Need | undefined;

  constructor(
    private needsService: NeedsService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  /**
   * Fetch the old need based on the provided ID
   */
  ngOnInit() {
    this.getNeed();
  }

  /**
   * Call service to get the old need based on the ID
   * Initialize the form with the old need values
   * @param id id of need to get
   */
  getNeed(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needsService.getNeedyId(id).subscribe(need => {
      this.oldNeed = need;
      this.need = { ...this.oldNeed! }; // Use non-null assertion operator here
    });
  }

  /**
   * Go back to last location in route
   */
  goBack(): void {
    this.location.back();
  }

  /**
   * Combine unchanged values from old need and changed values from the form
   * Call service to update the need with updatedNeed object
   */
  updateNeed() {
    const updatedNeed: Need = {
      ...this.oldNeed!,
      ...this.need
    };

    this.needsService.updateNeed(updatedNeed).subscribe(need => {
      console.log('Need Updated:', need);
      this.resetForm();
    });
  }

  /**
   * If update successful, reset form to blank need
   */
  resetForm() {
    this.need = { id: 0, name: '', quantity: 0, description: '', cost: 0};
  }

  /**
   * FROM HEROES, might not need 
   */
  // save(): void {
  //   if (this.need) {
  //     this.needsService.updateNeed(this.need)
  //       .subscribe(() => this.goBack());
  //   }
  // }
}
