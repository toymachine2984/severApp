import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Cookie} from "ng2-cookies";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private resourceHost: string;

  constructor(private http: HttpClient) {
    this.resourceHost = environment.resourceServer;
  }

  addImage(model: string): Observable<string> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token'),
      'Content-type': 'application/json;charset=utf-8'
    });
    return this.http.post<string>(this.resourceHost.concat('images'), model, {headers: headers});
  }
}
