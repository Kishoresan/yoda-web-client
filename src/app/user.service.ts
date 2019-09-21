import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticatedHttpCaller } from './authenticatedhttpcaller.service';
import { User } from './entity/User';
import { Address } from './entity/Address';
import { PhoneNumber } from './entity/PhoneNumber';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AuthenticatedHttpCaller <User>{

  private _servicePath = '/user'

  public getUserByEmail(_email: string) : Promise<Observable<User>> {
    return super._get(this._servicePath + '/email/' + _email);
  }

  public save(_user: User) {
    return super._save(this._servicePath, _user);
  }

  public update(_user: User) {
    return super._update(this._servicePath, _user);
  }
}

@Injectable({
  providedIn: 'root'
})
export class AddressService extends AuthenticatedHttpCaller<Address> {

  private _servicePath = '/userAddress'

  public getByUserId(_userId: string): Promise<Observable<Address>> {
    return this._get(this._servicePath + '/userId/' + _userId);
  }

  public getByEmailId(_email: string): Promise<Observable<Address>> {
    return this._get(this._servicePath + '/email/' + _email);
  }

  public save(_address: Address) {
    return super._save(this._servicePath, _address);
  }

  public update(_address: Address) {
    return super._update(this._servicePath, _address);
  }
}

@Injectable({
  providedIn: 'root'
})
export class PhoneNumberService extends AuthenticatedHttpCaller<PhoneNumber> {

  private _servicePath = '/phoneNumber'

  public getByUserId(_userId: string): Promise<Observable<PhoneNumber>> {
    return this._get(this._servicePath + '/userId/' + _userId);
  }

  public save(_phoneNumber: PhoneNumber) {
    return super._save(this._servicePath, _phoneNumber);
  }

  public update(_phoneNumber: PhoneNumber) {
    return super._update(this._servicePath, _phoneNumber);
  }
}