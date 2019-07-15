import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from "ng2-cookies";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Event} from "../entity/event";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private resourceHost: string;

  constructor(private http: HttpClient) {
    this.resourceHost = environment.resourceServer;
  }

  getEvents(): Observable<Event[]> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http.get<Event[]>(this.resourceHost.concat('events'), {headers: headers});
  }

  createEvent(model: Event): Observable<Event> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token'),
      'Content-type': 'application/json;charset=utf-8'
    });
    return this.http.post<Event>(this.resourceHost.concat('events'),
      JSON.stringify(model),{headers: headers});
  }
}
