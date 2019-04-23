import {Tournament} from "./Tournament";

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
  tournaments: Tournament[];

  // Mode stands for the table-mode
  mode: string;
}
