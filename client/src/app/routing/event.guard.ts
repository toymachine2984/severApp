import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {Cookie} from "ng2-cookies";

@Injectable({
  providedIn: 'root'
})
export class EventGuard implements CanActivate  {

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return Cookie.get('access_token') != '';

  }
  
}
