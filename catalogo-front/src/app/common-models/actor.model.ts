/* eslint-disable @typescript-eslint/no-inferrable-types */
/* eslint-disable @typescript-eslint/no-explicit-any */

export interface IActor{
    [index: string]: any;
    id?: number
    tratamiento?: string
    nombre?: string
    apellidos?: string
    telefono?: string
    email?: string
    sexo?: string
    nacimiento?: string
    avatar?: string
    conflictivo?: boolean
    icono?: string
  }
  
  export class Actor implements IActor {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    [index: string]: any;
    constructor(
      public id: number = 0,
      private _tratamiento?: string,
      public nombre?: string,
      public apellidos?: string,
      public telefono?: string,
      public email?: string,
      public sexo: string = 'H',
      public nacimiento?: string,
      public avatar?: string,
      public conflictivo: boolean = false,
    ) { }
    get tratamiento() { return this._tratamiento }
    set tratamiento(value: string | undefined) {
      if(this._tratamiento === value) return
      this._tratamiento = value
      if(!this._tratamiento) return
      this.sexo = this._tratamiento.endsWith('a.') ? 'M' : 'H'
    }
  }