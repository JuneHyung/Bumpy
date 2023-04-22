import SelectboxList from '/components/form/SelectboxInput.vue';

// More on how to set up stories at: https://storybook.js.org/docs/7.0/vue/writing-stories/introduction
export default {
  title: 'Example/SelectboxList',
  component: SelectboxList,
  tags: ['autodocs'],
  render: (args) => ({
    // Components used in your story `template` are defined in the `components` object
    components: {
      SelectboxList,
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
      <SelectboxList :data="inputData" style="width: 200px"/>
    </div>  
    `,
  }),
  parameters: {
    // More on how to position stories at: https://storybook.js.org/docs/7.0/vue/configure/story-layout
    layout: 'fullscreen',
  },
};

export const Selectbox = {
  args: {
    inputData: {
      value: '',
      id: 'selectTest',
      placeholder: 'placeholder입니다.',
      autofocus: true,
      list: [{dtlCd: '01', dtlNm: 'Item01'},{dtlCd: '02', dtlNm: 'Item02'},{dtlCd: '03', dtlNm: 'Item03'}]
    },
  },
};
