import { Component, Input, OnInit, OnChanges, Output, EventEmitter } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms' 
import { Income } from '../income';

@Component({
  selector: 'income-form',
  templateUrl: './income-form.component.html',
  styleUrls: ['./income-form.component.css']
})
export class IncomeFormComponent implements OnInit, OnChanges {

  incomeForm = this.fb.group({
    amount: ['', [Validators.required]],
    description: [''],
    date:  ['', [Validators.required]]
  });

  @Input() income: Income;
  @Output() save = new EventEmitter<Income>();

  get amount() { return this.incomeForm.get('amount'); }
  get description() { return this.incomeForm.get('description'); }
  get date() { return this.incomeForm.get('date'); }

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit() {
  }

  ngOnChanges() {
    this.incomeForm.patchValue(this.income);
  }

  onSubmit(button: string) {
    this.save.emit(
      Object.assign(new Income(), this.incomeForm.value)
    );
  }

}
