import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { Provider } from '../provider';
import { Validators, FormBuilder } from '@angular/forms';
import { OutlayService } from '../outlay.service';

@Component({
  selector: 'provider-form',
  templateUrl: './provider-form.component.html',
  styleUrls: ['./provider-form.component.css']
})
export class ProviderFormComponent implements OnInit {

  providerForm = this.fb.group({
    name: ['', [Validators.required]]
  });

  @Input() provider: Provider;
  @Output() save = new EventEmitter<Provider>();

  get name() { return this.providerForm.get('price'); }

  constructor(
    private fb: FormBuilder,
    private outlayService: OutlayService
  ) {}

  ngOnInit() {}

  ngOnChanges() {
    this.providerForm.patchValue(this.provider);
  }
  
  onSubmit() {
    console.log(this.providerForm.value);
    this.save.emit(
      Object.assign(new Provider(), this.providerForm.value)
    );
  }
}
