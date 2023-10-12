# 🐳 04_SpringBoot 3.0 도입하기

## 🤔 문제 상황

Swagger가 Spring Boot 3.0 이상에서 제대로 동작하지 않는 문제가 있어, Spring Boot 3.0 변경사항을 확인하고 꼭 필요한지 검토 후 도입하기로 결정

아래의 링크에서 Spring Boot 3.0 변경사항이 잘 설명되어있음.

주요 변경사항

- 최소 Java 17 버전 사용
- 빠른 빌드 속도
- 그 외 다양한 많은 기능들...

대부분이 잘 모르는 기능들이고, 내가 활용하기에는 아직 이른 내용들이 많았지만,

1. 새로운 HTTP 기준에 맞추었고
2. Cloud환경에서의 활용도가 높아졌고
3. 관리, 모니터링 측면에서 기능이 많아졌고
4. 성능 향상이 이루어졌다... 가 주를 이룬 것으로 보인다.

### Swagger를 사용할 수 있는가

이번 Spring Boot 3.0 으로 넘어오면서 AntPathMatcher가 PathPatternParser로 변경되면서, 기존 AntPathMatcher 방식을 사용하는 Swagger가 제대로 동작하지 않는 문제가 있는 것으로 확인헀다.

물론 Spring Boot 설정을 AntPathMatcher를 사용하도록하면 동작하지만, 업데이트해서 높인 성능을 굳이 낮출 필요가 있나 싶었다.

그런데 springfox가 아닌 springdoc을 활용하면 가능하다는 내용을 찾았다.

## 🚩 해결 방안

[Springdoc v2](https://springdoc.org/v2/)

Springdoc v2 에서 Spring Boot 3.x를 지원한다는 내용을 확인했다.

아래의 참고에 있는 블로그에서 해결방안을 stackOverflow에서 찾았다는 글을 찾았고.. 나도 확인해보았다.

위 링크를 확인해보면 introduction에 Spring-boot v3 을 지원한다고 적혀있고, 이를 적용해보니 정상적으로 작동함을 확인했다.

## 🔍 관련 Study

문제 상황에 대해 조사한 정보들을 정리하여 작성해 주세요.

## 📘 참고

조사한 정보들을 참고한 링크를 작성해 주세요.

(링크주소가 너무 긴 경우 아래 형식으로 줄여서 작성)

[spring Boot 3.0 변경사항](https://revf.tistory.com/260)

[Swagger를 SpringBoot 3.x에 적용](https://velog.io/@kjgi73k/Springboot3%EC%97%90-Swagger3%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)
