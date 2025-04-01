import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-formularios',
  imports: [FormsModule],
  templateUrl: './formularios.component.html',
  styleUrl: './formularios.component.css'
})
export class FormulariosComponent {

  public elemento: any = {
    id: 1, 
    nombre: 'name', 
    apellidos: 'surname', 
    correo: 'email@email.com', 
    fAlta: '2023-10-01', 
    edad: 25,
    nif: '12345678A',
    telefono: '123456789',
    activo: true
  };

  public modo: 'editar' | 'nuevo' = 'nuevo';

  add() {
    this.elemento = {}
    this.modo = 'nuevo';
  }

  edit(key: number) {
    this.elemento = {
      id: key, 
      nombre: 'name', 
      apellidos: 'surname', 
      correo: 'email@email.com', 
      fAlta: '2023-10-01', 
      edad: 25,
      nif: '12345678A',
      telefono: '123456789',
      activo: true
    };
    this.modo = 'editar';
  }

  cancel() {
    this.elemento = {}
    this.modo = 'nuevo';
  }

  send() {
    switch (this.modo) {
      case 'nuevo':
        alert(`POST ${JSON.stringify(this.elemento)}`);
        break;
      case 'editar':
        alert(`PUT ${JSON.stringify(this.elemento)}`);
        break;
      default:
        alert('Modo no reconocido: ' + this.modo);
        break;
    }
  }
}
