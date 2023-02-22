import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TrainerAuthService {

  loggedIn: boolean = false;

  constructor() { }

  login() {
    this.loggedIn = true;
    localStorage.setItem('loggedInTrainer', 'true');
  }

  logout() {
    this.loggedIn = false;
    localStorage.removeItem('loggedInTrainer');
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

}
