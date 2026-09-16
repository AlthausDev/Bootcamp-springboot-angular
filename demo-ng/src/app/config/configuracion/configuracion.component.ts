import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-configuracion',
    templateUrl: './configuracion.component.html',
    styleUrls: ['./configuracion.component.css'],
    changeDetection: ChangeDetectionStrategy.Eager,
    imports: [RouterLink]
})
export class ConfiguracionComponent {

}
