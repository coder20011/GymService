import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  loggedIn: boolean = false;

  constructor() { }

  login() {
    this.loggedIn = true;
    localStorage.setItem('loggedInAdmin', 'true');
  }

  logout() {
    this.loggedIn = false;
    localStorage.removeItem('loggedInAdmin');
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

}
