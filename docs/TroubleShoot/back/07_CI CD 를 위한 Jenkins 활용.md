# 🐳 05_SpringBoot Security 작성하기

## 🤔 문제 상황

npm build와 gradle build를 모두 편리하게 CI/CD를 하기 위해 툴이 필요해짐

이전에 사용했던 Jenkins가 파이프라인을 활용할 경우 손쉽게 설정할 수 있어 도입 고려

## 🚩 해결 방안

docker 환경으로 개발을 진행하고 있어, Jenkins를 docker container로 올림

GitHub의 Webhook으로 Jenkins의 주소로 토큰을 보내도록 설정하여 자동 빌드 및 배포가 진행 되도록 설정

Jenkins의 파이프라인을 활용하여 frontend 빌드시에는 npm build 후 배포

backend 빌드시에는 gradle build를 하여 배포

## 🔍 관련 Study

## 📘 참고

