import { CommonModule } from '@angular/common';
import { Component, computed, OnDestroy, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CapitalizePipe, ElipsisPipe } from '@my/core';
import { Unsubscribable } from 'rxjs';
import { NotificationType } from 'src/app/common-models/notification.model';
import { NotificationService } from 'src/app/common-services';
import { SizerComponent } from "../../../lib/my-core/components/sizer.component";
import { FormButtonsComponent } from "../../common-components/form-buttons/form-buttons.component";
import { CardComponent } from "../../common-components/card.component";
import { FormulariosComponent } from "../formularios/formularios.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-demos',
  imports: [FormsModule, CommonModule, ElipsisPipe, CapitalizePipe, SizerComponent, FormButtonsComponent, CardComponent, FormulariosComponent, RouterModule],
  templateUrl: './demos.component.html',
  styleUrl: './demos.component.css'
})
export class DemosComponent implements OnInit, OnDestroy {

    private fecha: Date = new Date('2025-03-31T00:00:00Z');
    public readonly nombre = signal<string>('Demos Component');
    public readonly fontSize = signal<number>(24);
    public readonly notifications = signal([
      {id: 1, nombre: 'Notificación 1', tipo: NotificationType.error, mensaje: 'Error en la aplicación'},
      {id: 2, nombre: 'Notificación 2', tipo: NotificationType.info, mensaje: 'Información importante'},
      {id: 3, nombre: 'Notificación 3', tipo: NotificationType.warn, mensaje: 'Operación exitosa'},
      {id: 4, nombre: 'Notificación 4', tipo: NotificationType.log, mensaje: 'Advertencia de seguridad'},
    ]);

    public readonly listado = signal([
      {id: 1, nombre: 'Madrid'},
      {id: 2, nombre: 'BARCELONA'},
      {id: 3, nombre: 'valencia'},
      {id: 4, nombre: 'A coruña'},
    ]);

    public readonly idProvincia = signal<number>(2);

    public resultado = signal<string>('');
    public visible = signal<boolean>(true);
    public invisible = computed<boolean>(() => !this.visible());
    public readonly estetica = signal({importante: true, urgente: true, error: false})


    constructor(public notificationViewModel: NotificationService) {}


    public get Fecha(): string {
        return this.fecha.toISOString();
    }

    public set Fecha(value: string) {
        this.fecha = new Date(value);
    }


    saluda(){
        this.resultado.set(`Hola ${this.nombre()}`);
    }

    despide(){
        this.resultado.set(`Adios ${this.nombre()}`);
    }

    di(algo: string){
        this.resultado.set("Dice: " + algo);
    }

    cambiaEstetica(){
        this.visible.update(v => !v);
        this.estetica.update(v => ({...v, importante: !v.importante}));
        this.estetica.update(v => ({...v, urgente: !v.urgente}));
        this.estetica.update(v => ({...v, error: !v.error}));
    }
    
    add(provicia: string){
      const id = this.listado().length + 1;
      this.listado.update(v => [...v, {id: id, nombre: provicia}]);
      this.idProvincia.set(id);
      this.notificationViewModel.add(`Se ha añadido la provincia ${provicia}`, NotificationType.info);
    }

    calcula(a: number, b:number) { return a + b; }
    
    private suscriptor?: Unsubscribable;

    ngOnInit(): void {
      this.suscriptor = this.notificationViewModel.Notification.subscribe(n => {
      if (n.getType() !== NotificationType.error) { return; }
      window.alert(`Suscripción: ${n.getMessage}`);
      const lastIndex = this.notificationViewModel.getNotifications().length - 1;
      this.notificationViewModel.remove(lastIndex);
      });
      }
     
      ngOnDestroy(): void {
        if (this.suscriptor) {
          this.suscriptor.unsubscribe();
          }         
      }
}