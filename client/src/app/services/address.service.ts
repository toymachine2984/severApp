import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Cookie} from "ng2-cookies";
import {Address} from "../entity/address";


@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private resourceHost: string;

  constructor(private http: HttpClient) {
    this.resourceHost = environment.resourceServer;
  }

  getAddress(): Observable<Address[]> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });

    return this.http.get<Address[]>(this.resourceHost.concat('address'), {headers: headers});
  }

  addAddress(model: string): Observable<Address> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + Cookie.get('access_token'),
      'Content-type': 'application/json;charset=utf-8'
    });
    return this.http.post<Address>(this.resourceHost.concat('address'), model,{headers: headers});
  }
}
