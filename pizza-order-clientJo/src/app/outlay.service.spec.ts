import { TestBed } from '@angular/core/testing';

import { OutlayService } from './outlay.service';

describe('OutlayService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OutlayService = TestBed.get(OutlayService);
    expect(service).toBeTruthy();
  });
});
