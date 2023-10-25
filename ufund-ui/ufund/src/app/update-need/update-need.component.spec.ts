import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateNeedComponent } from './update-need.component';

describe('UpdateNeedComponent', () => {
  let component: UpdateNeedComponent;
  let fixture: ComponentFixture<UpdateNeedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateNeedComponent]
    });
    fixture = TestBed.createComponent(UpdateNeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
