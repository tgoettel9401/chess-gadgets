export class Player {
  id: number;
  pid: number;
  firstName: string;
  lastName: string;
  name: string;
  yearOfBirth: number;
  mayOld: number;
  julOld: number;
  sepOld: number;
  novOld: number;
  janNew: number;
  marNew: number;
  mayNew: number;

  maxRating: number;
  minRating: number;

  // Mode stands for the table-mode
  mode: string;

  Player(firstName: string, lastName: string) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
