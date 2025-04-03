import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LANGUAGES_COMPONENTES } from './componente.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    LANGUAGES_COMPONENTES,
    RouterModule
  ]
})
export class ModuloModule { }
