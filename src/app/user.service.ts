import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { User } from './entity/User';
import { Address } from './entity/Address';

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

  getUserByEmail(username) {
    return this.http.get<User>(this.apiURL + '/user/email/' + username);
  }

  updateUser(user) {
    return this.http.put<User>(this.apiURL + '/user', user).subscribe(
      (res) => console.log(res),
      (err) => console.log(err)
    );
  }

  getUserAddress(username) {
     return this.http.get<Address>(this.apiURL + '/userAddress/userName/' + username);
  }

  saveOrUpdateUserAddress(userId, address) {
    address.userId = userId;

    return this.http.put<Address>(this.apiURL + '/userAddress/', address).subscribe(
      (res) => console.log(res),
      (err) => console.log(err)
    );
  }


}
