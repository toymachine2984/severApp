import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, retry} from 'rxjs/operators';
import {Injectable} from "@angular/core";
import {LoginService} from "../services/login.service";

@Injectable({providedIn: 'root'})
export class RefreshTokenInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // let token = localStorage.getItem('access_token');
    //
    // return next.handle(req).pipe(
    //   retry(1),
    //   catchError((error: HttpErrorResponse) => {
    //
    //     // We don't want to refresh token for some requests like login or refresh token itself
    //     // So we verify url and we throw an error if it's the case
    //     if (req.url.includes("token") || req.url.includes("login")) {
    //       // We do another check to see if refresh token failed
    //       // In this case we want to logout user and to redirect it to login page
    //       if (req.url.includes("refreshtoken")) {
    //         // this.loginService.logout();
    //         this.loginService.logout();
    //       }
    //
    //       return Observable.throw(error);
    //
    //     }
    //
    //     if (error.status === 401) {
    //       this.loginService.login();
    //       req = req.clone({
    //         setHeaders: {
    //           'Authorization': 'Bearer ' + token,
    //           'Content-type': 'application/json;charset=utf-8'
    //         }
    //       });
    //       return next.handle(req);
    //     } else {
    //       this.loginService.logout();
    //     }
    //     return throwError(error.message);
    //   })
    // );
    //

    return next.handle(req).pipe(
      retry(1),
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          console.log(error);
          this.loginService.login();
        }
        return throwError(error.message);
      })
    );
  }

}
