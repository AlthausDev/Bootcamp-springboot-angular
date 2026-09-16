
import { Component, ChangeDetectionStrategy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from '@my/core';
import { NotificationComponent } from './main/notification/notification.component';
import { HomeComponent } from "./main/home/home.component";
import { DemosComponent } from "./main/demos/demos.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NotificationComponent, HomeComponent, DemosComponent],
  templateUrl: './app.component.html',
  changeDetection: ChangeDetectionStrategy.Eager,
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
