export class Address {

  public constructor(init?: Partial<Address>) {
    Object.assign(this, init);
  }

  id: number;
  country: string;
  city: string;
  street: string;

}
