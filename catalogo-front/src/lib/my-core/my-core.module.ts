import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PIPES_STRINGS } from './pipes/strings.pipe';
import { SizerComponent } from './components/sizer.component';
import GraficoSvgComponent from './components/grafico-svg/grafico-svg.component';
import { PIPES_NUMBERS } from './pipes/numbers.pipe';

@NgModule({
  declarations: [ ],
  imports: [
    CommonModule, PIPES_STRINGS, PIPES_NUMBERS, SizerComponent, GraficoSvgComponent,
  ],
  exports: [ PIPES_STRINGS, PIPES_NUMBERS, SizerComponent, GraficoSvgComponent, ],
})
export class MyCoreModule { }
