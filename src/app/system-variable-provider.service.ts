import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SystemVariableProviderService {
  public SYSTEM_PARAMS = {
    REGION: '',
    COGNITO_POOL: {
      UserPoolId: 'us-east-2_v4sPO06sg',
      ClientId: '7vs0rjph9obratoc96p0aoffb0'
    }
  };
}
