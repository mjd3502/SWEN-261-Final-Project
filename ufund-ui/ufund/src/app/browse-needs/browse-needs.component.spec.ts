import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseNeedsComponent } from './browse-needs.component';

describe('BrowseNeedsComponent', () => {
  let component: BrowseNeedsComponent;
  let fixture: ComponentFixture<BrowseNeedsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BrowseNeedsComponent]
    });
    fixture = TestBed.createComponent(BrowseNeedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
