import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from '../app/entity/Country';
import { AuthenticatedHttpCaller } from './authenticatedhttpcaller.service';

@Injectable({
  providedIn: 'root'
})
export class CountryService extends AuthenticatedHttpCaller<Country> {

  private _servicePath = '/country'

  public getCountries(): Promise <Observable<Country[]>>{
    return super.getAll(this._servicePath);
  }
}
