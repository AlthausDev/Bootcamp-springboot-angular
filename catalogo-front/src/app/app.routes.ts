import { Routes } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
// import { routes as PeliculasRoutes } from './peliculas';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },

  {
    path: 'peliculas/:id/:nom/:idi/:tit',
    redirectTo: '/peliculas/:id/:tit',
  },
  {
    path: 'categorias/:id/:tit',
    redirectTo: '/categorias/:id/:tit',
    title: 'catalogo',
  },
  // { path: 'actores', children: PeliculasRoutes, title: 'catalogo' },
  {
    path: 'actores/:id',
    redirectTo: '/actores/:id',
  },
  {
    path: 'lenguages/:id/:nom/:idi/:tit',
    redirectTo: '/lenguages/:id/:tit',
  },
  {
    path: 'peliculas',
    loadChildren: () => import('./peliculas/modulo.module').then(m => m.PeliculasModule),
    title: 'peliculas',
  },
  {
    path: 'actores',
    loadChildren: () => import('./actores/modulo.module'),
    title: 'actores',
  },
  {
    path: 'categorias',
    loadChildren: () => import('./categorias/modulo.module'),
    title: 'categorias',
  },
  {
    path: 'idiomas',
    loadChildren: () => import('./idiomas/modulo.module'),
    title: 'idiomas',
  },
  { path: '404.html', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];