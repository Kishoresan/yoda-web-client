import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Country } from '../app/entity/Country';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  apiURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getCountries() {
    return this.http.get<Country[]>(this.apiURL + '/country');
  }

}
