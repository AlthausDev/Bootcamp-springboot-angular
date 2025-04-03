import { Routes } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
import { PeliculasListComponent, routes as PeliculasRoutes } from './peliculas';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },
  { path: 'categorias', component: PeliculasListComponent, data: { search: 'categorias' }, title: 'categorias' },
  { path: 'categorias/:idPeli/:tit', redirectTo: '/:idPeli/:tit', title: 'catalogo' },
  { path: '', children: PeliculasRoutes, title: 'catalogo' },
  { path: 'actores/:id/:nom/:idPeli/:tit', redirectTo: '/:idPeli/:tit', title: 'catalogo' },
  {
    path: 'actores', loadChildren: () => import('./actores/modulo.module'), title: 'actores'
  },
  {
    path: 'categorias', loadChildren: () => import('./categorias/modulo.module'), title: 'categorias'
  },
  {
    path: 'idiomas', loadChildren: () => import('./idiomas/modulo.module'), title: 'idiomas'
  },

  { path: '404.html', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];
