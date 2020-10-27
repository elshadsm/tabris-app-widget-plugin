/* eslint-disable no-undef */
const { TextView, Composite, TextInput, Button, contentView } = require('tabris');

const widget = new appwidget.Widget();

contentView.append(
    new Composite({ left: 0, top: 100, right: 0 }).append(
        new TextView({
            class: 'counter-label',
            left: 16, centerY: 0,
            text: 'Received counter value:',
            font: '24px'
        }),
        new TextView({
            class: 'counter-value',
            left: ['prev()', 16], centerY: 0, right: 16,
            font: 'bold 24px',
            text: '-'
        })
    ),
    new TextView({
        class: 'input-label',
        left: 16, top: ['prev()', 64], right: 16,
        text: 'Update Widget'
    }),
    new Composite({ left: 0, top: ['.input-label', 4], right: 0 }).append(
        new TextInput({
            id: 'input',
            left: 16, centerY: 0, right: ['50%', 8]
        }),
        new Button({
            left: ['prev()', 16], centerY: 0, right: 16,
            text: 'Update'
        }).onSelect(() => handleUpdate())
    )
);

function handleUpdate() {
    widget.counter = contentView.find('#input').first().text;
}

eslaunchmonitor.on({
    urlLaunch: ({ url, queryParameters }) => {
      /* Handle the selection event of the 'Open App' button in the widget  */
        console.log(url);
        console.log(queryParameters);
        contentView.find('.counter-value').first().text = queryParameters.counter;
    }
});
