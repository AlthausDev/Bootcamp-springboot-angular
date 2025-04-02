import { Injectable } from '@angular/core';
import { NotificationModel, NotificationType } from '../common-models/notification.model';
import { LoggerService } from '@my/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  public readonly NotificationType = NotificationType;
  private notifications: NotificationModel[] = [];
  private notificacion$ = new Subject<NotificationModel>();

  constructor(private out: LoggerService) {}

  public get Notifications(): NotificationModel[]{
    return Object.assign([], this.notifications);
  }

  public get IsThereAnyNotification(): boolean{
    return this.notifications.length > 0;
  }

  public get Notification(){
    return this.notificacion$.asObservable();
  }

  public add(Message: string, Type: NotificationType = NotificationType.error): void{
    if(!Message || Message.length === 0){
      this.out.error('NotificationService: El mensaje no puede estar vacio.');
      return;
    }

    const Id = this.IsThereAnyNotification ? this.notifications[this.notifications.length - 1].getId() + 1 : 1;
    const notification = new NotificationModel(Id, Type, Message);

    this.notifications.push(notification);
    this.notificacion$.next(notification);

    if(Type === NotificationType.error){
      this.out.error(`NOTIFICATION: ${Message}`);
    }
  }

  public getNotifications(): NotificationModel[]{
      return this.notifications.map(notification => {
        return new NotificationModel(notification.getId(), notification.getType(), notification.getMessage());
      });
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

  ngOnDestroy(): void {
    this.notificacion$.complete()
  }
}
