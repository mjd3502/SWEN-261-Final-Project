import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingPageNavbarComponent } from './landing-page-navbar.component';

describe('LandingPageNavbarComponent', () => {
  let component: LandingPageNavbarComponent;
  let fixture: ComponentFixture<LandingPageNavbarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LandingPageNavbarComponent]
    });
    fixture = TestBed.createComponent(LandingPageNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
