import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PELICULAS_COMPONENTES, PeliculasAddComponent, PeliculasEditComponent, PeliculasViewComponent, PeliculasListComponent } from './componente.component';


export const routes: Routes = [
  { path: '', component: PeliculasListComponent },
  {
    path: 'add', component: PeliculasAddComponent,
  },
  {
    path: ':id/edit', component: PeliculasEditComponent,
  },
  { path: ':id', component: PeliculasViewComponent },
  { path: ':id/:kk', component: PeliculasViewComponent },
];

@NgModule({
  declarations: [ ],
  exports: [
    PELICULAS_COMPONENTES,
    RouterModule,
  ],
  imports: [
    RouterModule.forChild(routes), PELICULAS_COMPONENTES,
  ]
})
export class PeliculasModule { }
