import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AuthMenuComponent } from './authorization-component/auth-menu/auth-menu.component';
import { LoginComponent } from './authorization-component/login/login.component';
import { RegisterComponent } from './authorization-component/register/register.component';
import { BmiCalculatorComponent } from './gym-component/bmi-calculator/bmi-calculator.component';
import { CalorieCalculatorComponent } from './gym-component/calorie-calculator/calorie-calculator.component';
import { AdminMenuComponent } from './user-component/admin-menu/admin-menu.component';
import { AssignedTrainerComponent } from './user-component/assigned-trainer/assigned-trainer.component';
import { AssignedUsersComponent } from './user-component/assigned-users/assigned-users.component';
import { TrainerMenuComponent } from './user-component/trainer-menu/trainer-menu.component';
import { UserMenuComponent } from './user-component/user-menu/user-menu.component';
import { TrainerAssignComponent } from './user-component/trainer-assign/trainer-assign.component';
import { UserDetailsComponent } from './user-component/user-details/user-details.component';
import { TrainerDetailsComponent } from './user-component/trainer-details/trainer-details.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    AuthMenuComponent,
    BmiCalculatorComponent,
    CalorieCalculatorComponent,
    AssignedTrainerComponent,
    AssignedUsersComponent,
    UserMenuComponent,
    TrainerMenuComponent,
    AdminMenuComponent,
    TrainerAssignComponent,
    UserDetailsComponent,
    TrainerDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
