import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../authorization-component/user';
import { UserTrainer } from './userTrainer';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  headers!: HttpHeaders;
  token!: string;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders();
    this.token = sessionStorage.getItem('token') || '';
    this.headers = this.headers.set("Authorization", "Bearer " + this.token);
    console.log(this.token);
  }

  getAssignedTrainer(userId: number): Observable<any> {
    return this.http.get("http://localhost:8082/trainer/" + userId, { headers: this.headers });
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>("http://localhost:8082/users", { headers: this.headers });
  }

  getAllTrainers(): Observable<User[]> {
    return this.http.get<User[]>("http://localhost:8082/trainers", { headers: this.headers });
  }

  getAssignedUsers(userId: number): Observable<User[]> {
    return this.http.get<User[]>("http://localhost:8082/users/" + userId, { headers: this.headers });
  }

  getUser(userId: number): Observable<any> {
    return this.http.get("http://localhost:8082/user/" + userId, { headers: this.headers });
  }

  assignTrainer(userTrainer:UserTrainer): Observable<any> {
    return this.http.post("http://localhost:8082/assign", userTrainer, { headers: this.headers, responseType: 'text' });
  }

}
