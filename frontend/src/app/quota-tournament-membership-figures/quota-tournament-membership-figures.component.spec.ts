import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuotaTournamentMembershipFiguresComponent} from './quota-tournament-membership-figures.component';

describe('QuotaTournamentMembershipFiguresComponent', () => {
  let component: QuotaTournamentMembershipFiguresComponent;
  let fixture: ComponentFixture<QuotaTournamentMembershipFiguresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuotaTournamentMembershipFiguresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuotaTournamentMembershipFiguresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
