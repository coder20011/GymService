import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedTrainerComponent } from './assigned-trainer.component';

describe('AssignedTrainerComponent', () => {
  let component: AssignedTrainerComponent;
  let fixture: ComponentFixture<AssignedTrainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignedTrainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignedTrainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
