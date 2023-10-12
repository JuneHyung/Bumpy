# 🐳 05_SpringBoot Security 작성하기

## 🤔 문제 상황

Spring Boot Security를 작성하는 방법이 변경되어 사용하던 방법이 Deprecated 되었다.

이에 따라 새로운 방식에 맞춰 작성하고자 한다.

기존방식 : WebSecurityConfigurerAdapter를 내려받아 설정들을 overide해서 작성

새로운 방식 : SecurityFilterChain을 @Bean으로 등록하여 설정들을 작성하여 사용

## 🔍 관련 Study

새로운 방식에 맞춰 작성하기 위해 일단 intelliJ에서 소개한 페이지로 넘어가 글을 확인하였다.

https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

기존 WebSecurityConfigurerAdapter을 사용하여 작성하던 방식을

새로운 방식으로 작성하는 방법에 대해 자세하게 소개하였다.

특히 자주 쓰는 http Builder의 경우 SecurityFilterChain으로 작성하여 빌드하여 리턴하면

그대로 같은 문법을 사용하여 사용할 수 있음을 확인할 수 있다.

그 외에 AuthenticationManager도 별도로 @Bean으로 선언하여,

어떤 방식으로 인증을 처리할 것인지 작성할 수 있음이 적혀있다.

아래는 VMware 기술자분이 설명하는 새로운 Security 적용법 설명 영상이다.

https://www.youtube.com/watch?v=s4X4SJv2RrU
