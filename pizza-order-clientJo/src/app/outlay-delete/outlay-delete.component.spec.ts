import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutlayDeleteComponent } from './outlay-delete.component';

describe('OutlayDeleteComponent', () => {
  let component: OutlayDeleteComponent;
  let fixture: ComponentFixture<OutlayDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutlayDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutlayDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
