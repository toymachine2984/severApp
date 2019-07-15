import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {EventGuard} from "../../routing/event.guard";import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {


  constructor(private loginService: LoginService,
              private eventGuard: EventGuard) {

  }

  ngOnInit() {

  }


  login() {
    this.loginService.login();
  }

  logout() {
    this.loginService.logout();
  }
}
