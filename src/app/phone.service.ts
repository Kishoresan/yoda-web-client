import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PhoneType } from './entity/PhoneType';
import { AuthenticatedHttpCaller } from './authenticatedhttpcaller.service';

@Injectable({
  providedIn: 'root'
})
export class PhoneService extends AuthenticatedHttpCaller<PhoneType> {

  private _servicePath = '/phoneType'

  public getAll(): Promise <Observable<PhoneType[]>>{
    return super._getAll(this._servicePath);
  }
}