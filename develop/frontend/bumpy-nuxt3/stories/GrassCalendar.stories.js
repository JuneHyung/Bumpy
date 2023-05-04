import Grass from '/components/calendar/GrassCalendar.vue';

export default {
  title: 'Example/GrassCalendar',
  tags: ['autodocs'],
  parameters: {
    layout: 'fullscreen',
  },
};
const activeList = [
  '2023-04-01',
  '2023-04-05',
  '2023-04-12',
  '2023-04-18',
  '2023-04-25',
  '2023-05-01',
  '2023-05-03',
  '2023-05-05',
  '2023-05-12',
  '2023-05-15',
  '2023-05-27',
  '2023-05-28',
  '2023-05-29',
  '2023-05-30',
  '2023-05-13',
  '2023-05-09',
  '2023-05-18',
  '2023-06-01',
  '2023-06-03',
  '2023-06-12',
  '2023-06-17',
  '2023-06-25',
  '2023-06-26',
  '2023-06-27',
  '2023-06-28',
  '2023-07-01',
  '2023-07-11',
  '2023-07-21',
  '2023-07-31',
  '2023-08-31',
]
export const GrassCalendar = {
  render: (args) => ({
    components: {
      Grass,
    },
    setup() {
      return {
        ...args,
      };
    },
    template: `
      <div style="width:300px;">
        <Grass :activeList="activeList"/>
      </div>
    `,
  }),
  args:{
    activeList
  }
};
