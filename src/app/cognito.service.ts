import { Injectable } from '@angular/core';

import { SystemVariableProviderService } from './system-variable-provider.service';


@Injectable({
  providedIn: 'root'
})
export class CognitoService {

  private POOL_SETTING = new SystemVariableProviderService().SYSTEM_PARAMS.COGNITO_POOL;

  signUp(email, password) {

  }

  getLoggedUser() {
    return sessionStorage.getItem('loggedInUser');
  }
}
