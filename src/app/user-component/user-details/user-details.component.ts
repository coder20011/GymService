import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/authorization-component/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  users: User[] = [];
  trainerAvailable: boolean[] = new Array();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.populateValues();
  }

  populateValues() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
      console.log(this.users);
      console.log(data);
      data.forEach((value)=>{
        this.getTrainer(value.userId);
      })
    })
  }

  getTrainer(userId: number) {
    this.userService.getAssignedTrainer(userId).subscribe(
      (data)=>{
        console.log(data);
        this.trainerAvailable[userId] = true;
      },
      (error)=> {
        console.log(error);
        this.trainerAvailable[userId] = false;
      }
    );
  }

}
