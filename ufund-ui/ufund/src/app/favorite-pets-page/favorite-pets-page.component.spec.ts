import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FavoritePetsPageComponent } from './favorite-pets-page.component';

describe('FavoritePetsComponent', () => {
  let component: FavoritePetsPageComponent;
  let fixture: ComponentFixture<FavoritePetsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FavoritePetsPageComponent]
    });
    fixture = TestBed.createComponent(FavoritePetsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
