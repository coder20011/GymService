import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/authorization-component/user';

@Component({
  selector: 'app-assigned-users',
  templateUrl: './assigned-users.component.html',
  styleUrls: ['./assigned-users.component.css']
})
export class AssignedUsersComponent implements OnInit {

  users: User[] = [];
  isError: boolean = false;
  message: string = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    let userId = sessionStorage.getItem('userId');
    console.log(userId);
    this.userService.getAssignedUsers(Number(userId)).subscribe(
      (data) => {
        this.users = data;
        console.log(this.users);
        console.log(data);
      },
      (error) => {
        console.log(error);
        this.isError = true;
        this.message = "Users not yet assigned!!!"
      }
    );
  }

}