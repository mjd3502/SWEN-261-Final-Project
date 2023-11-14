import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelperPetPageComponent } from './helper-pet-page.component';

describe('HelperPetPageComponent', () => {
  let component: HelperPetPageComponent;
  let fixture: ComponentFixture<HelperPetPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HelperPetPageComponent]
    });
    fixture = TestBed.createComponent(HelperPetPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
