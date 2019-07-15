import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Cookie} from "ng2-cookies";
import {Type} from "../entity/type";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  private resourceHost: string;

  constructor(private http: HttpClient) {
    this.resourceHost = environment.resourceServer;
  }

  getTypes(): Observable<Type[]> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http.get<Type[]>(this.resourceHost.concat('types'), {headers: headers});
  }


}
