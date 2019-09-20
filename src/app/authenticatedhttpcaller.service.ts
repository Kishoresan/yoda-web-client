import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auth } from 'aws-amplify';

@Injectable({
    providedIn: 'root'
  })
  export abstract class AuthenticatedHttpCaller<T> {

    apiURL = 'http://localhost:8080/api';

    constructor(protected _http: HttpClient) {
    }

    protected async getAll(_servicePath: string): Promise <Observable<T[]>> {

      var _token = undefined;

      await Auth.currentAuthenticatedUser().then(function (user){
        _token = user.signInUserSession.idToken.jwtToken;
      });

      const _httpOptions = {
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + _token
        })
      };

      return this._http.get(this.apiURL + _servicePath, _httpOptions) as Observable<T[]>;
    }
  }