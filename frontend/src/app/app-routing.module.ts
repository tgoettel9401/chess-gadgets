import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PlayerListComponent} from "./player-list/player-list.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', redirectTo: 'players', pathMatch: 'full' },
  { path: 'players', component: PlayerListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
