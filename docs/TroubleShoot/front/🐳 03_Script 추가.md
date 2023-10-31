# 🐳 03_Script 추가

## 🤔 문제 상황

회원 가입과 사용자 정보 페이지에서 우편 번호 검색 관련 기능이 필요.



## 🚩 해결 방안

Mount시 body에 `appendChild`로 script를 추가.



## 🔍 관련 Study

index.html에 추가를 해도되지만, 해당 api를 사용하는 화면에 진입했을때 추가하고 싶었습니다. <br/>왜냐하면 회원가입, 사용자 정보 2곳에서만 사용하기 때문에 이미 회원인 경우 해당 페이지로 자주 들어갈 일은 없다고 생각하여, **해당 페이지에 들어갈 때 script를 추가하는 방식**으로 개발하고 싶었습니다.

방식은 해당 페이지의 mount에서 script를 document의 body에 추가하는 방식으로 개발하였습니다.

우편 api는 `daum 우편번호 서비스` 를 활용 하여 개발하였습니다.

```typescript
// 우편번호API script 추가
const addScript = () => {
  const script = document.createElement("script");
  // script.src = process.env.ZIPCODE_SCRIPT_URL as string;
  script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'
  script.async = true;
  script.onload = () => {
    // API 로드가 완료되었을 때 실행되는 로직
    // 주소 검색 등을 처리할 수 있습니다.
  };
  document.body.appendChild(script);
};
```



## 📘 참고 

* [Daum 우편번호 서비스](https://postcode.map.daum.net/guide)
* [외부 JS 스크립트를 VueJS구성요소에 추가하는 방법](https://prostudy.tistory.com/405)