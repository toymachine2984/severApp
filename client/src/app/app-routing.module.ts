import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {EventComponent} from "./components/event/event.component";
import {EventGuard} from "./routing/event.guard";
// import {DocumentListComponent} from "./components/document-list/document-list.component";
// import {EventListComponent} from "./components/event-list/event-list.component";


const routes: Routes = [
// {path: 'heroes', component: HeroesComponent},
// {path: 'dashboard', component: DashboardComponent},
// {path: 'detail/:id', component: HeroDetailComponent},
  {path: 'home', component: HomeComponent},
  // {path: 'documents', component: DocumentListComponent},
  {path: 'events', component: EventComponent, canActivate:[EventGuard]},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
