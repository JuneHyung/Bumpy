# 🐳 02_progress와 meter

## 🤔 문제 상황

Progress 컴포넌트 개발을 하며 progress와 meter tag에 대해 공부.



## 🚩 해결 방안

meter tag를 이용하여 개발 진행 예정



## 🔍 관련 Study

### 👉 Progress Tag

Progress는 진행 정도를 나타내는 바를 만드는 태그.



**Attribute**

max와 value 2가지만 존재

전체를 max로 봤을떄 value가 어느 정도인지 나타난다.

최소값은 항상 0이며, min 속성이 없다.



### 👉 Meter Tag

분수 값이나 게이지 내에서 특정 스칼라 값이 어느 정도인지를 표현할 때 사용함.

(사용량, 비율, ...)



**Attribute**

+ form : meter요소가 포함될 하나 이상의 form 요소를 명시
+ high : 높은 값으로 간주되는 범위
+ low : 낮은 값으로 간주되는 범위
+ max : 게이지의 최대값
+ min : 게이지의 최소값
+ optimum : 게이지의 최적값
+ value : **필수 속성**으로 게이지의 현재 또는 측정된 값을 명시



### 👉 적용

처음에 Progress태그를 이용해 컴포넌트를 init했지만, meter로 변경해야함.





## 📘 참고

* [form action과 onsubmit차이](https://kindle14.tistory.com/57)
* [MDN form](https://developer.mozilla.org/ko/docs/Web/HTML/Element/form)
* [MDN Events](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Building_blocks/Events)

* [MDN input submit](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit)