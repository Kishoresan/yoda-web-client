import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { PhoneType } from './entity/PhoneType';

@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  apiURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getPhoneTypes() {
    return this.http.get<PhoneType[]>(this.apiURL + '/phoneType');
  }
}
