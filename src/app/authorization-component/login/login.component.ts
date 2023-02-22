import { Component, OnInit } from '@angular/core';
import { UserCredentials } from '../userCredentials';
import { AuthorizationService } from '../authorization.service';
import { Router } from '@angular/router';
import { AdminAuthService } from 'src/app/admin-auth.service';
import { UserAuthService } from 'src/app/user-auth.service';
import { TrainerAuthService } from 'src/app/trainer-auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: UserCredentials = {
    userName: "",
    password: ""
  };
  loginToken!: string;
  loginError!: string;

  constructor(private service: AuthorizationService, private router: Router, private adminAuthService: AdminAuthService, private userAuthService: UserAuthService, private trainerAuthService: TrainerAuthService) { }

  ngOnInit(): void {

  }

  onSubmit(credentials: UserCredentials) {
    console.log(this.credentials);
    this.service.login(credentials).subscribe(
      (data) => {
        console.log(data);
        this.loginToken = data;
        sessionStorage.setItem('token', this.loginToken);
        console.log(sessionStorage);
        this.getUserId(credentials);
        this.getUserType(credentials);
      },
      (error) => {
        console.log(error);
        this.loginError = "Invalid Credentials";
      }
    );
    this.service.validate().subscribe(
      (data) => {
        console.log(data);
      }
    );
  }

  getUserId(credentials: UserCredentials): void {
    this.service.getUserId(credentials.userName, credentials.password).subscribe(
      (data) => {
        console.log(data);
        sessionStorage.setItem('userId', data);
      }
    );
  }

  getUserType(credentials: UserCredentials): void {
    this.service.getUserType(credentials.userName, credentials.password).subscribe(
      (data) => {
        console.log(data);
        console.log(typeof (data));
        if (data === "user") {
          this.userAuthService.login();
          this.router.navigate(['/' + data + '/trainerInfo']);
        } else if (data === "trainer") {
          this.trainerAuthService.login();
          this.router.navigate(['/' + data]);
        } else {
          this.adminAuthService.login();
          this.router.navigate(['/' + data + '/trainers']);
        }
      }
    );
  }

}
