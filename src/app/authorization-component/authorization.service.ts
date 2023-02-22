import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCredentials } from './userCredentials';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  headers!:HttpHeaders;
  token!:string; 

  constructor(private http:HttpClient) { 
    this.headers = new HttpHeaders();
    this.token = sessionStorage.getItem('token') || '';    
    this.headers = this.headers.set("Authorization", "Bearer "+ this.token);
    console.log(this.token);
  }

  login(credentials:UserCredentials):Observable<any> {
    return this.http.post("http://localhost:8081/auth/login", credentials, {responseType: 'text'});
  }

  validate():Observable<any> {
    return this.http.get("http://localhost:8081/auth/validate", { headers: this.headers, responseType: 'text' });
  }
  
  getUserType(userName:string, password:string):Observable<any>{
    return this.http.get("http://localhost:8082/userType/"+userName+"/"+password,{responseType:'text'});
  }

  registerUser(user: User): Observable<any> {
    return this.http.post("http://localhost:8082/register", user,{responseType:'text'});
  }

  getUserId(userName:string, password:string):Observable<any>{
    return this.http.get("http://localhost:8082/userId/"+userName+"/"+password,{responseType:'text'});
  }

}