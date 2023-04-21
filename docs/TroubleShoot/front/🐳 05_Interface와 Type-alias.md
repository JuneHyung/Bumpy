# 🐳 05_Interface와 Type-alias

## 🤔 문제 상황

Interface와 type-alias에 대한 차이를 확실히 알고가기 위해.



## 🚩 해결 방안

### 결론

`type-alias`는 모든 타입에서 사용가능하다.

`interface`는 객체에만 사용이 가능하다.

`interface`와 `type-alias`둘 다 객체에 대한 타입을 선언할 때 사용될 수 있다.

`interface`는 선언을 다시하는 것으로 확장이 되지만, `type-alias`는 그렇지 않다.



확장 측면에서 사용 용도가 달라진다.

앞으로 사용 시 아래에 따라 구분해서 사용하자.

* 확장이 불가능한 타입 : `type-alias`
* 확장이 가능한 타입 : `interface`



## 🔍 관련 Study

둘 다 객체의 타입의 이름을 지정하는 방법들.

### 공통점

### 👉 Class를 통한 구현이 가능.

둘 다 class를 통한 구현이 가능하다.

```javascript
type PositionType = {
  x: number;
  y: number;
};
interface PositionInterface {
  x: number;
  y: number;
}

class Pos1 implements PositionType {
  x: number;
  y: number;
}
class Pos2 implements PositionInterface {
  x: number;
  y: number;
}
```



### 차이점

### 👉 interface는 객체에만 사용 가능

`type`은 모든 타입에 이름을 달 수 있지만, interface는 객체에서만 사용이 가능하다.

```typescript
interface FooInterface {
  value: string
}

type FooType = {
  value: string
}

type FooOnlyString = string
type FooTypeNumber = number

// 불가능
interface X extends string {}
```



### 👉 확장 방법의 차이

```typescript
interface PeopleInterface {
  name: string
  age: number
}

interface StudentInterface extends PeopleInterface {
  school: string
}
```

```typescript
type PeopleType = {
  name: string
  age: number
}

type StudentType = PeopleType & {
  school: string
}
```



### 👉 선언적 확장

`interface`에서 대부분기능들은 `type`에서 가능.

`type`은 새로운 속성을 추가하기 위해 다시 같은 이름으로 선언할 수 없지만, interface는 가능하다.<br/>(확장 기능에 있어서는 interface가 좀 더 준수한 성능을 가지고 있다고 함.)

```typescript
interface Window {
  title: string
}

interface Window {
  ts: TypeScriptAPI
}

// 같은 interface 명으로 Window를 다시 만든다면, 자동으로 확장이 된다.

const src = 'const a = "Hello World"'
window.ts.transpileModule(src, {})
```

```typescript
type Window = {
  title: string
}

type Window = {
  ts: TypeScriptAPI
}

// Error: Duplicate identifier 'Window'.
// 타입은 안된다.
```





### 👉 computed value의 사용

`type`은 가능하지만 `interface`는 불가능

```typescript
type names = 'firstName' | 'lastName'

type NameTypes = {
  [key in names]: string
}

const yc: NameTypes = { firstName: 'hi', lastName: 'yc' }

interface NameInterface {
  // error
  [key in names]: string
}
```



### 👉 라이브러리를 사용할 때

선언 병합을 통해 타입확장이 가능하다.

```typescript
// @emotion/react/types
export interface Theme {}

// emotion.d.ts
import '@emotion/react';

declare module '@emotion/react' {
  export interface Theme {
    colors: typeof Colors;
  }
}
```



## 📘 참고

https://www.typescriptlang.org/docs/handbook/2/everyday-types.html#differences-between-type-aliases-and-interfaces

https://mine-it-record.tistory.com/592

https://tecoble.techcourse.co.kr/post/2022-11-07-typeAlias-interface/