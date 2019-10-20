import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuotaTournamentImportedTournamentsComponent } from './quota-tournament-imported-tournaments.component';

describe('QuotaTournamentImportedTournamentsComponent', () => {
  let component: QuotaTournamentImportedTournamentsComponent;
  let fixture: ComponentFixture<QuotaTournamentImportedTournamentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuotaTournamentImportedTournamentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuotaTournamentImportedTournamentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
