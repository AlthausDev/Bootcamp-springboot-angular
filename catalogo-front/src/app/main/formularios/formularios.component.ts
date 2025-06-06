import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe } from "../../../lib/my-core/pipes/strings.pipe";

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { NotificationService } from 'src/app/common-services';
import { NIFNIEValidator, TypeValidator, UppercaseValidator } from '@my/core';

export abstract class RESTDAOService<T, K> {
  protected baseUrl = environment.apiUrl;
  protected http = inject(HttpClient)

  constructor(entidad: string, protected option = {}) {
    this.baseUrl += entidad;
  }
  query(extras = {}): Observable<T[]> {
    return this.http.get<T[]>(this.baseUrl, Object.assign({}, this.option, extras));
  }
  get(id: K, extras = {}): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${id}`, Object.assign({}, this.option, extras));
  }
  add(item: T, extras = {}): Observable<T> {
    return this.http.post<T>(this.baseUrl, item, Object.assign({}, this.option, extras));
  }
  change(id: K, item: T, extras = {}): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${id}`, item, Object.assign({}, this.option, extras));
  }
  remove(id: K, extras = {}): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${id}`, Object.assign({}, this.option, extras));
  }
}


@Injectable({providedIn: 'root'})
export class PersonasDaoService extends RESTDAOService<any, number> {
  constructor() {
    super('personas')
  }

}

@Component({
  selector: 'app-formularios',
  imports: [FormsModule, ErrorMessagePipe, NIFNIEValidator, TypeValidator, UppercaseValidator],
  templateUrl: './formularios.component.html',
  styleUrl: './formularios.component.css'
})
export class FormulariosComponent {

  constructor(private dao: PersonasDaoService, private notify: NotificationService) {}

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
    // this.elemento = { id: key, nombre: 'Pepito', apellidos: 'Grillo', correo: 'pgrillo@example.com', fAlta: '2025-01-01', edad: 99, nif: '12345678z', activo: true}
    // this.modo = 'edit'
    this.dao.get(key).subscribe({
      next: datos => {
        this.elemento = datos
        this.modo = 'editar'
      },
      error: err => this.notify.add(JSON.stringify(err))
    })
  }

  cancel() {
    this.elemento = {}
    this.modo = 'nuevo';
  }

  send() {
    switch(this.modo) {
      case 'nuevo':
         this.dao.add(this.elemento).subscribe({
          next: () => this.cancel(),
          error: err => this.notify.add(JSON.stringify(err))
        })
        //  alert(`POST ${JSON.stringify(this.elemento)}`)
        // this.cancel()
        break
      case 'editar':
        this.dao.change(this.elemento.id, this.elemento).subscribe({
          next: () => this.cancel(),
          error: err => this.notify.add(JSON.stringify(err))
        })
        // alert(`PUT ${JSON.stringify(this.elemento)}`)
        // this.cancel()
        break
    }
  }
}
