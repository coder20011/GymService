import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Calorie } from '../calorie';
import { FormArray, FormGroup } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Category } from '../category';
import { GymService } from '../gym.service';
import { Food } from '../food';

@Component({
  selector: 'app-calorie-calculator',
  templateUrl: './calorie-calculator.component.html',
  styleUrls: ['./calorie-calculator.component.css']
})
export class CalorieCalculatorComponent implements OnInit {

  calorieForm!: FormGroup;
  categoryList!: Category[];
  foodList: Food[][] = [[]];
  units: string = '';
  calorie!: Calorie;
  calorieList: Calorie[] = [];
  status: string = '';

  constructor(private fb: FormBuilder, private service: GymService) { }

  ngOnInit() {
    this.calorieForm = this.fb.group({
      calorieArray: this.fb.array([this.createCalorie()])
    });
    this.populateCategoryValues();
  }

  createCalorie(): FormGroup {
    return this.fb.group({
      categorySelect: [null, Validators.required],
      foodSelect: [null, Validators.required],
      quantity: [null, Validators.required]
    });
  }

  getCalorie(): FormArray {
    return this.calorieForm.get('calorieArray') as FormArray;
  }

  addCalorie() {
    (this.calorieForm.get('calorieArray') as FormArray).push(this.createCalorie());
  }

  deleteCalorie(index: number) {
    (this.calorieForm.get('calorieArray') as FormArray).removeAt(index);
    this.calorieList.pop();
  }

  populateCategoryValues(): void {
    this.service.getAllCategory().subscribe(
      (data) => {
        this.categoryList = data;
        console.log(data);
        console.log(this.categoryList);
      }
    );
  }

  populateFoodValues(foodCatId: number, index: number): void {
    console.log(foodCatId);
    this.service.getFoodByCategory(foodCatId).subscribe(
      (data) => {
        console.log(data);
        this.foodList[index] = data;
        console.log(this.foodList[index]);
      }
    );
  }

  onSelectCategory(index: number): void {
    const calorieArray = this.calorieForm.get('calorieArray') as FormArray;
    const calorieControl = calorieArray.at(index) as FormGroup;
    const categorySelect = calorieControl.get('categorySelect')?.value;
    // const foodSelect = calorieControl.get('foodSelect')?.value;
    // const quantity = calorieControl.get('quantity')?.value;
    console.log(categorySelect);
    console.log(categorySelect.categoryId);
    this.populateFoodValues(categorySelect.categoryId, index);

  }

  onSelectFood(index: number): void {
    const calorieArray = this.calorieForm.get('calorieArray') as FormArray;
    const calorieControl = calorieArray.at(index) as FormGroup;
    // const categorySelect = calorieControl.get('categorySelect')?.value;
    const foodSelect = calorieControl.get('foodSelect')?.value;
    console.log("****" + foodSelect.foodId);
    // const quantity = calorieControl.get('quantity')?.value;
    this.units = foodSelect.units;
    console.log(this.units);
  }

  submit() {
    this.calorieList = [];
    console.log(this.calorieForm.value);
    const calorieArray = this.calorieForm.get('calorieArray') as FormArray;
    calorieArray.controls.forEach((control) => {
      console.log(control.value);
      this.calorie = {
        foodId: control.value.foodSelect.foodId,
        quantity: control.value.quantity
      };
      this.calorieList.push(this.calorie);
      console.log(this.calorieList);
    });

    this.service.calculateTotalCalories(this.calorieList).subscribe(
      (data) => {
        console.log(data);
        this.status = data;
      }
    )
  }

}