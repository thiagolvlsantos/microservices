import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GuiModule } from '../gui/gui.module';

const modules = [CommonModule, FormsModule, ReactiveFormsModule, GuiModule]

@NgModule({
  imports: [...modules],
  declarations: [],
  exports: [...modules]
})
export class ComumModule { }
