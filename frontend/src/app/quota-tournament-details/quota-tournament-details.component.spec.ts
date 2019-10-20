import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuotaTournamentDetailsComponent } from './quota-tournament-details.component';

describe('QuotaTournamentDetailsComponent', () => {
  let component: QuotaTournamentDetailsComponent;
  let fixture: ComponentFixture<QuotaTournamentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuotaTournamentDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuotaTournamentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
