import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticatedHttpCaller } from './authenticatedhttpcaller.service';
import { User } from './entity/User';
import { Address } from './entity/Address';
import { PhoneNumber } from './entity/PhoneNumber';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AuthenticatedHttpCaller <User> {

  private _servicePath = '/user';

  public find(): Promise<Observable<User>> {
    return super._get(this._servicePath);
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

  private _servicePath = '/userAddress';

  public find(): Promise<Observable<Address>> {
    return super._get(this._servicePath);
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

  private _servicePath = '/phoneNumber';

  public getByUserId(_userId: string): Promise<Observable<PhoneNumber>> {
    return this._get(this._servicePath + '/userId/' + _userId);
  }

  public getByEmailId(_email: string): Promise<Observable<PhoneNumber[]>> {
    return this._getAll(this._servicePath + '/email/' + _email);
  }

  public saveAll(_phoneNumbers: PhoneNumber[]) {
    return super._saveAll(this._servicePath, _phoneNumbers);
  }

  public update(_phoneNumber: PhoneNumber) {
    return super._update(this._servicePath, _phoneNumber);
  }
}
