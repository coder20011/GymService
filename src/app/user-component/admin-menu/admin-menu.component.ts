import { Component } from '@angular/core';
import { AdminAuthService } from 'src/app/admin-auth.service';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent {

  constructor(private adminAuthService: AdminAuthService) { }
  
  logout() {
    this.adminAuthService.loggedIn = false;
    localStorage.removeItem('loggedInAdmin');
  }

}
