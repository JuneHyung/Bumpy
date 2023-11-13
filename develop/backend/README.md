# Bumpy Backend

## 목표
- Bumpy Frontend를 위한 백엔드 WAS 제작
- 로그인 및 인증
- 컨텐츠 데이터 제공
- 그 외 Util

## 폴더 구조
```
│ BumpyApplication.java
│
├─config
│      CacheConfig.java
│      JacksonConfig.java
│      LogConfig.java
│      MailSenderConfig.java
│      QueryDslConfig.java
│      SwaggerConfig.java
│
├─constants
│      BigDecimalFormat.java
│      DomainConstants.java
│
├─database
│  ├─entity
│  │  ├─cm
│  │  │      CmHFile.java
│  │  │      CmMUsednm.java
│  │  │
│  │  ├─composite
│  │  │      CmMUsednmId.java
│  │  │      DataHCardioId.java
│  │  │      DataHInbodyId.java
│  │  │      DataHMealId.java
│  │  │      DataHWeightId.java
│  │  │
│  │  ├─data
│  │  │      DataHAerobic.java
│  │  │      DataHInbody.java
│  │  │      DataHMeal.java
│  │  │      DataHWeight.java
│  │  │
│  │  └─usr
│  │          UsrHEmailauth.java
│  │          UsrMUsr.java
│  │
│  └─repository
│      ├─cm
│      │      CmHFileRepository.java
│      │      CmMUsednmRepository.java
│      │
│      ├─data
│      │      DataHAerobicRepository.java
│      │      DataHInbodyRepository.java
│      │      DataHMealRepository.java
│      │      DataHWeightRepository.java
│      │
│      └─usr
│              UsrHEmailauthRepository.java
│              UsrMUsrRepository.java
│
├─domain
│  ├─common
│  │      CommonController.java
│  │      CommonService.java
│  │
│  ├─main
│  │  │  MainController.java
│  │  │  MainService.java
│  │  │
│  │  └─dto
│  │          AerobicResponseDto.java
│  │          ChartAerobicResponseDto.java
│  │          ChartRequestDto.java
│  │          ChartWeightResponseDto.java
│  │          UserInfoResponse.java
│  │          WeightResponseDto.java
│  │
│  ├─screen
│  │  ├─aerobic
│  │  │  │  AerobicController.java
│  │  │  │  AerobicService.java
│  │  │  │
│  │  │  ├─dto
│  │  │  │      AerobicActivityResponseDto.java
│  │  │  │      AerobicResponse.java
│  │  │  │      DataHAerobicDto.java
│  │  │  │
│  │  │  └─projection
│  │  │          DataHAerobicInfo.java
│  │  │
│  │  ├─dto
│  │  │      SearchDateRequestDto.java
│  │  │      SearchMonthRequestDto.java
│  │  │      SearchRequestDto.java
│  │  │      SearchYoutubeDto.java
│  │  │
│  │  ├─inbody
│  │  │  │  InbodyController.java
│  │  │  │  InbodyService.java
│  │  │  │
│  │  │  └─dto
│  │  │          DataHInbodyDto.java
│  │  │          InbodyResponse.java
│  │  │          SearchInbodyDto.java
│  │  │
│  │  ├─meal
│  │  │  │  MealController.java
│  │  │  │  MealService.java
│  │  │  │
│  │  │  └─dto
│  │  │          DataHMealDto.java
│  │  │          MealResponse.java
│  │  │
│  │  └─weight
│  │      │  WeightController.java
│  │      │  WeightService.java
│  │      │
│  │      ├─dto
│  │      │      DataHWeightDto.java
│  │      │      WeightActivityResponseDto.java
│  │      │      WeightResponse.java
│  │      │
│  │      └─projection
│  │              DataHWeightInfo.java
│  │
│  ├─signup
│  │  │  SignUpController.java
│  │  │  SignUpService.java
│  │  │
│  │  └─dto
│  │          EmailRequestDto.java
│  │          UserIdRequestDto.java
│  │          UsrMUsrDto.java
│  │
│  └─user
│      │  UserController.java
│      │  UserService.java
│      │
│      └─dto
│              UserSearchDto.java
│              UserUpdateDto.java
│
├─exception
│      GlobalExceptionHandler.java
│      GlobalUnCheckedExceptionHandler.java
│
├─security
│  │  AccessDeniedHandlerImpl.java
│  │  AuthenticationEntryPointImpl.java
│  │  SecurityConfig.java
│  │
│  └─principal
│          PrincipalDetails.java
│          PrincipalDetailsService.java
│
└─util
    ├─customannotation
    │      BigDecimalScale.java
    │      BigDecimalSerializer.java
    │      Password.java
    │      PasswordValidator.java
    │
    ├─dto
    │  │  PasswordDto.java
    │  │  PictureDto.java
    │  │  ResultMap.java
    │  │  StringMap.java
    │  │
    │  └─youtube
    │          ResponseKeywordSearchDto.java
    │          SearchListKeywordDto.java
    │
    └─funtion
            FieldValueUtil.java
            UserIdValidator.java
            YoutubeService.java
```

## 기술 스택
- Maria DB
- Spring Boot
- Redis DB
- Jenkins

## DB 구조
![Bumpy DB 구조도](/develop/backend/bumpy_ERD.png)

## 기능별 주요 로직

### 기록 CRUD
- mariaDB와 Spring Boot를 이용한 CRUD API 작성
- JSON형태로 응답하며, 차트와 캘린더를 위한 객체도 DTO형태로 작성하여 관리

### 회원가입 중 이메일 인증
- 인증코드 발송용 구글 이메일을 준비
- Java Mail Sender를 활용하여 인증코드를 전송
- 인증코드 및 인증 여부, 만료 시간, 회원가입 시도중인 아이디를 DB에 저장하여 상태 기억 및 중복 시도 차단

### 사진파일 관리
- UUID를 통한 서버 저장 및 DB를 통한 목록 관리
- 사용자 요청시 base64를 통해 JSON형태로 제공

### 유튜브 목록 제공
- Youtube API를 통해 관련된 영상 목록 제공

### 캐시 서버 활용
- Redis를 활용한 조회 캐싱 적용
- JWT 리프레시 토큰 저장용으로 활용

## 후기 & 아쉬웠던 점
- 여러 WAS를 제작하다보니 무엇이 더 좋은 구조인지 고민하고 싶어졌다
- API 명세서는 잦은 수정과 게시가 필요해 결국 Notion에서 Postman으로 옮겨오게 되었고, 결국 실용성이 중시되어야한다고 생각한다.
- DTO 작명이 어렵다
- 로그용 에러메시지와 요청 응답용 에러메시지는 구분되어야하지 않을까 라는 생각을 하게되었다.
