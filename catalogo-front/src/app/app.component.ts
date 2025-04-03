import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AjaxWaitComponent, HeaderComponent, NotificationComponent } from './main';
import { NavigationService } from './common-services';
import { FooterComponent } from './main/footer/footer.component';
import { LanguagesComponent } from './language';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NotificationComponent, AjaxWaitComponent, FooterComponent, HeaderComponent, LanguagesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor(_nav: NavigationService) { }
}