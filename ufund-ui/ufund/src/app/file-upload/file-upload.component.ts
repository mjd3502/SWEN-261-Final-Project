import { Component } from '@angular/core';
import { FileUploadService } from '../file-upload.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})

export class FileUploadComponent {
  link: string = "";
  loading: boolean = false;
  files: File[] = [];

  constructor(private fileUploadService: FileUploadService){}

  ngOnInit():void{

  }

  //when a file is selected
  onChange(event: any){
    this.files = event.target.files;
  }

  //when button to upload is clicked
  onUpload(){
    this.loading = !this.loading

    console.log(this.files[0]);

    this.fileUploadService.upload(this.files[0]).subscribe(
      (event:any)=> {
        if(typeof (event) === 'object'){
          this.link = event.link;

          this.loading = false;
        }
      }
    )

  }
}
