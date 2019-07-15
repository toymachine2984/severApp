import {Status} from "./status";
import {Address} from "./address";
import {Type} from "./type";
import {Lecture} from "./lecture";
import {Tariff} from "./tariff";
import {Tags} from "./tags";

export class Event {

  public constructor(init?: Partial<Event>) {
    Object.assign(this, init);
  }

  id: number;

  title: string;

  description: string;

  startDate: Date;

  endDate: Date;

  status: Status;

  address: Address;

  imageLink: string;

  previewLink: string;

  videoLink: string;

  etutoriumId: number;

  type: Type;

  lecture: Lecture;

  tariffs: Tariff[];

  tags: Tags[];

}
