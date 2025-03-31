import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from '@my/core';
import { NotificationComponent } from './main/notification/notification.component';
import { HomeComponent } from "./main/home/home.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, NotificationComponent, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  // constructor(logger: LoggerService) {
  //   logger.log('AppComponent created LOG');
  //   logger.info('AppComponent created INFO');
  //   logger.warn('AppComponent created WARN');
  //   logger.error('AppComponent created ERROR');
  // }
}
