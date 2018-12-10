import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutlayFormComponent } from './outlay-form.component';

describe('OutlayFormComponent', () => {
  let component: OutlayFormComponent;
  let fixture: ComponentFixture<OutlayFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutlayFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutlayFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
