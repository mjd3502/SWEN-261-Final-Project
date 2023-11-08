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

    console.log(this.file);

    //created form data
    const data = new FormData();
    //adds the image to form data
    data.append('image',this.file, this.file.name)
    console.log(data.get('image'));
    console.log(data);
    
    //sends post request with 
    //form data or file ??
    this.http.post('http://localhost:8080/upload',data)
      .subscribe(response => {
        console.log(response);
      });
  }
}
