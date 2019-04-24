import { TestBed } from '@angular/core/testing';

import { DewisService } from './dewis.service';

describe('DewisService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DewisService = TestBed.get(DewisService);
    expect(service).toBeTruthy();
  });
});
