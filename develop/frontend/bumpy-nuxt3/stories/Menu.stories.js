import Menu from '/components/menu/Menu.vue';

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
        isActive: false,
      },
      {
        link: '/weight/weightList',
        label: '웨이트',
        isActive: false,
      },
      {
        link: '/aerobic/aerobicList',
        label: '유산소',
        isActive: false,
      },
      {
        link: '/meal/mealList',
        label: '식단',
        isActive: false,
      },
      {
        link: '/inbody/inbodyList',
        label: '인바디',
        isActive: false,
      },
    ]
  }
};
