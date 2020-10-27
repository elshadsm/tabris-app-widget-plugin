import { Widget } from 'tabris';

declare global {

  namespace appwidget {

    export class TabrisAppWidget extends Widget {

      public counter: number;

      constructor(properties: Partial<TabrisAppWidget>);

    }

  }

}

export { };
