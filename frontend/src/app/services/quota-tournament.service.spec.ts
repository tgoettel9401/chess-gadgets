import { TestBed } from '@angular/core/testing';

import { QuotaTournamentService } from './quota-tournament.service';

describe('QuotaTournamentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuotaTournamentService = TestBed.get(QuotaTournamentService);
    expect(service).toBeTruthy();
  });
});
