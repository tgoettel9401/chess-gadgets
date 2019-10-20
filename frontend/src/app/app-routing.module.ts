import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PlayerListComponent} from "./player-list/player-list.component";
import {QuotaComponent} from "./quota/quota.component";
import {QuotaTournamentDetailsComponent} from "./quota-tournament-details/quota-tournament-details.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', redirectTo: 'players', pathMatch: 'full' },
  { path: 'players', component: PlayerListComponent },
  { path: 'quota', component: QuotaComponent},
  { path: 'quotaTournamentDetails/:id', component: QuotaTournamentDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
