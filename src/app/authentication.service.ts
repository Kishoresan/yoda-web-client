import { Injectable } from '@angular/core';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  authenticate(username, password) {
    if (this.authenticateUsingCognito(username, password)) {
      sessionStorage.setItem('loggedInUser', username);
      return true;
    } else {
      return false;
    }
  }

  isUserLoggedIn() {
    return sessionStorage.getItem('loggedInUser') !== null;
  }

  getLoggedInUser() {
    return sessionStorage.getItem('loggedInUser');
  }

  logOut() {
    sessionStorage.removeItem('loggedInUser');
  }

  authenticateUsingCognito(username, password) {
    return true;
  }
}
