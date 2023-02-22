import { Component } from '@angular/core';
import { TrainerAuthService } from 'src/app/trainer-auth.service';

@Component({
  selector: 'app-trainer-menu',
  templateUrl: './trainer-menu.component.html',
  styleUrls: ['./trainer-menu.component.css']
})
export class TrainerMenuComponent {

  constructor(private trainerAuthService: TrainerAuthService) { }
  
  logout() {
    this.trainerAuthService.loggedIn = false;
    localStorage.removeItem('loggedInTrainer');
  }

}
