import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OutlayService } from '../outlay.service';
import { Location } from '@angular/common';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-outlay-delete',
  templateUrl: './outlay-delete.component.html',
  styleUrls: ['./outlay-delete.component.css']
})
export class OutlayDeleteComponent implements OnInit {

  id: number = null;
  title = 'Kiadás törlése';

  constructor(
    private route: ActivatedRoute,
    private outlayService: OutlayService,
    private location: Location,
    private router: Router,
    private authService: AuthService
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.title = 'Kiadás törlése';
    } else {
      this.router.navigate(['/outlay']);
    }
  }

  async onButtonClicked() {
    await this.outlayService.deleteOutlay(this.id);
    this.location.back();
  }
}
