(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _player_list_player_list_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./player-list/player-list.component */ "./src/app/player-list/player-list.component.ts");




var routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', redirectTo: 'players', pathMatch: 'full' },
    { path: 'players', component: _player_list_player_list_component__WEBPACK_IMPORTED_MODULE_3__["PlayerListComponent"] },
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-toolbar color=\"primary\">\n  {{title}}\n</mat-toolbar>\n\n<a routerLink=\"/home\">\n  <button mat-button color=\"primary\">\n    Player List\n  </button>\n</a>\n\n<button mat-button color=\"primary\">\n  Second Button\n</button>\n\n<router-outlet></router-outlet>\n\n<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n"

/***/ }),

/***/ "./src/app/app.component.scss":
/*!************************************!*\
  !*** ./src/app/app.component.scss ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2FwcC5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'DSJ-Gadgets';
    }
    AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.scss */ "./src/app/app.component.scss")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _player_list_player_list_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./player-list/player-list.component */ "./src/app/player-list/player-list.component.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _angular_cdk_layout__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/cdk/layout */ "./node_modules/@angular/cdk/esm5/layout.es5.js");
/* harmony import */ var ngx_soap__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ngx-soap */ "./node_modules/ngx-soap/fesm5/ngx-soap.js");












var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"],
                _player_list_player_list_component__WEBPACK_IMPORTED_MODULE_6__["PlayerListComponent"],
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_4__["AppRoutingModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_7__["HttpClientModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_9__["BrowserAnimationsModule"],
                _angular_cdk_layout__WEBPACK_IMPORTED_MODULE_10__["LayoutModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTabsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_8__["FormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_8__["ReactiveFormsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSelectModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatPaginatorModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSortModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatDatepickerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatNativeDateModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatExpansionModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSlideToggleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatGridListModule"],
                ngx_soap__WEBPACK_IMPORTED_MODULE_11__["NgxSoapModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatDialogModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCheckboxModule"],
            ],
            providers: [],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/models/Tournament.ts":
/*!**************************************!*\
  !*** ./src/app/models/Tournament.ts ***!
  \**************************************/
/*! exports provided: Tournament */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Tournament", function() { return Tournament; });
var Tournament = /** @class */ (function () {
    function Tournament() {
    }
    return Tournament;
}());



/***/ }),

/***/ "./src/app/player-list/player-list.component.html":
/*!********************************************************!*\
  !*** ./src/app/player-list/player-list.component.html ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<br> <br>\n\n<mat-card>\n  <mat-card-header>\n    Player List\n    &nbsp; &nbsp;\n    <button mat-raised-button (click)=\"getPlayers()\">\n      <mat-icon>update</mat-icon> Update\n    </button>\n    <button mat-raised-button (click)=\"importTournamentsAndSave()\">\n      <mat-icon>import_export</mat-icon> Import Tournaments\n    </button>\n    <button mat-raised-button (click)=\"importPlayerIds()\">\n      <mat-icon>import_export</mat-icon> Import Player Ids\n    </button>\n    <button mat-raised-button (click)=\"importPlayerDetails()\">\n      <mat-icon>import_export</mat-icon> Import Player Tournaments\n    </button>\n    <button mat-raised-button (click)=\"calculateRatings()\">\n      <mat-icon>code</mat-icon> Calculate\n    </button>\n    &nbsp; &nbsp;\n    <br> <br>\n  </mat-card-header>\n\n  <mat-card-content>\n    <table mat-table [dataSource]=\"dataSource\" matSort class=\"mat-elevation-z8\">\n\n      <!-- Name Column -->\n      <ng-container matColumnDef=\"name\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> Name </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field>\n            <input name=\"name\" matInput [(ngModel)]=\"element.name\" [disabled]=\"element.mode!='edit'\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- pid Column -->\n      <ng-container matColumnDef=\"pid\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> DSB-Number </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field>\n            <input name=\"name\" matInput [(ngModel)]=\"element.pid\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- birthyear Column -->\n      <ng-container matColumnDef=\"birthyear\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header class=\"small\"> Year of Birth </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field>\n            <input name=\"name\" matInput [(ngModel)]=\"element.yearOfBirth\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- may old Column -->\n      <ng-container matColumnDef=\"mayOld\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> May 2018 </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field class=\"small\">\n            <input name=\"name\" matInput [(ngModel)]=\"element.ratings[1]\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- july old Column -->\n      <ng-container matColumnDef=\"julOld\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> July 2018 </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field class=\"small\">\n            <input name=\"name\" matInput [(ngModel)]=\"element.ratings[2]\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- may old Column -->\n      <ng-container matColumnDef=\"sepOld\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> Sept 2018 </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field class=\"small\">\n            <input name=\"name\" matInput [(ngModel)]=\"element.ratings[3]\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- may old Column -->\n      <ng-container matColumnDef=\"novOld\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> Nov 2018 </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field class=\"small\">\n            <input name=\"name\" matInput [(ngModel)]=\"element.ratings[4]\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- may old Column -->\n      <ng-container matColumnDef=\"janNew\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> Jan 2019 </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field class=\"small\">\n            <input name=\"name\" matInput [(ngModel)]=\"element.ratings[5]\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- may old Column -->\n      <ng-container matColumnDef=\"marNew\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> March 2019 </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field class=\"small\">\n            <input name=\"name\" matInput [(ngModel)]=\"element.ratings[6]\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <!-- may old Column -->\n      <ng-container matColumnDef=\"mayNew\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> May 2019 </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <mat-form-field class=\"small\">\n            <input name=\"name\" matInput [(ngModel)]=\"element.ratings[7]\" [disabled]=\"true\">\n          </mat-form-field>\n        </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"buttons\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <div>\n            <button mat-raised-button (click)=\"searchPlayer(element)\">\n              <mat-icon>search</mat-icon>\n            </button>\n            <button mat-raised-button (click)=\"getPlayerDetails(element)\">\n              <mat-icon>details</mat-icon>\n            </button>\n          </div>\n        </td>\n      </ng-container>\n\n      <tr mat-header-row *matHeaderRowDef=\"displayedColumns\"></tr>\n      <tr mat-row *matRowDef=\"let row; columns: displayedColumns\"></tr>\n    </table>\n    <mat-paginator [pageSizeOptions]=\"[10, 20]\" showFirstLastButtons></mat-paginator>\n  </mat-card-content>\n</mat-card>\n\n<br>\n"

/***/ }),

/***/ "./src/app/player-list/player-list.component.scss":
/*!********************************************************!*\
  !*** ./src/app/player-list/player-list.component.scss ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".small {\n  width: 50px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGxheWVyLWxpc3QvQzpcXFVzZXJzXFxUb2JpYXNcXERvY3VtZW50c1xcUHJvZ3JhbW1pZXJ1bmdlblxcZHNqXFxmcm9udGVuZC9zcmNcXGFwcFxccGxheWVyLWxpc3RcXHBsYXllci1saXN0LmNvbXBvbmVudC5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsWUFBVyxFQUNaIiwiZmlsZSI6InNyYy9hcHAvcGxheWVyLWxpc3QvcGxheWVyLWxpc3QuY29tcG9uZW50LnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuc21hbGwge1xyXG4gIHdpZHRoOiA1MHB4O1xyXG59XHJcbiJdfQ== */"

/***/ }),

/***/ "./src/app/player-list/player-list.component.ts":
/*!******************************************************!*\
  !*** ./src/app/player-list/player-list.component.ts ***!
  \******************************************************/
/*! exports provided: PlayerListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PlayerListComponent", function() { return PlayerListComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _services_player_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/player.service */ "./src/app/services/player.service.ts");
/* harmony import */ var _services_dewis_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../services/dewis.service */ "./src/app/services/dewis.service.ts");
/* harmony import */ var _models_Tournament__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../models/Tournament */ "./src/app/models/Tournament.ts");
/* harmony import */ var _services_tournament_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../services/tournament.service */ "./src/app/services/tournament.service.ts");







var PlayerListComponent = /** @class */ (function () {
    function PlayerListComponent(playerService, dewisService, tournamentService) {
        this.playerService = playerService;
        this.dewisService = dewisService;
        this.tournamentService = tournamentService;
    }
    PlayerListComponent.prototype.ngOnInit = function () {
        this.getPlayers();
        this.displayedColumns = ['name', 'pid', 'birthyear', 'mayOld', 'julOld', 'sepOld', 'novOld', 'janNew', 'marNew', 'mayNew', 'buttons'];
    };
    PlayerListComponent.prototype.getPlayers = function () {
        var _this = this;
        this.playerService.getAllPlayers().subscribe(function (data) { return _this.players = data._embedded.players; }, function (error) { return console.log(error); }, function () { return _this.updateTable(_this.players); });
    };
    PlayerListComponent.prototype.updateTable = function (players) {
        this.dataSource = new _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableDataSource"](players);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    };
    PlayerListComponent.prototype.getPlayerDetails = function (element) {
        var _this = this;
        this.dewisService.findTournamentCardById(element.pid).subscribe(function (res) {
            var test = res.result.return.tournaments;
            var tournaments = [];
            for (var _i = 0, _a = res.result.return.tournaments.item; _i < _a.length; _i++) {
                var tournament = _a[_i];
                var tempTournament = new _models_Tournament__WEBPACK_IMPORTED_MODULE_5__["Tournament"]();
                tempTournament.tname = tournament.tname.$value;
                tempTournament.tcode = tournament.tcode.$value;
                tempTournament.ratingOld = tournament.ratingOld.$value;
                tempTournament.ratingNew = tournament.ratingNew.$value;
                tournaments.push(tempTournament);
            }
            _this.playerService.addTournamentsToPlayer(element, tournaments).subscribe();
            element.tournaments = tournaments;
        });
    };
    PlayerListComponent.prototype.searchPlayer = function (element) {
        var _this = this;
        this.dewisService.findPlayerByName(element.firstName, element.lastName).subscribe(function (res) {
            // If only one player is found, this player can be set as the correct player.
            if (!Array.isArray(res.result.return.members.item)) {
                element.pid = res.result.return.members.item.pid.$value;
            }
            // If more than one player is found, it is checked if by using birthyear the result is unique.
            else if (res.result.return.members.item.length > 1) {
                var counter = 0;
                var correctPlayers = [];
                for (var _i = 0, _a = res.result.return.members.item; _i < _a.length; _i++) {
                    var player = _a[_i];
                    var value = +player.yearOfBirth.$value;
                    if (value == element.yearOfBirth) {
                        correctPlayers.push(player);
                        counter++;
                    }
                }
                // If counter == 1 then now the result is unique.
                if (counter == 1) {
                    element.pid = correctPlayers[0].pid.$value;
                }
                // If the result is not unique after using the birthyear, the club will be used as well.
                else {
                    var correctPlayers2 = [];
                    var counter2 = 0;
                    for (var _b = 0, correctPlayers_1 = correctPlayers; _b < correctPlayers_1.length; _b++) {
                        var player = correctPlayers_1[_b];
                        if (player.club == element.club) {
                            correctPlayers2.push(player);
                            counter2++;
                        }
                    }
                    // If the counter is still > 1, then this information is logged.
                    if (counter2 > 1)
                        console.log("Counter is > 1, so first element chosen!");
                    // Now it is assumed that the result is unique.
                    else {
                        element.pid = correctPlayers2[0].pid.$value;
                    }
                }
            }
        }, function (error) { return console.log(error); }, function () { return _this.playerService.patchPlayer(element).subscribe(); });
    };
    PlayerListComponent.prototype.importTournamentsAndSave = function () {
        var _this = this;
        var startDate = new Date(2018, 0, 1);
        var endDate = new Date(2019, 0, 1);
        this.tournamentService.deleteAllTournaments().subscribe(function () { }, function (error) { return console.log(error); }, function () {
            _this.dewisService.findTournamentsByPeriod(startDate, endDate).subscribe(function (res) {
                var tournaments = [];
                for (var _i = 0, _a = res.result.return.tournaments.item; _i < _a.length; _i++) {
                    var tournament = _a[_i];
                    var tournamentForImport = new _models_Tournament__WEBPACK_IMPORTED_MODULE_5__["Tournament"]();
                    tournamentForImport.calculatedOn = new Date(tournament.computedOn.$value);
                    tournamentForImport.finishedOn = new Date(tournament.finishedOn.$value);
                    tournamentForImport.tcode = tournament.tcode.$value;
                    tournamentForImport.tname = tournament.tname.$value;
                    tournamentForImport.tid = tournament.tid.$value;
                    // Rating old and Rating new are still empty because they are only available on Tournament attached with player.
                    //this.tournamentService.postTournament(tournamentForImport).subscribe();
                    tournaments.push(tournamentForImport);
                }
                _this.tournamentService.postMultipleTournaments(tournaments).subscribe();
            }, function (error) { return console.log(error); }, function () {
                var startDate = new Date(2019, 0, 1);
                var endDate = new Date(2020, 0, 1);
                _this.dewisService.findTournamentsByPeriod(startDate, endDate).subscribe(function (res) {
                    var tournaments = [];
                    for (var _i = 0, _a = res.result.return.tournaments.item; _i < _a.length; _i++) {
                        var tournament = _a[_i];
                        var tournamentForImport = new _models_Tournament__WEBPACK_IMPORTED_MODULE_5__["Tournament"]();
                        tournamentForImport.calculatedOn = new Date(tournament.computedOn.$value);
                        tournamentForImport.finishedOn = new Date(tournament.finishedOn.$value);
                        tournamentForImport.tcode = tournament.tcode.$value;
                        tournamentForImport.tname = tournament.tname.$value;
                        tournamentForImport.tid = tournament.tid.$value;
                        // Rating old and Rating new are still empty because they are only available on Tournament attached with player.
                        //this.tournamentService.postTournament(tournamentForImport).subscribe();
                        tournaments.push(tournamentForImport);
                    }
                    _this.tournamentService.postMultipleTournaments(tournaments).subscribe();
                }, function (error) { return console.log(error); }, function () {
                });
            });
        });
    };
    PlayerListComponent.prototype.calculateRatings = function () {
        var _this = this;
        var players = [];
        var newPlayers = [];
        this.playerService.getAllPlayers().subscribe(function (data) { return players = data._embedded.players; }, function (error) { return console.log(error); }, function () {
            var _loop_1 = function (player) {
                _this.playerService.getRatings(player).subscribe(function (data) { return player.ratings = data; }, function (error) { return console.log(error); }, function () {
                    newPlayers.push(player);
                    _this.players = newPlayers;
                    _this.updateTable(newPlayers);
                });
            };
            for (var _i = 0, players_1 = players; _i < players_1.length; _i++) {
                var player = players_1[_i];
                _loop_1(player);
            }
        });
    };
    PlayerListComponent.prototype.importPlayerIds = function () {
        var _this = this;
        var players = [];
        this.playerService.getAllPlayers().subscribe(function (data) { return players = data._embedded.players; }, function (error) { return console.log(error); }, function () {
            var _loop_2 = function (playerOut) {
                _this.dewisService.findPlayerByName(playerOut.firstName, playerOut.lastName).subscribe(function (res) {
                    // If only one player is found, this player can be set as the correct player.
                    if (!Array.isArray(res.result.return.members.item)) {
                        playerOut.pid = res.result.return.members.item.pid.$value;
                    }
                    // If more than one player is found, it is checked if by using birthyear the result is unique.
                    else if (res.result.return.members.item.length > 1) {
                        var counter = 0;
                        var correctPlayers = [];
                        for (var _i = 0, _a = res.result.return.members.item; _i < _a.length; _i++) {
                            var player = _a[_i];
                            var value = +player.yearOfBirth.$value;
                            if (value == playerOut.yearOfBirth) {
                                correctPlayers.push(player);
                                counter++;
                            }
                        }
                        // If counter == 1 then now the result is unique.
                        if (counter == 1) {
                            playerOut.pid = correctPlayers[0].pid.$value;
                        }
                        // If the result is not unique after using the birthyear, the club will be used as well.
                        else {
                            var correctPlayers2 = [];
                            var counter2 = 0;
                            for (var _b = 0, correctPlayers_2 = correctPlayers; _b < correctPlayers_2.length; _b++) {
                                var player = correctPlayers_2[_b];
                                if (player.club == playerOut.club) {
                                    correctPlayers2.push(player);
                                    counter2++;
                                }
                            }
                            // If the counter is still > 1, then this information is logged.
                            if (counter2 > 1)
                                console.log("Counter is > 1, so first element chosen!");
                            // Now it is assumed that the result is unique.
                            else {
                                playerOut.pid = correctPlayers2[0].pid.$value;
                            }
                        }
                    }
                    _this.playerService.patchPlayer(playerOut).subscribe(function () { _this.updateTable(players); }, function (error) { return console.log(error); });
                }, function (error) { return console.log(error); }, function () {
                });
            };
            for (var _i = 0, players_2 = players; _i < players_2.length; _i++) {
                var playerOut = players_2[_i];
                _loop_2(playerOut);
            }
        });
    };
    PlayerListComponent.prototype.importPlayerDetails = function () {
        var _this = this;
        var players = [];
        this.playerService.getAllPlayers().subscribe(function (data) { return players = data._embedded.players; }, function (error) { return console.log(error); }, function () {
            var _loop_3 = function (playerOut) {
                _this.dewisService.findTournamentCardById(playerOut.pid).subscribe(function (res) {
                    var test = res.result.return.tournaments;
                    var tournaments = [];
                    for (var _i = 0, _a = res.result.return.tournaments.item; _i < _a.length; _i++) {
                        var tournament = _a[_i];
                        var tempTournament = new _models_Tournament__WEBPACK_IMPORTED_MODULE_5__["Tournament"]();
                        tempTournament.tname = tournament.tname.$value;
                        tempTournament.tcode = tournament.tcode.$value;
                        tempTournament.ratingOld = tournament.ratingOld.$value;
                        if (!isNaN(tournament.ratingNew))
                            tempTournament.ratingNew = tournament.ratingNew.$value;
                        else
                            tempTournament.ratingNew = tempTournament.ratingOld;
                        tournaments.push(tempTournament);
                    }
                    playerOut.tournaments = tournaments;
                    _this.playerService.addTournamentsToPlayer(playerOut, tournaments).subscribe();
                });
            };
            for (var _i = 0, players_3 = players; _i < players_3.length; _i++) {
                var playerOut = players_3[_i];
                _loop_3(playerOut);
            }
        });
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])(_angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginator"]),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginator"])
    ], PlayerListComponent.prototype, "paginator", void 0);
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])(_angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSort"]),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSort"])
    ], PlayerListComponent.prototype, "sort", void 0);
    PlayerListComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-player-list',
            template: __webpack_require__(/*! ./player-list.component.html */ "./src/app/player-list/player-list.component.html"),
            styles: [__webpack_require__(/*! ./player-list.component.scss */ "./src/app/player-list/player-list.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_player_service__WEBPACK_IMPORTED_MODULE_3__["PlayerService"], _services_dewis_service__WEBPACK_IMPORTED_MODULE_4__["DewisService"], _services_tournament_service__WEBPACK_IMPORTED_MODULE_6__["TournamentService"]])
    ], PlayerListComponent);
    return PlayerListComponent;
}());



/***/ }),

/***/ "./src/app/services/dewis.service.ts":
/*!*******************************************!*\
  !*** ./src/app/services/dewis.service.ts ***!
  \*******************************************/
/*! exports provided: DewisService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DewisService", function() { return DewisService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ngx_soap__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ngx-soap */ "./node_modules/ngx-soap/fesm5/ngx-soap.js");



var DewisService = /** @class */ (function () {
    function DewisService(soap) {
        var _this = this;
        this.soap = soap;
        this.soap.createClient('assets/dewis.wsdl').then(function (client) { return _this.client = client; });
    }
    DewisService.prototype.findPlayerByName = function (firstName, lastName) {
        var body = {
            firstname: firstName,
            surename: lastName,
        };
        return this.client.call("searchByName", body);
    };
    DewisService.prototype.findTournamentCardById = function (pid) {
        var body = {
            pid: pid,
        };
        return this.client.call("tournamentCardForId", body);
    };
    DewisService.prototype.findTournamentsByPeriod = function (startDate, endDate) {
        var body = {
            since: startDate.toISOString(),
            until: endDate.toISOString(),
            bSubs: true,
        };
        return this.client.call("tournamentsByPeriod", body);
    };
    DewisService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [ngx_soap__WEBPACK_IMPORTED_MODULE_2__["NgxSoapService"]])
    ], DewisService);
    return DewisService;
}());



/***/ }),

/***/ "./src/app/services/player.service.ts":
/*!********************************************!*\
  !*** ./src/app/services/player.service.ts ***!
  \********************************************/
/*! exports provided: PlayerService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PlayerService", function() { return PlayerService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");





var PlayerService = /** @class */ (function () {
    function PlayerService(http) {
        this.http = http;
        this.baseUrl = 'http://localhost:8080/api/';
    }
    PlayerService.prototype.getAllPlayers = function () {
        var url = this.baseUrl + 'players';
        return this.http.get(url);
    };
    PlayerService.prototype.addTournamentsToPlayer = function (player, tournaments) {
        var url = this.baseUrl + 'players/' + player.id + '/addTournamentsAsList';
        return this.http.patch(url, tournaments);
    };
    PlayerService.prototype.patchPlayer = function (player) {
        var url = this.baseUrl + 'players/' + player.id;
        return this.http.patch(url, player)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["tap"])(function (changedPlayer) { return console.log('Changed player with id=' + changedPlayer.id); }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError('changePlayer')));
    };
    PlayerService.prototype.getRatings = function (player) {
        var url = this.baseUrl + 'players/' + player.id + '/getRatings';
        return this.http.get(url);
    };
    PlayerService.prototype.handleError = function (operation, result) {
        if (operation === void 0) { operation = 'operation'; }
        return function (error) {
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            alert(operation + " failed: " + error.message);
            // Let the app keep running by returning an empty result.
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_3__["of"])(result);
        };
    };
    PlayerService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], PlayerService);
    return PlayerService;
}());



/***/ }),

/***/ "./src/app/services/tournament.service.ts":
/*!************************************************!*\
  !*** ./src/app/services/tournament.service.ts ***!
  \************************************************/
/*! exports provided: TournamentService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TournamentService", function() { return TournamentService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");




var TournamentService = /** @class */ (function () {
    function TournamentService(http) {
        this.http = http;
        this.baseUrl = 'http://localhost:8080/api/';
    }
    TournamentService.prototype.deleteAllTournaments = function () {
        var url = this.baseUrl + 'tournaments/deleteAllTournaments';
        return this.http.get(url);
    };
    TournamentService.prototype.postMultipleTournaments = function (tournaments) {
        var url = this.baseUrl + 'tournaments/addTournamentsAsList';
        return this.http.post(url, tournaments);
    };
    TournamentService.prototype.handleError = function (operation, result) {
        if (operation === void 0) { operation = 'operation'; }
        return function (error) {
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            alert(operation + " failed: " + error.message);
            // Let the app keep running by returning an empty result.
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_3__["of"])(result);
        };
    };
    TournamentService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], TournamentService);
    return TournamentService;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! hammerjs */ "./node_modules/hammerjs/hammer.js");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(hammerjs__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");





if (_environments_environment__WEBPACK_IMPORTED_MODULE_4__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_2__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_3__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\Tobias\Documents\Programmierungen\dsj\frontend\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map