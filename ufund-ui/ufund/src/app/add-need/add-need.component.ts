import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-need',
  templateUrl: './add-need.component.html',
  styleUrls: ['./add-need.component.css']
})
export class AddNeedComponent {
  AddNeedSection = new FormGroup(
    {
      name: new FormControl('',[Validators.required]),
      quantity: new FormControl('',[Validators.required]),
      description: new FormControl('',[Validators.required]),
      cost: new FormControl('',[Validators.required]),
    }
  )


  

}
