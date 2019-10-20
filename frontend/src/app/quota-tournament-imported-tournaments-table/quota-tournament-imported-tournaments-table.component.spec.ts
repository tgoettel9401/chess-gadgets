import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuotaTournamentImportedTournamentsTableComponent} from './quota-tournament-imported-tournaments-table.component';

describe('QuotaTournamentImportedTournamentsTableComponent', () => {
  let component: QuotaTournamentImportedTournamentsTableComponent;
  let fixture: ComponentFixture<QuotaTournamentImportedTournamentsTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuotaTournamentImportedTournamentsTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuotaTournamentImportedTournamentsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
