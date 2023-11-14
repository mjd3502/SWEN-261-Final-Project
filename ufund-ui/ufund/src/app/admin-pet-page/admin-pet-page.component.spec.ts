import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminPetPageComponent } from './admin-pet-page.component';

describe('AdminPetPageComponent', () => {
  let component: AdminPetPageComponent;
  let fixture: ComponentFixture<AdminPetPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminPetPageComponent]
    });
    fixture = TestBed.createComponent(AdminPetPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
