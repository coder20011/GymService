import { Component } from '@angular/core';
import { Bmi } from '../bmi';
import { GymService } from '../gym.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-bmi-calculator',
  templateUrl: './bmi-calculator.component.html',
  styleUrls: ['./bmi-calculator.component.css']
})
export class BmiCalculatorComponent {

  bmiForm!: FormGroup;
  bmi!: Bmi;
  bmiInfo: string = "";

  constructor(private service: GymService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.bmiForm = new FormGroup({
      height: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]+(\.[0-9]+)?$/)]),
      weight: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]+(\.[0-9]+)?$/)])
    });
  }

  onSubmit(bmi: Bmi) {
    console.log(bmi);
    this.service.calculateBmi(bmi).subscribe((data) => {
      console.log(data);
      this.bmiInfo = data;
    });
  }

  onCancel() {

  }


}
