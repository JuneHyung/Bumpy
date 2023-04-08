# 🐳 03_GITHUB action 활용

## 🤔 문제 상황

가장 핫한 github action 을 활용하여 AWS EC2에 CI/CD를 하는 내용을 구현하고자 함

해결 방안에 github action 코드를 작성하였지만....

AWS EC2 SSH 사용법이 조금 불편하다...

단순하게 SSH 키를 등록하고 사용하는 것이 아니라 AWS 계정에 대해 하위 사용자를 생성하고

사용자에 키를 등록하고 권한 범위를 직접 설정해주어야한다.

근데 일단 등록한 키로 putty 등을 활용하여 console 연결을 시도하려했으나, 연결이 되지않아

더 찾아봐야한다.

## 🚩 해결 방안

아래와 같이 github 코드는 작성하였다..

1. 빌드하여 docker 이미지를 생성하고
2. AWS ECR 이라는 repository에 로그인하여 이미지를 복사한다음
3. EC2로 배포하여 docker run을 실행한다.

on:
  push:
    branches:
      - main

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
    - name: Checkout code
      uses: actions/checkout@v2
      
    - name: Build and tag Docker image
      run: |
        docker build -t my-spring-boot-app .
        docker tag my-spring-boot-app:latest 522398560360.dkr.ecr.ap-northeast-2.amazonaws.com/my-spring-boot-app:latest
        
    - name: Login to ECR
      id: login-ecr
      uses: aws-actions/amazon-ecr-login@v1
      
    - name: Push Docker image to ECR
      run: |
        docker push 522398560360.dkr.ecr.ap-northeast-2.amazonaws.com/my-spring-boot-app:latest
        
    - name: Deploy to EC2
      run: |
        sudo apt-get update
        sudo apt-get install -y awscli
        aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 522398560360.dkr.ecr.ap-northeast-2.amazonaws.com
        docker stop my-spring-boot-app || true
        docker rm my-spring-boot-app || true
        docker pull 522398560360.dkr.ecr.ap-northeast-2.amazonaws.com/my-spring-boot-app:latest
        docker run -d --name my-spring-boot-app -p 8080:8080 522398560360.dkr.ecr.ap-northeast-2.amazonaws.com/my-spring-boot-app:latest



## 🔍 관련 Study

문제 상황에 대해 조사한 정보들을 정리하여 작성해 주세요.



## 📘 참고

조사한 정보들을 참고한 링크를 작성해 주세요.

(링크주소가 너무 긴 경우 아래 형식으로 줄여서 작성)

```text
[보여줄 링크 text](링크 주소)
```

