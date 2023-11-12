import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowsePetsComponent } from './browse-pets.component';

describe('BrowseNeedsComponent', () => {
  let component: BrowsePetsComponent;
  let fixture: ComponentFixture<BrowsePetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BrowsePetsComponent]
    });
    fixture = TestBed.createComponent(BrowsePetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
