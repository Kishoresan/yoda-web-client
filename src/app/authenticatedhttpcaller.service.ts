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

    protected async _getAll(_servicePath: string): Promise <Observable<T[]>> {
      const _httpOptions = await this._getHttpOptions();
      return this._http.get(this.apiURL + _servicePath, _httpOptions) as Observable<T[]>;
    }

    protected async _get(_servicePath: string): Promise <Observable<T>> {
      const _httpOptions = await this._getHttpOptions();
      return this._http.get(this.apiURL + _servicePath, _httpOptions) as Observable<T>;
    }

    protected async _save(_servicePath: string, _t: T) {
      const _httpOptions = await this._getHttpOptions();
      return this._http.post<T>(this.apiURL + _servicePath, _t, _httpOptions).subscribe(
        (res) => console.log(res),
        (err) => console.log(err)
      );
    }

    protected async _saveAll(_servicePath: string, _t: T[]) {
      const _httpOptions = await this._getHttpOptions();
      return this._http.post<T[]>(this.apiURL + _servicePath, _t, _httpOptions).subscribe(
        (res) => console.log(res),
        (err) => console.log(err)
      );
    }

    protected async _update(_servicePath: string, _t: T) {
      const _httpOptions = await this._getHttpOptions();
      return this._http.put<T>(this.apiURL + _servicePath, _t, _httpOptions).subscribe(
        (res) => console.log('success' + res),
        (err) => console.log('error' + err)
      );
    }

    private async _getHttpOptions() {

      let _token;

      await Auth.currentAuthenticatedUser().then(function (user) {
        _token = user.signInUserSession.idToken.jwtToken;
      });

      return  {
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + _token
        })
      };
    }
  }
