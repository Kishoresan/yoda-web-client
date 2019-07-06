import { TestBed } from '@angular/core/testing';

import { SystemVariableProviderService } from './system-variable-provider.service';

describe('SystemVariableProviderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SystemVariableProviderService = TestBed.get(SystemVariableProviderService);
    expect(service).toBeTruthy();
  });
});
