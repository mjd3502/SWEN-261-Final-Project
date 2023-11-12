import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterModule } from '@angular/router';
import { BrowsePetsComponent } from './browse-pets.component';

describe('BrowseNeedsComponent', () => {
  let component: BrowsePetsComponent;
  let fixture: ComponentFixture<BrowsePetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BrowsePetsComponent],
      imports: [RouterModule]
    });
    fixture = TestBed.createComponent(BrowsePetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
