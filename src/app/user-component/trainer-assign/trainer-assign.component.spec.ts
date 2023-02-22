import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerAssignComponent } from './trainer-assign.component';

describe('TrainerAssignComponent', () => {
  let component: TrainerAssignComponent;
  let fixture: ComponentFixture<TrainerAssignComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrainerAssignComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainerAssignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
