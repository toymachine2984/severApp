export class Token {

  public constructor(init?: Partial<Token>) {
    Object.assign(this, init);
  }

  access_token: string;
  authorities: [];
  expires_in: Date;
  jti: string;
  refresh_expires_in: Date;
  refresh_token: string;
  scope: string;
  token_type: string;

}
