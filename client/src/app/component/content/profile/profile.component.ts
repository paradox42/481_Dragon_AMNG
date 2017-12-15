import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../../../model/user';
import { State } from '../../../model/state';
import { UserService } from '../../../shared/services/user.service';
import { AuthService } from '../../../shared/services/auth.service';
import { Response } from '@angular/http/src/static_response';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  @Input() user: User;

  keys: any[];
  states = State;       //seriously I think State should be named StateEnum

  error = [];

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) {
    this.keys = Object.keys(this.states).filter(Number);
  }

  ngOnInit() {
    this.getUser();
  }

  getUser(): void{
    this.userService.getMe().
    subscribe(u => this.user = u);
  }

  updateUser(): void{
    this.userService.updateUser(this.user)
      .subscribe(
        (response: Response) => {
          if (response.status === 201) {
            this.router.navigate(['/']);
          } else {
            this.error = response.json().message();
          }
        }
      );
  }
}
