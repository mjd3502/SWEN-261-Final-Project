import { TestBed } from '@angular/core/testing';

import { FavoritePetsService } from './favorite-pets.service';

describe('FavoritePetsService', () => {
  let service: FavoritePetsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FavoritePetsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
