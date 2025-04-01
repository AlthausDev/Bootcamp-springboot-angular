import { Routes } from '@angular/router';
import { DemosComponent, FormulariosComponent, HomeComponent } from './main';

export const routes: Routes = [
{path: '/', component: HomeComponent, pathMatch: 'full'},
{path: '/home', component: HomeComponent},
{path: '/demos', component: DemosComponent, title: 'Demos', pathMatch: 'full'},
{path: '/demos/:id', component: DemosComponent, title: 'Demos'},
{path: '/personas', component: FormulariosComponent},
{path: '/personas/add', component: FormulariosComponent},
{path: '/personas/:id', component: FormulariosComponent},
{path: '/personas/:id/editar', component: FormulariosComponent}

];
