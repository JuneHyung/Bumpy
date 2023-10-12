# 🐳 04_Vue3 Storybook

## 🤔 문제 상황

nuxt3에서 storybook사용하려 했으나, nuxt용은 nuxt2에 맞춰져있고, nuxt3에서 사용하기위해 vue3용 storybook을 설치가 필요.



## 🚩 해결 방안

vue3 storybook을 설치해 사용하기 위해 공식문서의 내용을 정리.



## 🔍 관련 Study

## 🌏 why Vue3 storybook?

nuxt용 storybook은 nuxt2와는 호환되지만 nuxt3용이 없어, vue3 storybook을 사용해 적용.

공식문서와 설치후 자동으로 만들어진 예제를 가지고 정리.

설치를 하고나면, 관련된 파일들과 명령어가 자동등록됨. <br/>(vite를 사용한다면, webpack -> vite로 바꿔주는 작업 필요.)

```json
{
  "private": true,
  "scripts": {
	...
    "storybook": "storybook dev -p 6006",
    "build-storybook": "storybook build"
  },
  "devDependencies": {
    "@babel/preset-env": "^7.21.4",
    "@babel/preset-typescript": "^7.21.4",
    "@storybook/addon-essentials": "^7.0.2",
    "@storybook/addon-interactions": "^7.0.2",
    "@storybook/addon-links": "^7.0.2",
    "@storybook/blocks": "^7.0.2",
    "@storybook/testing-library": "^0.0.14-next.2",
    "@storybook/vue3": "^7.0.2",
    "@storybook/vue3-webpack5": "^7.0.2",
    "@vue/compiler-sfc": "^3.2.47",
    "nuxt": "^3.2.3",
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "storybook": "^7.0.2",
    "vue-loader": "^17.0.1"
  },
  "dependencies": {
	...
  }
}
```



루트 디렉토리에 `.storybook`과 `storybook`폴더를 생성됨.<br/>(`.storybook`은 storybook관련 설정들, `storybook` 은 story들을 작성하는 폴더 인듯 하다.)

**명령어** : `npm run storybook`

story파일들은 뒤에 `.stories.js` 또는 `.stories.ts`를 붙인다.<br/>(모듈기반표준인 CSF(Component Story Format)로 작성됨.)

생성된 예제를 통해 알아가보자.

```vue
// stories/Button.vue
<template>
  <button type="button" :class="classes" @click="onClick" :style="style">{{ label }}</button>
</template>

<script>
import './button.css';
import { reactive, computed } from 'vue';

export default {
  name: 'my-button',

  props: {
    label: {
      type: String,
      required: true,
    },
    primary: {
      type: Boolean,
      default: false,
    },
    size: {
      type: String,
      validator: function (value) {
        return ['small', 'medium', 'large'].indexOf(value) !== -1;
      },
    },
    backgroundColor: {
      type: String,
    },
  },

  emits: ['click'],

  setup(props, { emit }) {
    props = reactive(props);
    return {
      classes: computed(() => ({
        'storybook-button': true,
        'storybook-button--primary': props.primary,
        'storybook-button--secondary': !props.primary,
        [`storybook-button--${props.size || 'medium'}`]: true,
      })),
      style: computed(() => ({
        backgroundColor: props.backgroundColor,
      })),
      onClick() {
        emit('click');
      },
    };
  },
};
</script>

```



```typescript
// stories/Button.stories.js
import MyButton from './Button.vue';

// More on how to set up stories at: https://storybook.js.org/docs/7.0/vue/writing-stories/introduction
export default {
  title: 'Example/Button',
  component: MyButton,
  tags: ['autodocs'],
  argTypes: {
    backgroundColor: {
      control: 'color',
    },
    onClick: {},
    size: {
      control: {
        type: 'select',
      },
      options: ['small', 'medium', 'large'],
    },
  },
};

// More on writing stories with args: https://storybook.js.org/docs/7.0/vue/writing-stories/args
export const Primary = {
  args: {
    primary: true,
    label: 'Button',
  },
};

export const Secondary = {
  args: {
    label: 'Button',
  },
};

export const Large = {
  args: {
    size: 'large',
    label: 'Button',
  },
};

export const Small = {
  args: {
    size: 'small',
    label: 'Button',
  },
};

```

![Storybook Button Example](D:\Bumpy\docs\TroubleShoot\front\readme_images\04_storybook_Button_Example.png)

작성된 부분으로 살펴봤을 때,

해당 컴포넌트의 Props, Event들을 확인할 수 있고, size에 따른 버튼들을 설정해주어 따로 다 확인할 수 있다.





## 🌏 Write Stories

### 👉 How to write stories?

Storybook은 UI 구성요소의 렌더링된 상태를 캡쳐한다.

argumetns를 세팅한 상태의 컴포넌트를 리턴하는 함수이다.

Storybook은 `React의 props`, `Vue의 props`, `Angular의 @Input` 및 기타 유사한 개념에 대해 이야기할 때 **args**를 사용한다.



### 👉 Where to put storeis?

컴포넌트의 스토리들은 구성 요소 파일과 함께 있는 스토리 파일에 정의 하며,

스토리 파일은 **개발 전용**이고, production 번들에 포함되지 않는다.



### 👉 Default Export

`default export metadata`는 storybook이 스토리를 나열하고 애드온에서 사용하는 정보를 제공하는 방법을 제어한다.

>  ! 참고
>
>  Storybook 7.0부터 스토리 제목은 빌드 프로세스의 일부로 정적으로 분석됩니다.
>
>  default export에는 정적으로 읽을 수 있는 제목 속성 또는 자동 제목을 계산할 수 있는 구성 요소 속성이 포함되어야 함.
>
>  id속성을 사용해 스토리 URL을 사용자 정의하는 것도 정적으로 읽을 수 있어야 함.



### 👉 Defining Stories

CSF파일에 명명된 **내보내기(export)를 사용**해 구성요소의 스토리를 정의합니다.

`UpperCamelCase`를 사용하는 것이 좋다.



### 👉 Write Stories

story는 컴포넌트를 어떻게 render하는지 설명하는 function이다.

구성요소당 여러 스토리를 가질 수 있고, 가장 간단한 방법은 다른 인수를 사용해 여러번 렌더링 하는 것.

하지만, 스토리가 많은 경우 반복될 수 있다. => `compoents: {Button}`등 이런부분들

**Example**

```typescript
// Button.stories.ts
import type { Meta, StoryObj } from '@storybook/vue3';
import Button from './Button.vue';

const meta: Meta<typeof Button> = {
  title: 'Button',
  component: Button,
};

export default meta;
type Story = StoryObj<typeof Button>;

export const Primary: Story = {
  render: () => ({
    components: { Button },
    template: '<Button background="#ff0" label="Button" />',
  }),
};

export const Secondary: Story = {
  render: () => ({
    components: { Button },
    template: '<Button background="#ff0" label="😄👍😍💯" />',
  }),
};

export const Tertiary: Story = {
  render: () => ({
    components: { Button },
    template: '<Button background="#ff0" label="📚📕📈🤓" />',
  }),
};
```



### 👉 Args 사용

args를 통해 패턴을 구체화.

각 스토리에 대해 작성하고 유지 관리해야 하는 상용구 코드를 줄인다.

storybook의 control pannel을 사용해 실시간으로 편집해볼 수 있다.<br/>즉, 동적으로 변경하여 test를 수행하고, edge case를 찾을 수 있다.<br/>addon을 이용해 더 강화할 수 있다.

[공식문서 자세한 내용 보러가기](https://storybook.js.org/docs/vue/writing-stories/introduction)

```typescript
// Button.stories.ts
...
export const Primary: Story = {
  render: (args) => ({
    components: { Button },
    setup() {
      return { args };
    },
    template: '<Button v-bind="args" />',
  }),
  args: {
    background: '#ff0',
    label: 'Button',
  },
};

export const Secondary: Story = {
  render: (args) => ({
    components: { Button },
    setup() {
      return { args };
    },
    template: '<Button v-bind="args" />',
  }),
  args: {
    ...Primary.args,
    label: '😄👍😍💯',
  },
};

export const Tertiary: Story = {
  render: (args) => ({
    components: { Button },
    setup() {
      return { args };
    },
    template: '<Button v-bind="args" />',
  }),
  args: {
    ...Primary.args,
    label: '📚📕📈🤓',
  },
};
```



**Using the play function**

`play function`과 @storybook/addon-interactions`는 사용자 개입이 필요한 구성여소 시나리오 테스트를 위한 도우미 메서드이다.

스토리가 렌더링 되면 실행되는 작은 코드 스니펫이다.

아래 링크는 양식 구성요소의 유효선 검사를 하는 예제이다.

[예제 보러가기](https://storybook.js.org/docs/vue/writing-stories/introduction#using-the-play-function)



### 👉 Using parameters

Parameters는 스토리에 대한 정적 metadata를  정의하는 Storybook방법이다.

**Example**

앱의 다른 컴포넌트와 다른 배경set에 대한 Button구성요소를 테스트 한다고 가정.

```typescript
// Button.stories.ts
import type { Meta } from '@storybook/vue3';

import Button from './Button.vue';

const meta: Meta<typeof Button> = {
  title: 'Button',
  component: Button,
  //👇 Creates specific parameters for the story
  parameters: {
    backgrounds: {
      values: [
        { name: 'red', value: '#f00' },
        { name: 'green', value: '#0f0' },
        { name: 'blue', value: '#00f' },
      ],
    },
  },
};

export default meta;
```

버튼 스토리가 선택될 때마다 배경 애드온이 스스로 재구성하도록 지시합니다.



## 🌏 Docs

Autodocs로 자동화된 문서를 생성하면 지속 가능한 문서 패턴을 구축할 수 있다.

하지만, 적합하지 않을 경우도 있기 때문에 그런 경우 확장하여 `Doc Blocks`와 함께 `MDX`를 결합하는 것이 좋다.

### 👉 AutoDocs

Storybook Autodocs는 UI 구성 요소에 대한 포괄적인 문서를 신속하게 생성하는 데 도움이 되는 강력한 도구.

Autodocs를 활용하면 story를 MDX 및 Doc Blocks로 추가 확장할 수 있는 살아있는 문서로 변환하여 구성 요소의 기능을 명확하고 간결하게 이해할 수 있습니다.

**Setting**

story의 default export에 **tags**을 추가.

```typescript
const meta: Meta<typeof Button> = {
  title: 'Button',
  component: Button,
  //👇 Enables auto-generated documentation for the component story
  tags: ['autodocs'],
  argTypes: {
    backgroundColor: { control: 'color' },
  },
};
```



**Configure**

기본적으로 zero-config를 지원. (빈 config)

태그 구성 속성을 통해 활성화된 각 스토리에 대한 문서 페이지를 자동으로 설정

Storybook cofing 파일을 확장하고, 문서 생성 방법을 제어하는 추가 옵션을 제공할 수 있다.

```typescript
// .storybook/main.ts

// Replace your-framework with the framework you are using (e.g., react-webpack5, vue3-webpack5)
import type { StorybookConfig } from '@storybook/your-framework';

const config: StorybookConfig = {
  stories: ['../src/**/*.stories.@(js|ts)'],
  addons: [
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    '@storybook/addon-interactions',
  ],
  framework: {
    name: '@storybook/your-framework',
    options: {},
  },
  docs: {
    autodocs: 'tag', // 자동 생성 문서 페이지를 구성함.
    defaultName: 'Documentation', // 자동 생성된 문서 페이지의 이름을 바꾼다.
  },
};

export default config;
```



### 👉 Write a custom template

기본 문서 템플릿을 교체하려면, UI구성파일(ex) .storybook/preview.js)를 확장하고, 

docs 파라미터를 도입할 수 있다.



**MDX 사용**

JSX처리가 구성되지 않는 React가 아닌 프로젝트에서 유용.

일반 프로젝트에서 MDX파일은 일반문서이기 때문에, **isTemplate**속성에 MDX파일이 문서 템플릿임을 나타내 줘야 한다.

```mdx
{/* DocumentationTemplate.mdx */}

import { Meta, Title, Subtitle, Description, Primary, Controls, Stories } from '@storybook/blocks';

{/* 
  * 👇 The isTemplate property is required to tell Storybook that this is a template
  * See https://storybook.js.org/docs/react/api/doc-block-meta
  * to learn how to use
*/}

<Meta isTemplate />

<Title />

# Default implementation

<Primary />

## Inputs

The component accepts the following inputs (props):

<Controls />

---

## Additional variations

Listed below are additional variations of the component.

<Stories />
```

이후, `.storybook/preview.js`나 다른 스토리파일에서 가져오기를 통해 사용할 수 있다.

```typescript
// .storybook/preview.jsx

import DocumentationTemplate from './DocumentationTemplate.mdx';

export default {
  parameters: {
    docs: {
      page: DocumentationTemplate,
    },
  },
};
```



> ! Advanced
>
> Doc Container를 customize하거나 기본 테마를 재정의 하는 것도 가능하다.
>
> 자세한 내용은 필요시 아래 링크를 참고.
>
> [Advanced Configuration](https://storybook.js.org/docs/vue/writing-docs/autodocs#advanced-configuration)



## 🌏 Trouble shoot 참고

### 👉 자동 생성된 문서가 monorepo에 보이지 않는다.

Autodocs기능은 스토리에 대한 문서를 자동으로 생성하도록 구축되있는데 단일 레포지토리 설정으로 작업하는 경우 문서 일부가 생성되지 않는 문제가 발생할 수 있다.

**이를 해결하기 위한 권장사항**

package의 루트 대신 구성요소를 직접 참조하도록 export문을 업데이트 .

```typescript
// MyComponent.stories.ts|tsx

// Replace your-framework with the name of your framework
import type { Meta } from '@storybook/your-framework';

// ❌ Don't use the package's index file to import the component.
import { MyComponent } from '@component-package';

// ✅ Use the component's export to import it directly.
import { MyComponent } from '@component-package/src/MyComponent';

const meta: Meta<typeof MyComponent> = {
  /* 👇 The title prop is optional.
   * See https://storybook.js.org/docs/react/configure/overview#configure-story-loading
   * to learn how to generate automatic titles
   */
  title: 'MyComponent',
  component: MyComponent,
};

export default meta;
```



`.storybook/main.js|ts`또한 TypeScript를 사용하여 개발하는 경우 다음을 포함하도록 Storybook의 구성 파일(예: )을 업데이트해야 할 수 있습니다.

```typescript
// .storybook/main.ts

// Replace your-framework with the framework you are using (e.g., react-webpack5, vue3-webpack5)
import type { StorybookConfig } from '@storybook/your-framework';

const config: StorybookConfig = {
  stories: ['../src/**/*.mdx', '../src/**/*.stories.@(js|ts)'],
  addons: [
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    '@storybook/addon-interactions',
  ],
  framework: {
    // The name of the framework you want to use goes here
    name: '@storybook/your-framework',
    options: {},
  },
  typescript: {
    // Overrides the default Typescript configuration to allow multi-package components to be documented via Autodocs.
    reactDocgen: 'react-docgen',
    skipBabel: true,
    check: false,
  },
};

export default config;
```



## 🌏 ETC

추가적으로 addons나 Test관련 내용은 공식문서에서 확인할 수 있다.

사용하게 된다면, 새로 정리할 에정.



## 📘 참고

[Storybook for vue3](https://storybook.js.org/docs/vue/get-started/install/)
