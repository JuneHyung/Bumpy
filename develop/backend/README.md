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


## 후기 & 아쉬웠던 점