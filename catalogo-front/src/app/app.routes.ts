import { Routes } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
// Removed unused import
import { PeliculasListComponent, routes as PeliculasRoutes } from './peliculas';
import { LoginFormComponent, RegisterUserComponent } from './security';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },
  { path: 'catalogo/categorias', component: PeliculasListComponent, data: { search: 'categorias' }, title: 'categorias' },
  { path: 'catalogo/categorias/:idPeli/:tit', redirectTo: '/catalogo/:idPeli/:tit', title: 'catalogo' },
  { path: 'catalogo', children: PeliculasRoutes, title: 'catalogo' },
  { path: 'actores/:id/:nom/:idPeli/:tit', redirectTo: '/catalogo/:idPeli/:tit', title: 'catalogo' },
  {
    path: 'actores', loadChildren: () => import('./actores/modulo.module'), title: 'actores'
  },
  {
    path: 'categorias', loadChildren: () => import('./categorias/modulo.module'), title: 'categorias'
  },
  {
    path: 'idiomas', loadChildren: () => import('./idiomas/modulo.module'), title: 'idiomas'
  },
  // {
  //   path: 'contactos', loadChildren: () => import('./contactos/modulo.module').then(mod => mod.ContactosModule), title: 'contactos',
  //   canActivate: [AuthCanActivateFn]
  // },

  { path: 'login', component: LoginFormComponent },
  { path: 'registro', component: RegisterUserComponent },

  { path: '404.html', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];
