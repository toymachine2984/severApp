import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Cookie} from "ng2-cookies";
import {Lecture} from "../entity/lecture";

@Injectable({
  providedIn: 'root'
})
export class LectureService {

  private resourceHost: string;

  constructor(private http: HttpClient) {
    this.resourceHost = environment.resourceServer;
  }

  getLectures(): Observable<Lecture[]> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http.get<Lecture[]>(this.resourceHost.concat('lectures'), {headers: headers});
  }

  addLectures(model: string): Observable<Lecture> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token'),
      'Content-type': 'application/json;charset=utf-8'
    });
    return this.http.post<Lecture>(this.resourceHost.concat('lectures'), model, {headers: headers});
  }

}
