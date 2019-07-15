import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FileUploadModule} from "ng2-file-upload";
import {MaterialTimePickerModule } from '@candidosales/material-time-picker';
import {
  MatTabsModule,
  MatSidenavModule,
  MatIconModule,
  MatIconRegistry,
  MatButtonModule,
  MatCardModule,
  MatInputModule,
  MatListModule,
  MatTableModule,
  MatPaginatorModule,
  MatSelectModule,
  MatStepperModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatProgressBarModule,
  MatButtonToggleModule,
  MatAutocompleteModule, MatChipsModule, MatExpansionModule, MatCheckboxModule
} from "@angular/material";
import {MatToolbarModule} from '@angular/material/toolbar';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {HeaderComponent} from './components/header/header.component';
import {HomeComponent} from './components/home/home.component';
import {RefreshTokenInterceptor} from './interceptors/refresh-token.interceptor';
import {EventListComponent} from './components/event-list/event-list.component';
import {EventComponent} from './components/event/event.component';
import {EventNewComponent} from './components/event-new/event-new.component';
import {FileDropZoneComponent} from './components/file-dropzone/file-drop-zone.component';
import {CKEditorModule} from "@ckeditor/ckeditor5-angular";
import {STEPPER_GLOBAL_OPTIONS} from "@angular/cdk/stepper";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    EventListComponent,
    EventComponent,
    EventNewComponent,
    FileDropZoneComponent,
  ],
  imports: [
    FileUploadModule,
    CKEditorModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatSidenavModule,
    MatIconModule,
    FlexLayoutModule,
    MatButtonModule,
    MatSelectModule,
    MatListModule,
    MatToolbarModule,
    MatPaginatorModule,
    MatTableModule,
    MatStepperModule,
    MatCardModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatButtonToggleModule,
    MatAutocompleteModule,
    MatExpansionModule,
    MatChipsModule,
    MatCheckboxModule,
    MaterialTimePickerModule,
    MatIconModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RefreshTokenInterceptor,
      multi: true
    },
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { showError: true }
    },
    MatIconRegistry
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
