import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminBrowsePetsComponent } from './admin-browse-pets.component';

describe('AdminBrowsePetsComponent', () => {
  let component: AdminBrowsePetsComponent;
  let fixture: ComponentFixture<AdminBrowsePetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminBrowsePetsComponent]
    });
    fixture = TestBed.createComponent(AdminBrowsePetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
