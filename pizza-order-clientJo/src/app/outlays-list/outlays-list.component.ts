import { Component, OnInit } from '@angular/core';
import { OutlayService } from '../outlay.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'outlays-list',
  templateUrl: './outlays-list.component.html',
  styleUrls: ['./outlays-list.component.css']
})
export class OutlaysListComponent implements OnInit {

  outlays = []

  constructor(private outlayService: OutlayService, private authService: AuthService) { }

  async ngOnInit() {
    this.outlays = await this.outlayService.getOutlays();
    this.authService.getBalance();
  }

}
