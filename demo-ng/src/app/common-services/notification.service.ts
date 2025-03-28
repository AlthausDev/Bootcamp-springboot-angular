import { Injectable } from '@angular/core';
import { NotificationType } from '../common-models/notification';
import { LoggerService } from '@my/core';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  public readonly NotificationType = NotificationType;
  private notifications: { id: number; message: string; type: NotificationType }[] = [];

  constructor(private out: LoggerService) {}

  public get Notifications(): Notification[]{
    return Object.assign([], this.notifications);
  }

  public get IsThereAnyNotification(): boolean{
    return this.notifications.length > 0;
  }

  public add(message: string, type: NotificationType = NotificationType.error): void{
    if(!message || message.length === 0){
      this.out.error('NotificationService: El mensaje no puede estar vacio.');
      return;
    }

    const id = this.IsThereAnyNotification ? this.notifications[this.notifications.length - 1].id + 1 : 1;

    this.notifications.push({ id, message, type });

    if(type === NotificationType.error){
      this.out.error(`NOTIFICATION: ${message}`);
    }
  }

  public remove(index: number): void{
    if(index < 0 || index >= this.notifications.length){
      this.out.error('NotificationService: El indice no es valido.');
      return;
    }

    this.notifications.splice(index, 1);
  }

  public clear(): void{
    if(this.IsThereAnyNotification){
      this.notifications = [];
    }
  }
}
