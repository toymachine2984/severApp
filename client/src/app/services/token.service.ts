import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Token} from "../entity/token";

@Injectable({
  providedIn: 'root'
})
export class TokenService {


  private readonly _clientId: string;
  private readonly _redirectUri: string;

  //fixme add checkbox in to html
  private scope = 'foo';
  private response_type: string;
  private readonly _secret: string;

  constructor() {
    this._clientId = environment.clientId;
    this.response_type = environment.response_type;
    this._secret = environment.secret;
    this._redirectUri = window.location.origin;
  }

  getToken(): Token {
    return JSON.parse(localStorage.getItem('token'));
  }


  updateToken(token: Token) {
    localStorage.removeItem('token');
    localStorage.setItem('token', JSON.stringify(token));
  }


  checkToken(): boolean {
    if (this.getToken() != null) {
      return this.getToken().expires_in.getTime() >= Date.now();
    } else {
      return false;
    }
  }

  checkRefreshToken(): boolean {
    if (this.getToken() != null) {
      if (this.getToken().refresh_expires_in.getTime() >= Date.now()) {
        return true;
      } else {
        this.removeToken();
        return false;
      }
    }
  }

  getTokenParams(grantType: string): URLSearchParams {
    let params = new URLSearchParams();
    params.append('grant_type', grantType);
    params.append('client_id', this._clientId);
    params.append('client_secret', this._secret);
    params.append('redirect_uri', this._redirectUri);
    return params;
  }

  removeToken() {
    localStorage.removeItem('token');
  }


  get clientId(): string {
    return this._clientId;
  }

  get redirectUri(): string {
    return this._redirectUri;
  }
}
