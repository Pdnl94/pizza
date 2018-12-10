import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IncomeService } from '../income.service';
import { Location } from '@angular/common';
import { AuthService } from '../auth.service';
@Component({
  selector: 'income-delete',
  templateUrl: './income-delete.component.html',
  styleUrls: ['./income-delete.component.css']
})
export class IncomeDeleteComponent implements OnInit {

  id: number = null;
  title = 'Bevétel törlése';

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
      this.title = 'Pizza törlése';
    } else {
      this.router.navigate(['/incomes']);
    }
  }

  async onButtonClicked() {
    await this.incomeService.deleteIncome(this.id);
    this.location.back();
  }
}