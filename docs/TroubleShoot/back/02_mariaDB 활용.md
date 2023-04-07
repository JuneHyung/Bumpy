# 🐳 02_mariaDB 활용

## 🤔 문제 상황

오픈소스인 maria DB 를 사용하기로 결정하면서, 미리 해야하는 설정들과 필요한 기능 파악 중 대표적 문제 발견

mariaDB는 대소문자 구분 문제가 있음
 
lower_case_table_names 옵션 값

lower_case_table_names=0 : 테이블 생성 및 조회 시 대/소문자 구분

lower_case_table_names=1 : 입력 값을 모두 소문자로 인식. 즉, 대소문자 구분하지 않음

lower_case_table_names=2 : 윈도우에서 대/소문자를 구분해서 테이블 생성

OS별 default value

linux, unix : 0

windows : 1

mac OS : 2

## 🚩 해결 방안

/etc/my.cnf.d/server.cnf 파일을 열고 mysqld 밑에 'lower_case_table_names=1' 추가

## 🔍 관련 Study

문제 상황에 대해 조사한 정보들을 정리하여 작성해 주세요.


## 📘 참고

[대소문자 구분 설정](https://yjh5369.tistory.com/entry/MariaDB-104-%EB%8C%80%EC%86%8C%EB%AC%B8%EC%9E%90-%EA%B5%AC%EB%B6%84-%EC%84%A4%EC%A0%95%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)

