import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

const modules = [CommonModule, NgbModule]

@NgModule({
  imports: [
    ...modules,
    NgbModule.forRoot()
  ],
  declarations: [],
  exports: [
    ...modules
  ]
})
export class GuiModule { }