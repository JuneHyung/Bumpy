import ChartTest from '/components/charts/ChartTest.vue';

export default {
  title: 'Example/ChartTest',
  component: ChartTest,
  tags: ['autodocs'],
  render: (args) => ({
    components: {
      ChartTest,
    },
    setup() {
      return {
        ...args,
      };
    },
    template: `
    
      <ChartTest/>
    
    `,
  }),
  parameters: {
    layout: 'fullscreen',
  },
};

export const Default = {
  args:{
    
  }
};
