import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { TrainerAuthService } from './trainer-auth.service';

@Injectable({
  providedIn: 'root'
})
export class TrainerAuthGuard implements CanActivate {

  constructor(private trainerAuthService: TrainerAuthService, private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (localStorage.getItem('loggedInTrainer') === 'true') {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

}
