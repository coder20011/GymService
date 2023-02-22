import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/authorization-component/user';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserTrainer } from '../userTrainer';

@Component({
  selector: 'app-trainer-assign',
  templateUrl: './trainer-assign.component.html',
  styleUrls: ['./trainer-assign.component.css']
})
export class TrainerAssignComponent implements OnInit {

  users: User[] = [];
  userTrainer: UserTrainer = {
    transactionId : 0,
    trainerId : 0,
    userId : 0
  } ;

  constructor(private userService: UserService, private route:ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.userTrainer.userId = this.route.snapshot.params['id'];
    console.log(this.userTrainer.userId);
    this.userService.getAllTrainers().subscribe(data => {
      this.users = this.users.concat(data);
      console.log(this.users);
      console.log(data);
    });
  }

  assign(userId:number) {
    console.log(userId);
    this.userTrainer.trainerId = userId;
    console.log("Tid"+this.userTrainer.trainerId);
    this.userService.assignTrainer(this.userTrainer).subscribe(
      (data)=> {
        console.log(data);
        window.alert(data);
        this.router.navigate(['/admin/users'])
      }
    )
  }

}
