import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/authorization-component/user';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-assigned-trainer',
  templateUrl: './assigned-trainer.component.html',
  styleUrls: ['./assigned-trainer.component.css']
})
export class AssignedTrainerComponent implements OnInit {

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  public assignedTrainer: User = {} as User;
  isError: boolean = false;
  message: string = '';

  ngOnInit(): void {
    this.getAssignedTrainer();
  }

  getAssignedTrainer() {
    let userId = sessionStorage.getItem('userId');
    console.log(userId);
    this.userService.getAssignedTrainer(Number(userId)).subscribe({
      next: (Response: User) => {
        this.assignedTrainer = Response;
        console.log(Response);
      },
      error: (err: Error) => {
        console.log(err);
        this.isError = true;
        this.message = "Trainer not yet assigned!!!";
        console.log(this.message)
      },
    });
  }
}
