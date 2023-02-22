import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  loggedIn: boolean = false;

  constructor() { }

  login() {
    this.loggedIn = true;
    localStorage.setItem('loggedInUser', 'true');
    console.log("___"+this.loggedIn);
  }

  logout() {
    this.loggedIn = false;
    localStorage.removeItem('loggedInUser');
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

}
