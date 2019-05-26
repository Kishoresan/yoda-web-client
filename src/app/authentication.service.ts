import { Injectable } from '@angular/core';

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
    const loggedInUser = sessionStorage.getItem('loggedInUser');
    console.log(!(loggedInUser === null));
    return !(loggedInUser === null);
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
