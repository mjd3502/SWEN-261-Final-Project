import { Component } from '@angular/core';
import { FileUploadService } from '../file-upload.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})

export class FileUploadComponent {
  file!: File;

  constructor(private http:HttpClient){}

  ngOnInit():void{

  }

  //when a file is selected
  onChange(event: any){
    console.log(event);
    this.file= <File>event.target.files[0];
  }

  //when button to upload is clicked
  onUpload(){
    //created form data
    const data = new FormData();
    //adds the image to form data
    data.append('image',this.file, this.file.name)
    
    //sends post request with the form data
    this.http.post('http://localhost:4200/upload',data)
      .subscribe(response => {
        console.log(response);
      });
  }
}
