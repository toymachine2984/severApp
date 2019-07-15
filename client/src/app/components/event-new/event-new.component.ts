import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {TypeService} from "../../services/type.service";
import {Type} from "../../entity/type";
import {Event} from "../../entity/event";
import {EventService} from "../../services/event.service";
import {Lecture} from "../../entity/lecture";
import {LectureService} from "../../services/lecture.service";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import '@ckeditor/ckeditor5-build-classic/build/translations/ru';
import * as ClassicCKEditor from "@ckeditor/ckeditor5-build-classic";
import {Address} from "../../entity/address";
import {MatChipInputEvent, MatAutocomplete, MatAutocompleteSelectedEvent, MatIconRegistry} from "@angular/material";
import {Tags} from "../../entity/tags";
import {TagsService} from "../../services/tags.service";
import {Tariff} from "../../entity/tariff";
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';

@Component({
  selector: 'app-event-new',
  templateUrl: './event-new.component.html',
  styleUrls: ['./event-new.component.scss'],
  providers: [{provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}}]
})
export class EventNewComponent implements OnInit {

  isLinear = false;
  private firstFormGroup: FormGroup;
  private secondFormGroup: FormGroup;
  private thirdFormGroup: FormGroup;
  private types: Type[];
  private selectedTags: Tags[] = [];
  private allTags: Tags[];
  private filteredTags: Observable<Tags[]>;
  private editorData: string;
  private lectures: Lecture[];
  private filteredLecture: Observable<Lecture[]>;
  private event: Event;
  private CKEditor = ClassicCKEditor;
  private step = 0;
  private startTime = {hour: 0, minute: 0, meriden: 'PM', format: 24};
  private endTime = {hour: 0, minute: 0, meriden: 'PM', format: 24};

  @ViewChild('tagsInput') tagsInput: ElementRef<HTMLInputElement>;
  @ViewChild('tagsAuto') matAutocomplete: MatAutocomplete;

  constructor(private _formBuilder: FormBuilder,
              private typeService: TypeService,
              private eventService: EventService,
              private lectureService: LectureService,
              private tagService: TagsService) {
  }

  public config = {
    language: 'ru'
  };

  ngOnInit() {
    this.event = new Event();
    this.event.address = new Address();
    this.event.address.city = 'Almaty';
    this.event.address.country = 'KZ';
    this.typeService.getTypes().subscribe((data) => this.types = data);

    this.lectureService.getLectures().subscribe((data) => {
      this.autocompleteLectureEvent(data);
      this.lectures = data;
    });

    this.firstFormGroup = this._formBuilder.group({
      title: new FormControl('', [Validators.required]),
      type: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['']
    });
    this.secondFormGroup = this._formBuilder.group({
      address: ['', Validators.required],
      description: ['', Validators.required],
      lecture: ['', Validators.required]
    });
    this.thirdFormGroup = this._formBuilder.group({
      tags: [''],
      tariffs: this._formBuilder.array([this.createTariff()])
    });

    this.tagService.getTags().subscribe((data) => {
      this.autocompleteTagEvent(data);
      this.allTags = data
    });

    this.firstFormGroup.valueChanges.subscribe(data => {
      this.event.title = data['title'];
      this.event.type = data['type'];
      this.event.startDate = data['startDate'];
      this.event.endDate = data['endDate'];
    });

    this.secondFormGroup.valueChanges.subscribe(data => {
      this.event.lecture = data['lecture'];
      this.event.address.street = data['address'];
    });

    this.thirdFormGroup.valueChanges.subscribe(data => {
      this.event.tags = this.selectedTags;
      this.event.tariffs = data['tariffs'];
    });
  }

  receiveImagePath(event) {
    if (event.isMain) {
      this.event.imageLink = event.url;
    } else {
      this.event.previewLink = event.url;
    }
  }


  private autocompleteLectureEvent(lectures: Lecture[]) {
    this.filteredLecture = this.secondFormGroup.get('lecture').valueChanges
      .pipe(
        startWith(''),
        map(value => value ? this.lectureFilter(value) : lectures.slice())
      );
  }

  private autocompleteTagEvent(tags: Tags[]) {
    this.filteredTags = this.thirdFormGroup.get('tags').valueChanges
      .pipe(
        startWith(''),
        map(value => value ? this.tagFilter(value) : tags.slice())
      );
  }


  private addTag(event: MatChipInputEvent): void {
    if (!this.matAutocomplete.isOpen) {
      const input = event.input;
      const value = event.value.toLowerCase();
      if (value) {
        let tags = this.allTags.find(e => e.value.toLowerCase() === value);
        this.selectedTags.push(tags != undefined ? tags : new Tags(value));
      }
      if (input) {
        input.value = '';
      }
      this.thirdFormGroup.get('tags').setValue(null);
    }
  }

  removeTag(tag: Tags): void {
    const index = this.selectedTags.indexOf(tag);

    if (index >= 0) {
      this.selectedTags.splice(index, 1);
    }
  }

  //fixme check is date undefined
  onChangeHour(event, isStart) {
    if (isStart) {
      this.startTime = event;
      this.event.startDate.setHours(this.startTime.hour);
      this.event.startDate.setMinutes(this.startTime.minute);
    } else {
      this.endTime = event;
      this.event.endDate.setHours(this.endTime.hour);
      this.event.endDate.setMinutes(this.endTime.minute);
    }

  }

  selectedTag(event: MatAutocompleteSelectedEvent): void {
    this.selectedTags.push(event.option.value);
    this.tagsInput.nativeElement.value = '';
    this.thirdFormGroup.get('tags').setValue(null);
  }

  private tagFilter(tag: any): Tags[] {
    if (typeof tag === "string") {
      const tagValue = tag.toLowerCase();
      return this.allTags.filter(tag => tag.value.toLowerCase().indexOf(tagValue) === 0);
    } else {
      return this.allTags.filter(t => tag.id === t.id);
    }
  }

  private lectureFilter(lecture: any): Lecture[] {
    if (typeof lecture === "string") {
      const filterValue = lecture.toLowerCase();
      return this.lectures.filter(value => value.name.toLowerCase().indexOf(filterValue) === 0);
    } else {
      return this.lectures.filter(l => l.id === lecture.id);
    }
  }

  private displayValue(val: any) {
    return val ? val.name : val;
  }


  private createTariff(): FormGroup {
    return this._formBuilder.group({
      name: ['', Validators.required],
      cost: ['', Validators.required],
      discount: [''],
      endDate: ['']
    });
  }

  private addTariff() {
    let arr = (this.thirdFormGroup.get('tariffs') as FormArray);
    arr.push(this.createTariff());
    this.step = arr.length - 1;
  }

  private removeTariff(index: number) {
    (this.thirdFormGroup.get('tariffs') as FormArray).removeAt(index);
  }


  setOpened(index: number) {
    this.step = index;
  }

  nextStep(index: number) {
    let tariffs = this.thirdFormGroup.get('tariffs') as FormArray;
    this.step = tariffs.length != index ? index + 1 : tariffs.length;
  }

  createEvent(){
    this.eventService.createEvent(this.event).subscribe(response => {
      console.log(response);
    });
  }


  submitFirstStep() {
    console.log(this.event);
    if (this.firstFormGroup.invalid) {
      return;
    }
  }

  submitSecondStep() {
    console.log(this.event);
  }

  submitThirdStep() {
    console.log(this.event);
  }


}
