import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  // //api url to accept post requests 
  // url = "http://localhost:5000/upload"

  // constructor(private http:HttpClient) { }

  // onChange(event){
  //   console.log(event);
  // }



  // upload(file: File):Observable<any>{
  //   const formData = new FormData();

  //   //storing form under the name "file"
  //   formData.append("file",file,file.name);

  //   //http post to the server with the form data
  //   return this.http.post<File>(this.url, formData)
  // }
}
