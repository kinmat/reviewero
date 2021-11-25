import { WebSocketService } from 'src/app/services/web-socket.service';
import { AuthService } from 'src/app/services/auth.service';
import { AuthenticationService } from './services/authentication.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BodyComponent } from './components/body/body.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HomeComponent } from './components/home/home.component';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfileComponent } from './components/profile/profile.component';
import { BasicAuthHtppInterceptorService } from './services/basic-auth-htpp-interceptor.service';
import { BookService } from './services/book.service';
import { AllBooksComponent } from './components/all-books/all-books.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { AuthorDetailsComponent } from './components/author-details/author-details.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { CommonModule } from '@angular/common';
import { SearchComponent } from './components/search/search.component';
import { AuthorListComponent } from './components/author-list/author-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { PublicProfileComponent } from './components/public-profile/public-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTabsModule } from '@angular/material/tabs';
import { MatListModule } from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faComment, faUserCircle, faCheckCircle, faTimesCircle } from '@fortawesome/free-regular-svg-icons';
import { MatButtonModule } from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import { faPlus, faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { ChatComponent } from './components/chat/chat.component';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { myRxStompConfig } from './my-rx-stomp.config';
import { CollectionComponent } from './components/collection/collection.component';
import { MatExpansionModule } from '@angular/material/expansion';
import {MatSlider, MatSliderModule} from '@angular/material/slider';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    BodyComponent,
    SidebarComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    AllBooksComponent,
    AuthorDetailsComponent,
    BookListComponent,
    BookDetailsComponent,
    SearchComponent,
    AuthorListComponent,
    UserListComponent,
    PublicProfileComponent,
    ChatComponent,
    CollectionComponent
  ],
  imports: [
    MatSidenavModule,
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    MatSliderModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MdbCollapseModule,
    MatTabsModule,
    MatListModule,
    MatMenuModule,
    MatExpansionModule,
    FontAwesomeModule,
    MatButtonModule,
    MatDividerModule,
    RouterModule.forRoot([
      { path: '', component: HomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'books/:id', component: BookDetailsComponent },
      { path: 'books', component: AllBooksComponent },
      { path: 'authors/:id', component: AuthorDetailsComponent },
      { path: 'user/:id', component: PublicProfileComponent},
      { path: 'search', component: SearchComponent },
      { path: 'collection', component: CollectionComponent },
      { path: 'chat/user/:id', component: ChatComponent },
    ]),
    BrowserAnimationsModule,
  ],
  providers: [AuthenticationService, AuthService, BookService,
    {
      provide:HTTP_INTERCEPTORS, useClass:BasicAuthHtppInterceptorService, multi:true
    },
    WebSocketService
   ],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    // Add multiple icons to the library
    library.addIcons(faComment, faUserCircle, faUserPlus, faCheckCircle, faTimesCircle, faPlus);
  }
}
