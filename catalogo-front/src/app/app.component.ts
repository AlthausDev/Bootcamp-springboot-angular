import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AjaxWaitComponent, HeaderComponent, NotificationComponent } from './main';
import { NavigationService } from './common-services';
import { FooterComponent } from './main/footer/footer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NotificationComponent, AjaxWaitComponent, FooterComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor(_nav: NavigationService) { }
}