import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from './category';
import { Calorie } from './calorie';
import { Bmi } from './bmi';

@Injectable({
  providedIn: 'root'
})
export class GymService {

  headers!:HttpHeaders;
  token!:string; 

  constructor(private http:HttpClient) { 
    this.headers = new HttpHeaders();
    this.token = sessionStorage.getItem('token') || '';    
    this.headers = this.headers.set("Authorization", "Bearer "+ this.token);
    console.log(this.token);
  }

  getAllCategory():Observable<any> {
    return this.http.get("http://localhost:8083/category", { headers: this.headers });
  }

  getFoodByCategory(foodCatId:number):Observable<any> {
    return this.http.get("http://localhost:8083/food/category/" + foodCatId, { headers: this.headers });
  }

  calculateTotalCalories(caloriesList:Calorie[]):Observable<any> {
    return this.http.post("http://localhost:8083/calories", caloriesList, { headers: this.headers, responseType: 'text' });
  }

  calculateBmi(bmi:Bmi):Observable<any> {
    return this.http.post("http://localhost:8083/bmi", bmi, { headers: this.headers, responseType: 'text' });
  }

}
