import { Component } from '@angular/core';
import { UserAuthService } from 'src/app/user-auth.service';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent {

  constructor(private userAuthService: UserAuthService) { }
  
  logout() {
    this.userAuthService.loggedIn = false;
    localStorage.removeItem('loggedInUser');
  }
  
}
