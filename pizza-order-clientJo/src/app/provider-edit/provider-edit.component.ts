import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OutlayService } from '../outlay.service';
import { Location } from '@angular/common';
import { Provider } from '../provider';

@Component({
  selector: 'provider-edit',
  templateUrl: './provider-edit.component.html',
  styleUrls: ['./provider-edit.component.css']
})
export class ProviderEditComponent implements OnInit {

  id: number = null;
  provider: Provider = new Provider();
  title = 'Új szolgáltató hozzáadása';

  constructor(
    private route: ActivatedRoute,
    private outlayService: OutlayService,
    private location: Location,
    private router: Router,
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.provider = await this.outlayService.getProvider(this.id);
      this.title = 'Szolgáltató szerkesztése';
    }
  }

  async onFormSave(provider: Provider) {
    if (this.id) {
      await this.outlayService.modifyProvider(this.id, provider)
      this.location.back();
    } else {
      await this.outlayService.addProvider(provider);
      this.router.navigate(['/providers']);
    }
  }
}
