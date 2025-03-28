import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from '@my/core';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'demo-ng';

  constructor(logger: LoggerService) {
    logger.log('AppComponent created LOG');
    logger.info('AppComponent created INFO');
    logger.warn('AppComponent created WARN');
    logger.error('AppComponent created ERROR');
  }
}
