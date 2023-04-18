import NumberInput from '/components/form/NumberInput.vue';

// More on how to set up stories at: https://storybook.js.org/docs/7.0/vue/writing-stories/introduction
export default {
  title: 'Example/NumberInput',
  component: NumberInput,
  tags: ['autodocs'],
  render: (args) => ({
    // Components used in your story `template` are defined in the `components` object
    components: {
      NumberInput,
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
      <NumberInput :data="inputData" style="width: 200px"/>
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
  args: {
    inputData: {
      value: 0,
      placeholder: '숫자를 입력해주세요.',
      suffix: 'SUFFIX',
    },
  },
};

export const NumInput = {
  args: {
    inputData: {
      value: 0,
      placeholder: '숫자를 입력해주세요.',
      autofocus: true,
      max: 20,
      min: 5,
      step: 3,
      noStep: true,
    },
  },
};
