import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { User } from './entity/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getPhoneTypes() {
    return this.http.get<User[]>(this.apiURL + '/user');
  }

  saveUser(user) {
    return this.http.post<User>(this.apiURL + '/user', user).subscribe(
      (res) => console.log(res),
      (err) => console.log(err)
    );
  }


}
