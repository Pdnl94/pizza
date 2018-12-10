import { Component, OnInit } from '@angular/core';
import { OutlayService } from '../outlay.service';

@Component({
  selector: 'provider-list',
  templateUrl: './provider-list.component.html',
  styleUrls: ['./provider-list.component.css']
})
export class ProviderListComponent implements OnInit {

  providers = []

  constructor(private outlayService: OutlayService) { }

  async ngOnInit() {
    this.providers = await this.outlayService.getProviders();
  }

}
