import ActivityList from '~/components/list/ActivityList.vue';
// import RectangleList from "/components/list/RectangleList.vue";

// More on how to set up stories at: https://storybook.js.org/docs/7.0/vue/writing-stories/introduction
export default {
  title: 'Example/List',
  tags: ['autodocs'],
  parameters: {
    layout: 'fullscreen',
  },
};

const listData = [
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
];

export const Square = {
  render: (args) => ({
    components: {
      ActivityList,
    },
    setup() {
      return {
        ...args,
      };
    },
    template: `
      <div style="width:500px;">
        <ActivityList type="square" :list="listData" listType="weight"/>
      </div>
    `,
  }),
  args: {
    type: 'square',
    listType: 'weight',
    listData,
  },
};
export const Rectangle = {
  render: (args) => ({
    components: {
      ActivityList,
    },
    setup() {
      return {
        ...args,
      };
    },
    // Then, the spread values can be accessed directly in the template
    template: `
      <div style="width:500px; height:200px;">
        <ActivityList type="rectangle" :list="listData" listType="weight"/>
      </div>
    `,
  }),
  args: {
    type: 'rectangle',
    listType: 'weight',
    listData,
  },
};
