import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBrowseNeedsComponent } from './admin-browse-needs.component';

describe('AdminBrowseNeedsComponent', () => {
  let component: AdminBrowseNeedsComponent;
  let fixture: ComponentFixture<AdminBrowseNeedsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminBrowseNeedsComponent]
    });
    fixture = TestBed.createComponent(AdminBrowseNeedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
