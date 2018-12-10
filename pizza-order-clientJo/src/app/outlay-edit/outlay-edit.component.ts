import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { OutlayService } from '../outlay.service';
import { Outlay } from '../outlay';
import { Location } from '@angular/common';

@Component({
  selector: 'outlay-edit',
  templateUrl: './outlay-edit.component.html',
  styleUrls: ['./outlay-edit.component.css']
})
export class OutlayEditComponent implements OnInit {

  id: number = null;
  outlay: Outlay = new Outlay();
  title = 'Új kiadás hozzáadása';

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
      this.outlay = await this.outlayService.getOutlay(this.id);
      this.title = 'Kiadás szerkesztése';
    }
  }

  async onFormSave(outlay: Outlay) {
    if (this.id) {
      await this.outlayService.modifyOutlay(this.id, outlay)
      this.location.back();
    } else {
      await this.outlayService.addOutlay(outlay);
      this.router.navigate(['/outlays']);
    }
  }

}
