import Calendar from '/components/Calendar.vue';

// More on how to set up stories at: https://storybook.js.org/docs/7.0/vue/writing-stories/introduction
export default {
  title: 'Example/Calendar',
  component: Calendar,
  tags: ['autodocs'],
  render: (args) => ({
    // Components used in your story `template` are defined in the `components` object
    components: {
      Calendar,
    },
    // The story's `args` need to be mapped into the template through the `setup()` method
    setup() {
      // Story args can be spread into the returned object
      return {
        ...args,
      };
    },
    // Then, the spread values can be accessed directly in the template
    template: `
    <div style="display:flex; width:100%;height:100%;padding: 20px;">
      <Calendar :list=list style="width:100%"/>
    </div>  
    `,
  }),
  parameters: {
    // More on how to position stories at: https://storybook.js.org/docs/7.0/vue/configure/story-layout
    layout: 'fullscreen',
  },
};

// More on writing stories with args: https://storybook.js.org/docs/7.0/vue/writing-stories/args

export const Default = {
  args:{
    list: [
      {title:'운동01', date:'2023-04-01',},
      {title:'운동01-2', date:'2023-04-01'},
      {title:'운동01-3', date:'2023-04-01'},
      {title:'운동01-4', date:'2023-04-01'},
      {title:'운동01-5', date:'2023-04-01'},
      {title:'운동01-6', date:'2023-04-01'},
      {title:'운동02', date:'2023-04-12'},
      // {title:'운동03', date:'2023-04-23'},
      // {title:'운동04', date:'2023-04-18'},
      // {title:'운동05', date:'2023-04-03'},
    ]
  }
};
