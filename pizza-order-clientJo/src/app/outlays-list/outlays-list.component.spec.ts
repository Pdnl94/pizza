import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutlaysListComponent } from './outlays-list.component';

describe('OutlaysListComponent', () => {
  let component: OutlaysListComponent;
  let fixture: ComponentFixture<OutlaysListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutlaysListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutlaysListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
