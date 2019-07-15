import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {TokenService} from "./token.service";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly authHost: string;


  constructor(private http: HttpClient, private tokenService: TokenService) {
    this.authHost = environment.authorizationServer;
    this
  }

  retrieveToken(code) {
    let tokenParams = this.tokenService.getTokenParams('authorization_code');
    tokenParams.append('code', code);


    let headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Accept': 'application/json',
      'Authorization': 'Basic ' + btoa(this.tokenService.clientId + ":secret")
    });
    this.http.post(this.authHost.concat('/oauth/token'), tokenParams.toString(), {headers: headers})
      .subscribe(
        data => this.saveToken(data),
        err => console.log(err)
      );
  }

  updateToken() {
    let headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Accept': 'application/json',
      'Authorization': 'Basic ' + btoa(this.tokenService.clientId + ":secret")
    });
    let tokenParams = this.tokenService.getTokenParams('refresh_token');
    tokenParams.append('refresh_token', this.tokenService.getToken().refresh_token);

    this.http.post(this.authHost.concat('/oauth/token'), tokenParams.toString(), {headers: headers}).subscribe(
      data => this.saveToken(data),
      err => console.log(err)
    );
  }

  saveToken(token) {

    this.tokenService.updateToken(token);
    this.tokenService.getToken();
    //
    // let currentDate = new Date();
    // let expireDate = new Date(currentDate.getTime() + (1000 * token.expires_in));
    // let refresh_expires_in = new Date(currentDate.getTime() + (1000 * token.refresh_expires_in));
    // Cookie.delete("access_token");
    // Cookie.delete("refresh_token");
    // Cookie.set("access_token", token.access_token, expireDate);
    // Cookie.set("refresh_token", token.refresh_token, refresh_expires_in);
  }

  logout() {
    this.tokenService.removeToken();
    window.location.reload();
  }

  login() {
    if (this.tokenService.checkRefreshToken()) {
      this.updateToken();
    } else {
      this.getClientCode();
    }
  }

  getClientCode() {
    window.location.href = this.authHost + '/oauth/authorize?response_type=code&client_id=' + this.tokenService.clientId + '&scope=foo&redirect_uri=' + this.tokenService.redirectUri;

  }

  isLoggedIn(): void {
    let i = window.location.href.indexOf('code');
    if (!this.tokenService.checkToken() && i != -1) {
      this.retrieveToken(window.location.href.substring(i + 5));
    }
  }
}
