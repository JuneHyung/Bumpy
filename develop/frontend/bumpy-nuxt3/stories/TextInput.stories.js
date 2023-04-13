import TextInput from '/components/form/TextInput.vue';

// More on how to set up stories at: https://storybook.js.org/docs/7.0/vue/writing-stories/introduction
export default {
  title: 'Example/TextInput',
  component: TextInput,
  tags: ['autodocs'],
  render: (args) => ({
    // Components used in your story `template` are defined in the `components` object
    components: {
      TextInput,
    },
    // The story's `args` need to be mapped into the template through the `setup()` method
    setup() {
      // Story args can be spread into the returned object
      return {
        ...args,
      };
    },
    // Then, the spread values can be accessed directly in the template
    template: '<TextInput :data="inputData"/>',
  }), parameters: {
    // More on how to position stories at: https://storybook.js.org/docs/7.0/vue/configure/story-layout
    layout: 'fullscreen',
  },
};

// More on writing stories with args: https://storybook.js.org/docs/7.0/vue/writing-stories/args

export const checkNull = {
  args: {
    inputData: {
      placeholder: '아이디를 입력해주세요'
    }
  },
};