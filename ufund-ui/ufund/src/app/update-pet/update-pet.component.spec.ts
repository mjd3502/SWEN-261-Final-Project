import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePetComponent } from './update-pet.component';

describe('UpdatePetComponent', () => {
  let component: UpdatePetComponent;
  let fixture: ComponentFixture<UpdatePetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatePetComponent]
    });
    fixture = TestBed.createComponent(UpdatePetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
