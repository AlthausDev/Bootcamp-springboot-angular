import {
  Component,
  forwardRef,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { LanguagesViewModelService } from './servicios.service';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, ParamMap, RouterModule, RouterLink} from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorMessagePipe as ErrorMessagePipe } from "../../lib/my-core/pipes/strings.pipe";
import { TypeValidator } from '@my/core';

@Component({
  selector: 'app-languages',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
    forwardRef(() => LanguagesAddComponent),
    forwardRef(() => LanguagesEditComponent),
    forwardRef(() => LanguagesViewComponent),
    forwardRef(() => LanguagesListComponent),
  ],
})
export class LanguagesComponent implements OnInit, OnDestroy {
  constructor(private vm: LanguagesViewModelService) {}
  public get VM(): LanguagesViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
  ngOnDestroy(): void {
    this.vm.clear();
  }
}

@Component({
  standalone: true,
  selector: 'app-languages-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [RouterLink],
})
export class LanguagesListComponent implements OnInit, OnDestroy {
  constructor(private vm: LanguagesViewModelService) {}
  public get VM(): LanguagesViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
  ngOnDestroy(): void {
    this.vm.clear();
  }
}

@Component({
  selector: 'app-languages-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe],
  providers: [LanguagesViewModelService],
})
export class LanguagesAddComponent implements OnInit {
  constructor(private vm: LanguagesViewModelService) {}
  public get VM(): LanguagesViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
  }
}

@Component({
  selector: 'app-languages-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe, ErrorMessagePipe],
})
export class LanguagesEditComponent implements OnInit, OnDestroy {
  private obs$?: Subscription;
  constructor(
    protected vm: LanguagesViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): LanguagesViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
        this.vm.edit(id);
      } else {
        this.router.navigate(['/404.html']);
      }
    });
  }
  ngOnDestroy(): void {
    this.obs$!.unsubscribe();
  }
}

@Component({
  selector: 'app-languages-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [DatePipe, RouterModule],
})
export class LanguagesViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(
    protected vm: LanguagesViewModelService,
    protected router: Router
  ) {}
  public get VM(): LanguagesViewModelService {
    return this.vm;
  }
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  ngOnChanges(changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const LANGUAGES_COMPONENTES = [
  LanguagesComponent,
  LanguagesListComponent,
  LanguagesAddComponent,
  LanguagesEditComponent,
  LanguagesViewComponent,
];
