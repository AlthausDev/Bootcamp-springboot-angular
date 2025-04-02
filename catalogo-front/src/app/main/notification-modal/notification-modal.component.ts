import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { NotificationService } from 'src/app/common-services/notification.service';

@Component({
  selector: 'app-notification-modal',
  imports: [NgClass],
  templateUrl: './notification-modal.component.html',
  styleUrl: './notification-modal.component.css'
})
export class NotificationModalComponent {

    constructor(private notificationViewModel: NotificationService) {}

    public get NotificationVewModel() {
        return this.notificationViewModel;
    }


}
