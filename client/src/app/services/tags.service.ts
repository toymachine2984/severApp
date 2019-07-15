import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Cookie} from "ng2-cookies";
import {Tags} from "../entity/tags";

@Injectable({
  providedIn: 'root'
})
export class TagsService {

  private resourceHost: string;

  constructor(private http: HttpClient) {
    this.resourceHost = environment.resourceServer;
  }

  getTags(): Observable<Tags[]> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http.get<Tags[]>(this.resourceHost.concat('tags'), {headers: headers});
  }

  addTags(model: string): Observable<Tags> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token'),
      'Content-type': 'application/json;charset=utf-8'
    });
    return this.http.post<Tags>(this.resourceHost.concat('tags'), model, {headers: headers});
  }

}
