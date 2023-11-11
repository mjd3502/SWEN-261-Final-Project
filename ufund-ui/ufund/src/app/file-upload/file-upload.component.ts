import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})

export class FileUploadComponent {
  //stores the file passed
  file!: File;
  
  //data from url
  //defines if a need or pet is being stored
  type!: String;
  //id of associated pet/need
  id!: String;

  constructor(private http:HttpClient, private route: ActivatedRoute,){}

  ngOnInit():void{
    //gets the id # from the url on init and the type
    this.getVals();

  }

  getVals() :void{
    //gets the id from the url and the type (need/pet)
    this.id = String(this.route.snapshot.paramMap.get('id'));
    this.type = String(this.route.snapshot.paramMap.get('type'));
  }

  //when a file is selected
  onChange(event: any){
    console.log(event);

    //sets this file to the file the user selects
    this.file= <File>event.target.files[0];
  }

  //when button to upload is clicked
  onUpload(){

    //created form data
    const data = new FormData();

    //adds the image to form data
    data.append('image',this.file, this.file.name)
    //adds the id from the url for the file to be named when stored in the folder
    data.append('name',(String)(this.id))

    //console log image data and the name, just the id in the url which will be the name of the file when saved
    console.log(data.get('image'));
    console.log(data.get('name'));
    
    //if an image for a need is added
    if(this.type == 'need'){
      //sends post request for a need with 
      //form data containing the file and the name (Id) for it to be stored under
      this.http.post('http://localhost:8080/upload-need',data)
        .subscribe(response => {
          console.log(response);
        });
    }

    //if an image for a pet being added
    else{
      //sends post request for a pet with 
      //form data containing the file and the name (Id) for it to be stored under
      this.http.post('http://localhost:8080/upload-pet',data)
        .subscribe(response => {
          console.log(response);
        });
    }
    
  }
}
