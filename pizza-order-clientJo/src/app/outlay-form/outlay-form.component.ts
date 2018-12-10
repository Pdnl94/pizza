import { Component, Input, OnInit, OnChanges, Output, EventEmitter, Provider } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms'
import { Outlay } from '../outlay';
import { OutlayService } from '../outlay.service';

@Component({
  selector: 'outlay-form',
  templateUrl: './outlay-form.component.html',
  styleUrls: ['./outlay-form.component.css']
})
export class OutlayFormComponent implements OnInit, OnChanges {

  providers = [];

  outlayForm = this.fb.group({
    price: ['', [Validators.required]],
    provider: ['', [Validators.required]],
    date: ['', [Validators.required]]
  });

  @Input() outlay: Outlay;
  @Output() save = new EventEmitter<Outlay>();

  get price() { return this.outlayForm.get('price'); }
  get provider() { return this.outlayForm.get('provider'); }
  get date() { return this.outlayForm.get('date'); }

  constructor(
    private fb: FormBuilder,
    private outlayService: OutlayService
  ) {}

  async ngOnInit() {
    this.providers = await this.outlayService.getProviders();
  }

  ngOnChanges() {
    this.outlayForm.patchValue(this.outlay);
  }
  
  onSubmit() {
    console.log(this.outlayForm.value);
    this.save.emit(
      Object.assign(new Outlay(), this.outlayForm.value)
    );
  }

}
