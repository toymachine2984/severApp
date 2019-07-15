export class Document {
  gender: string;

  name: {
    title: string;
    first: string;
    last: string;
  };

  location: {
    street: string;
    city: string;
    state: string;
    postcode: number;
    coordinates: {
      latitude: string;
      longitude: string;
    };
    timezone: {
      offset: string;
      description: string;
    };
  };
  email: string;

  login: {
    uuid: string;
    username: string;
    password: string;
    salt: string;
    md5: string;
    sha1: string;
    sha256: string;
  };

  dob: {
    date: Date;
    age: number;

    registered: {
      date: Date;
      age: number;
    };

    phone: string;


    cell: number;

    id: {
      name: string;
      value: number;
    };

    picture: {
      large: string;
      medium: string;
      thumbnail: string;
    }

    nat: string;
  }
}

