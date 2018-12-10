import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutlayEditComponent } from './outlay-edit.component';

describe('OutlayEditComponent', () => {
  let component: OutlayEditComponent;
  let fixture: ComponentFixture<OutlayEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutlayEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutlayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
