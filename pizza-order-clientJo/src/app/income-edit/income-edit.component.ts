import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IncomeService } from '../income.service';
import { Income } from '../income';
import { Location } from '@angular/common';
import { AuthService } from '../auth.service';
@Component({
  selector: 'income-edit',
  templateUrl: './income-edit.component.html',
  styleUrls: ['./income-edit.component.css']
})
export class IncomeEditComponent implements OnInit {

  id: number = null;
  income: Income = new Income();
  title = 'Új pizza hozzáadása';

  constructor(
    private route: ActivatedRoute,
    private incomeService: IncomeService,
    private location: Location,
    private router: Router,
    private authService: AuthService
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.income = await this.incomeService.getIncome(this.id);
      this.title = 'Pizza szerkesztése';
    }
  }

  async onFormSave(income: Income) {
    if (this.id) {
      await this.incomeService.modifyIncome(this.id, income)
      this.location.back();
    } else {
      await this.incomeService.addIncome(income);
      this.router.navigate(['/incomes']);
    }
  }

}