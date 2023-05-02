import Menu from '/components/Menu.vue';

export default {
  title: 'Example/Menu',
  component: Menu,
  tags: ['autodocs'],
  render: (args) => ({
    components: {
      Menu,
    },
    setup() {
      return {
        ...args,
      };
    },
    template: `
    <div style="display:flex; width:200px;height:100%;padding: 20px;">
      <Menu :list=list style="width:100%"/>
    </div>  
    `,
  }),
  parameters: {
    layout: 'fullscreen',
  },
};

export const MenuList = {
  args:{
    list: [
      {
        link: '/main',
        label: 'Main',
      },
      {
        link: '/weight/weightList',
        label: '웨이트',
      },
      {
        link: '/aerobic/aerobicList',
        label: '유산소',
      },
      {
        link: '/meal/mealList',
        label: '식단',
      },
      {
        link: '/inbody/inbodyList',
        label: '인바디',
      },
    ]
  }
};
