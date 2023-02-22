import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/authorization-component/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-trainer-details',
  templateUrl: './trainer-details.component.html',
  styleUrls: ['./trainer-details.component.css']
})
export class TrainerDetailsComponent {

  users: User[] = [];

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.populateValues();
  }

  populateValues() {
    this.userService.getAllTrainers().subscribe(data => {
      this.users = data;
      console.log(this.users);
      console.log(data);
    })
  }

}
