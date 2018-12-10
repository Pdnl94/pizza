import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OutlayService } from '../outlay.service';
import { Location } from '@angular/common';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-provider-delete',
  templateUrl: './provider-delete.component.html',
  styleUrls: ['./provider-delete.component.css']
})
export class ProviderDeleteComponent implements OnInit {
  id: number = null;
  title = 'Szolgáltató törlése';

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
      this.title = 'Szolgáltató törlése';
    } else {
      this.router.navigate(['/providers']);
    }
  }

  async onButtonClicked() {
    await this.outlayService.deleteProvider(this.id);
    this.location.back();
  }
}
