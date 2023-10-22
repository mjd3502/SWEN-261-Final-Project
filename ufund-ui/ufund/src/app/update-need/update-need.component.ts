import { Component } from '@angular/core';
import { NeedsService } from '../needs.service';

@Component({
  selector: 'app-update-need',
  templateUrl: './update-need.component.html',
  styleUrls: ['./update-need.component.css']
})
export class UpdateNeedComponent {
  need: any = {};
  oldNeed: any = {};

  constructor(private needsService:NeedsService) {}

  ngOnInit() {
    // Fetch the old need based on the provided ID
    const id = 1; // Replace this with the actual ID or fetch it from the route params
    this.getNeed(id);
  }

  getNeed(id: number) {
    // Call service to get the old need based on the ID
    this.needsService.getNeedyId(id).subscribe((data: any) => {
      this.oldNeed = data;
      // Initialize the form with the old need values
      this.need = { ...this.oldNeed };
    });
  }

  updateNeed() {
    // Combine unchanged values from old need and changed values from the form
    const updatedNeed = {
      ...this.oldNeed,
      ...this.need
    };

    // Call service to update the need with updatedNeed object
    this.needsService.updateNeed(updatedNeed).subscribe((data: any) => {
      console.log('Need Updated:', data);
      // Reset the form after successful update if needed
      this.resetForm();
    });
  }

  resetForm() {
    this.need = {};
  }
}