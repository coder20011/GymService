import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './authorization-component/login/login.component';
import { CalorieCalculatorComponent } from './gym-component/calorie-calculator/calorie-calculator.component';
import { RegisterComponent } from './authorization-component/register/register.component';
import { AssignedUsersComponent } from './user-component/assigned-users/assigned-users.component';
import { AssignedTrainerComponent } from './user-component/assigned-trainer/assigned-trainer.component';
import { BmiCalculatorComponent } from './gym-component/bmi-calculator/bmi-calculator.component';
import { TrainerAssignComponent } from './user-component/trainer-assign/trainer-assign.component';
import { AdminAuthGuard } from './admin-auth.guard';
import { TrainerDetailsComponent } from './user-component/trainer-details/trainer-details.component';
import { UserDetailsComponent } from './user-component/user-details/user-details.component';
import { TrainerAuthGuard } from './trainer-auth.guard';
import { UserAuthGuard } from './user-auth.guard';

const routes: Routes = [
  { path: "home", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "trainer", component: AssignedUsersComponent, canActivate: [TrainerAuthGuard] },
  { path: "user/trainerInfo", component: AssignedTrainerComponent, canActivate: [UserAuthGuard] },
  { path: "bmi", component: BmiCalculatorComponent, canActivate: [UserAuthGuard] },
  { path: "calorie", component: CalorieCalculatorComponent, canActivate: [UserAuthGuard] },
  { path: "admin/trainers", component: TrainerDetailsComponent, canActivate: [AdminAuthGuard] },
  { path: "admin/users", component: UserDetailsComponent, canActivate: [AdminAuthGuard] },
  { path: "assign/:id", component: TrainerAssignComponent, canActivate: [AdminAuthGuard] },
  { path: "", redirectTo: "home", pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
