import AreaChart from '/components/charts/AreaChart.vue';
import LineChart from '/components/charts/LineChart.vue';
import BarChart from '/components/charts/BarChart.vue';
export default {
  title: 'Example/ChartTest',
  // component: [ChartTest, AreaChart],
  tags: ['autodocs'],
  // parameters: {
  //   layout: 'fullscreen',
  // },
};

export const AreaType = {
  render: (args) => ({
    components: {
      AreaChart,
    },
    setup() {
      return {
        ...args,
      };
    },
    template: `
      <AreaChart/>
    `,
  }),
};
export const LineType = {
  render: (args) => ({
    components: {
      LineChart,
    },
    setup() {
      return {
        ...args,
      };
    },
    template: `
      <LineChart/>
    `,
  }),
};
export const BarType = {
  render: (args) => ({
    components: {
      BarChart,
    },
    setup() {
      return {
        ...args,
      };
    },
    template: `
      <BarChart/>
    `,
  }),
};
