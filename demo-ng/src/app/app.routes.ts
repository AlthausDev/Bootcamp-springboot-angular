import { Routes } from '@angular/router';
import { DemosComponent, FormulariosComponent, HomeComponent } from './main';
import { ContactosAddComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './contactos';

export const routes: Routes = [
    { path: 'contactos', children: [
        { path: '', component: ContactosListComponent},
        { path: 'add', component: ContactosAddComponent},
        { path: ':id/edit', component: ContactosEditComponent},
        { path: ':id', component: ContactosViewComponent},
        { path: ':id/:kk', component: ContactosViewComponent},
        ]},
       

{path: '/', component: HomeComponent, pathMatch: 'full'},
{path: '/home', component: HomeComponent},
{path: '/demos', component: DemosComponent, title: 'Demos', pathMatch: 'full'},
{path: '/demos/:id', component: DemosComponent, title: 'Demos'},
{path: '/personas', component: FormulariosComponent},
{path: '/personas/add', component: FormulariosComponent},
{path: '/personas/:id', component: FormulariosComponent},
{path: '/personas/:id/editar', component: FormulariosComponent},

{path: '****', redirectTo: '/home', pathMatch: 'full'},

];
