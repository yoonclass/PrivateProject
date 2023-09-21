## 목차
- [들어가며](#들어가며)
  - [프로젝트 소개](#1-프로젝트-소개)    
  - [프로젝트 기능](#2-프로젝트-기능)    
  - [사용 기술](#3-사용-기술)   
     - [백엔드](#3-1-백엔드)
     - [프론트엔드](#3-2-프론트엔드)
  - [실행 화면](#4-실행-화면)   


- [구조 및 설계](#구조-및-설계)
  - [패키지 구조](#1-패키지-구조)
  - [DB 설계](#2-db-설계)

- [개발 내용](#개발-내용)

- [마치며](#마치며)
  - [프로젝트 보완사항](#1-프로젝트-보완사항)
  - [후기](#2-후기)

## 들어가며
### 1. 프로젝트 소개
프로젝트를 시작하게 된 계기는 게시판을 직접 만들며 습득한 기술과 각종 오류에 적응하기 위해 시작하게 되었습니다.

이 프로젝트는 독서 커뮤니티 사이트를 주제로 하고 있으며, 책을 읽거나 독후감을 쓸 수 있습니다.

### 2. 프로젝트 기능

프로젝트의 주요 기능은 다음과 같습니다.
- **독후감 게시판 -** CRUD 기능, 추천수, 페이징 및 검색 처리
- **도서목록 게시판 -** CRUD 및 파일 업로드(관리자 전용), 페이징 및 검색 처리
- **사용자 -** 로그인(자동로그인, ID/PWD 찾기) 회원가입(ID 중복 검사, 이메일 인증, 비밀번호 유효성 검사), 프로필 수정
- **댓글 -** CRUD 기능

### 3. 사용 기술

#### 3-1 백엔드

##### 주요 프레임워크 / 라이브러리
- JDK 11
- Spring 5.2.24 RELEASE
- Apache Tomcat v9.0

##### Build Tool
- Maven 3.8.1

##### DataBase
- Oracle Database11g
- MyBatis

##### Tool
- Spring Tool Suite 3
- Git

#### 3-2 프론트엔드
- Html/Css
- Bootstrap
- JS/jQuery

### 4. 실행 화면
<details>
  <summary>게시글 관련</summary>
  <div markdown="1">
  <br>
  <b>1. 게시글 전체 목록</b><br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/a4a6ae49-513f-421a-9c69-7c8996ba8047.png">
    전체 목록을 페이징 처리하여 조회할 수 있다.<br><br>
  
  <b>2. 게시글 조회</b><br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/03a96b29-6514-4d59-a364-762505a9d3c1.png">
    로그인과 상관없이 누구나 글은 조회할 수 있다.<br><br>
  
  <b>3. 게시글 등록</b><br>  
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/2830a193-c86c-4d4e-bd74-92c51143d967.png">
    로그인 한 사용자만 새로운 글을 작성할 수 있고, 작성 후 목록 화면으로 redirect한다.<br><br>

  <b>4. 게시글 수정</b><br>  
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/c9c7c7af-3bc2-468e-acac-47c251ede52e.png">
    <br>본인이 작성한 글만 수정할 수 있으며 관리자 admin은 삭제만 가능하다.
    <br><br>    
    
  <b>6. 게시글 검색 및 페이징</b><br>  
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/b3b633d6-544a-4010-a3db-881fe92df8dc.png">
    <br>검색조건을 설정할 수 있다.<br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/a62bfcb1-f8d9-4cb3-8096-03b8e5fe565b.png">
    검색 키워드에 포함된 글을 모두 보여준다.<br>
    페이지 이동, 게시물 조회할 때 검색조건 값이 유지된다.
    <br><br>
  
  <b>7. 게시글 추천</b><br>  
    게시글 추천 기능은 독후감 게시판에 있습니다.<br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/b114d9eb-34bf-466f-b8ba-68bb37d5b74b.png">
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/9a1f9652-b388-4525-a198-8b6630348ce2.png">
    게시글 조회화면에서 추천을 할 수 있고 추천된 상태에서 추천취소를 할 수 있다.<br><br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/48889e8f-198d-45e6-94ac-3282bdd49217.png">
    <br><br>
  
  <b>8. 파일 업로드</b><br>  
    파일 업로드 기능은 도서목록 게시판에 있으며 관리자만 작업 가능하다.
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/46b90aa8-cf13-4e10-9513-5e3fa7d72788.png">
    <br>도서 등록 화면에서 파일 추가 및 삭제가 가능하다.(관리자 가능)<br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/46a718ec-511c-4e3e-b871-edc2b790c759.png">
    <br>도서 수정 화면에서 기존 파일 및 새 파일에 대한 추가, 삭제가 가능하다.<br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/daa77c7e-26ed-4fbe-8035-0d8d9c203944.png">
    <br>도서 조회 화면에서 파일을 다운로드 할 수 있다.(관리자, 회원 가능)
    <br><br>
  </div>
 </details>

<details>
  <summary>회원 관련</summary>   
  <div markdown="1">
  
  <br><b>1. 회원가입</b><br>  
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/2634e2ed-6c98-4a3d-a2f1-de95ce306412">
    <br>이용약관, 개인정보 수집에 동의할 경우 회원가입을 진행할 수 있다.<br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/d87e8d59-4c63-4aa4-90d8-dc9dc4480e3c.png">
    <br>ID 중복 확인, 이메일 인증, 비밀번호 유효성 검사를 진행하며 완료 시 회원 정보를 저장하고<br>
    메인 화면으로 리디렉트합니다.
    <br>
    
  <b>2. 로그인</b><br>  
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/04b5c153-6e47-44ac-b8a8-c2c2269ebae1.png">
    <br>자동 로그인 클릭 시 7일 동안 로그인이 유지됩니다.<br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/2552977e-a3fc-4cff-83b1-7d0797efdad6.png">
    <br>로그인 실패 시 빨간 글씨로 안내 문구가 출력됩니다.<br>
    로그인 실패 여부와 상관없이 아이디 찾기 및 비밀번호 재발급 화면으로 이동할 수 있습니다.<br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/c054435c-8843-48e1-a5aa-7e3b20d361fb.png">
    로그인에 성공하면 로그인 직전에 봤던 페이지로 이동하며 회원가입일 경우 접근이 거부됩니다.
    <br><br>
    
  <b>3. ID/PWD 찾기</b><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/342792c8-9302-40be-bcdc-d7b1a532f268.png">
    회원가입 시 입력한 이메일로 아이디 및 임시 비밀번호 발급이 가능합니다.
    <br>

  <b>4. 프로필 수정</b><br><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/a464b05a-b8f0-47d2-be2c-571c1b3cced7">
    <br>이미지 버튼을 눌러 프로필 이미지를 설정할 수 있고 현재 비밀번호 일치 여부를 확인하여<br>
    새 비밀번호 변경이 가능합니다.
    <br><br>
  </div>
</details>

<details>
  <summary>댓글 관련</summary>   
  <div markdown="1">
  <b>1. 댓글 작성</b><br><br>   
  <br>미로그인 사용자 화면<br>   
  <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/d836e13d-6df6-4e73-b209-0b8a060c44b9">
  <br>댓글은 로그인한 사용자만 달 수 있다.<br><br>

  <b>2. 댓글 수정/삭제</b><br><br>
  <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/0286406a-1d86-4537-b51a-b61ebf145e96">
  <br>사용자는 자신이 작성한 댓글만 수정/삭제할 수 있다.<br><br>
  <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/397f6528-5253-4484-a162-49d9307badee">
  <br>관리자는 다른 사용자의 댓글 삭제가 가능하지만 수정은 할 수 없다.<br><br>
  </div>
</details>

## 구조 및 설계   
   
### 1. 패키지 구조
   
<details>
  <summary>패키지 구조 보기</summary>  
  <br>
  <div markdown="1">
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/06d4d5f4-af4b-47d0-914f-289e41ccb6c9" alt="Image 1"><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/d13c32f6-979c-4104-a3d6-1ab86352b56b" alt="Image 2"><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/bd0e4e77-8265-4922-afb2-ea330fa4a06e" alt="Image 3"><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/d9314876-49bc-41d4-9bb4-d98baacaa1f2" alt="Image 4"><br>
    <br><b>test</b><br>
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/d7c53d1f-e9e0-4cf0-bc2e-88afe5d7e68b" alt="Image 5"><br>
  </div>
</details>

  ### 2. DB 설계
  #### ERD 설계
  <div markdown="1">
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/195b23f8-460e-42f7-ab88-73a81b6603ab">
  </div>

  
  #### 회원 테이블
  <div markdown="1">
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/ffee8c01-2adb-463f-a290-600eabe3a3c3">
  </div>
    
  
  #### 게시판 테이블
  <div markdown="1">
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/12351eaf-4c35-4b6c-9c2e-9e6979ec52dc">
    <img src="https://github.com/yoonclass/PrivateProject/assets/135006470/d8d62062-abf0-4a7d-be04-ad83ea620bb6">
  </div>
  

  ## 개발 내용
  - <a href="https://yoon-class.tistory.com/107">화면설계</a>
  - <a href="https://yoon-class.tistory.com/123">게시판 CRUD 작업</a>
  - <a href="https://yoon-class.tistory.com/135">게시판 페이징 처리</a>
  - <a href="https://yoon-class.tistory.com/138">댓글 구현</a>
  - <a href="https://yoon-class.tistory.com/139">댓글 페이징 처리</a>
  - <a href="https://yoon-class.tistory.com/147">로그인/로그아웃 구현</a>
  - <a href="https://yoon-class.tistory.com/153">중복 로그인/로그인 실패 메세지/자동 로그인 구현</a>
  - <a href="https://yoon-class.tistory.com/157">Security 게시판별 권한 설정</a>
  - <a href="https://yoon-class.tistory.com/161">댓글 보안,마이 페이지 구현</a>
  - <a href="https://yoon-class.tistory.com/168">첨부파일 기능 구현</a>
  - <a href="https://yoon-class.tistory.com/172">회원가입,ID/PWD 찾기 구현</a>
  - <a href="https://yoon-class.tistory.com/181">프로필 이미지 수정기능 구현</a>
  - <a href="https://yoon-class.tistory.com/182">프로필 이미지 댓글 적용 처리</a>
  - <a href="https://yoon-class.tistory.com/184">메인 페이지 최신글/인기글 구현</a>
  - <a href="https://yoon-class.tistory.com/187">게시판별 검색기능 구현</a>

  ## 마치며   
  ### 1. 프로젝트 보완사항   
  - 헤더에서 카테고리별 검색 기능 추가
  - 방명록 게시판 추가
  - 게시판 조회화면 날짜 표현형식 수정
   
  ### 2. 후기   

  처음 만들어본 프로젝트이기 때문에 설레는 마음도 있었지만 그 이상으로 아쉬움도 크게 남았습니다.  
  스스로 고민하며 코드를 넣고 블로그에 최대한 직관적으로 정리하려고 노력하는 과정 속에서 공부할 수 있는 부분이 많았습니다.  
  강의시간에 공부한 예제랑 비슷한 부분도 직접 설계한 프로젝트에 적용하면서 많은 오류를 마주쳤고,  
  '이 데이터를 화면에 보이려면 어떻게 해야지?', '이 부분에서 ajax 방식을 사용하는 것이 맞나?' 등  
  다양한 오류를 만나며 2,3일이 걸리더라도 노력을 멈추지 않은 덕분에 원하는 결과물을 조금씩이나마 만들 수 있었던 것 같습니다.  
  그리고 '이 코드는 왜 여기서 쓰여야하는가?', '이 화면은 다른 코드로 구현할 수 있을까?' 등  
  프로젝트 작업일지를 쓰다가 의문을 느끼고 재작업을 한 시간도 많았지만 결과적으로 스스로 1번이라도 더 고민하는 습관을 가지게 되었습니다.  
  
  이번 프로젝트는 완성도 수준을 떠나 저에게 좋은 경험이 되었다고 생각합니다.  
  이 프로젝트는 저에게 있어서 '실력이 부족하다'라는 단편적인 생각에서 '이렇게 발전해가야겠다'라는 생각으로 바뀌는 좋은 계기가 되었습니다.  
  방향성 있는 지속적인 노력을 통해 더 나은 웹 애플리케이션을 만들 수 있도록 할 것입니다.  
  
  긴 글 읽어주셔서 감사합니다.
