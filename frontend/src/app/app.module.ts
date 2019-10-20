import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatSelectModule,
  MatSlideToggleModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule
} from "@angular/material";
import {PlayerListComponent} from "./player-list/player-list.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {LayoutModule} from "@angular/cdk/layout";
import {NgxSoapModule} from "ngx-soap";
import {QuotaComponent} from './quota/quota.component';
import {QuotaTournamentDetailsComponent} from './quota-tournament-details/quota-tournament-details.component';
import {QuotaTournamentImportedTournamentsComponent} from './quota-tournament-imported-tournaments/quota-tournament-imported-tournaments.component';
import {QuotaTournamentImportedTournamentsTableComponent} from './quota-tournament-imported-tournaments-table/quota-tournament-imported-tournaments-table.component';

@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    QuotaComponent,
    QuotaTournamentDetailsComponent,
    QuotaTournamentImportedTournamentsComponent,
    QuotaTournamentImportedTournamentsTableComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatTabsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatSlideToggleModule,
    MatGridListModule,
    NgxSoapModule,
    MatDialogModule,
    MatCheckboxModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
