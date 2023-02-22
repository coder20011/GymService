import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { AuthorizationService } from '../authorization.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userDetails: User = {
    userId: 0,
    userName: '',
    password: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
    contactNumber: '',
    emailId: '',
    userType: '',
  };

  constructor(private service: AuthorizationService, private router:Router) { }

  ngOnInit(): void {
    
  }

  onSubmit(user:User): void {
    console.log(user);
    this.service.registerUser(user).subscribe(
      (data)=> {
        console.log(data);
      }
    );
    this.router.navigate(["/login"]);
  }

}