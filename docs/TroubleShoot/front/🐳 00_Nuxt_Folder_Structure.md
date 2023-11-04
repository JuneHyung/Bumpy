# 🐳 Nuxt Folder Structure

## 🤔 문제 상황

Nuxt3의 경우, 프로젝트 생성시 폴더를 직접 생성해서 작성해야한다. <br/>Nuxt의 폴더 구조에 대해 간단하게 알아보자.



## 🚩 해결 방안

## 🔍 관련 Study

Nuxt를 생성하고, 생성된 폴더의 구조에 대해 알아보자. <br/>

### 👉 폴더 구조

📦PJT <br/>
 ┣ 📂 .nuxt					# 넉스트 빌드 결과물 폴더 <br/>
 ┣ 📂 assets				 # style, image, font등 웹 리소스 폴더 <br/>
 ┣ 📂 components		# 사용할 컴포넌트 폴더 <br/>
 ┣ 📂 layouts				# 레이아웃 컴포넌트 폴더 <br/>
 ┣ 📂 middleware		 # middleware파일을 저장해두는 폴더 <br/>
 ┣ 📂 pages				  # 화면파일들을 저장해두는 폴더 - 자동라우팅 적용. <br/>
 ┣ 📂 plugins 				# Vue Plugin 폴더  <br/>
	... <br/>
 ┗ 📂 store					# 상태 관리 폴더  <br/>

주의 깊게 봐야할 부분은 pages폴더와 layouts폴더이다.

Nuxt3의 특징으로 페이지의 주는 레이아웃 컴포넌트를 작성할 수 있고, page별로 다른 layouts을 커스텀하여 추가할 수 있다. 

그 다음 pages폴더에서 작성한 폴더와 파일이름으로 라우팅을 자동으로 설정할 수 있다.






## 📘 참고

* [Nuxt3 공식문서 - directory-structure](https://nuxt.com/docs/guide/directory-structure/utils)
* [Nuxt 폴더구조](https://joshua1988.github.io/vue-camp/nuxt/folder-structure.html#nuxt의-폴더-구조-2)