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
    
    **1. 게시글 전체 목록**
    ![게시글 관련](https://github.com/yoonclass/PrivateProject/assets/135006470/a4a6ae49-513f-421a-9c69-7c8996ba8047)
    전체 목록을 페이징 처리하여 조회할 수 있다. 

    **2. 게시글 조회**
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/03a96b29-6514-4d59-a364-762505a9d3c1)
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/32d4f799-5333-44a8-9cbb-a356b5d6363d)
    로그인과 상관없이 누구나 글은 조회할 수 있다.
    
    **3. 게시글 등록**
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/2830a193-c86c-4d4e-bd74-92c51143d967)
    로그인 한 사용자만 새로운 글을 작성할 수 있고, 작성 후 목록 화면으로 redirect한다.

    **4. 게시글 수정**  
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/c9c7c7af-3bc2-468e-acac-47c251ede52e)
    본인이 작성한 글만 변경을 눌러 수정할 수 있으며 관리자 admin은 삭제만 가능하다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/2151d8bf-971b-47e2-a3e7-49869ca75ba6)
    제목과 내용만 수정할 수 있게 하고 수정 및 삭제할 경우 목록 화면으로 redirect 한다.   
    목록 버튼을 누를 시 목록 화면으로 돌아간다.

    **6. 게시글 검색 및 페이징**
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/b3b633d6-544a-4010-a3db-881fe92df8dc)
    검색조건을 설정할 수 있다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/a62bfcb1-f8d9-4cb3-8096-03b8e5fe565b)
    검색 키워드에 포함된 글을 모두 보여준다.
    페이지 이동, 게시물 조회할 때 검색조건 값이 유지된다.

    **7. 게시글 추천**
    게시글 추천 기능은 독후감 게시판에 있습니다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/b114d9eb-34bf-466f-b8ba-68bb37d5b74b)
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/9a1f9652-b388-4525-a198-8b6630348ce2)
    게시글 조회화면에서 추천을 할 수 있고 추천된 상태에서 추천취소를 할 수 있다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/48889e8f-198d-45e6-94ac-3282bdd49217)
    전체 목록 화면에서 게시물은 추천 수만큼 좋아요 갯수로 표시된다.

    **8. 파일 업로드**
    파일 업로드 기능은 도서목록 게시판에 있으며 관리자만 작업 가능하다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/46b90aa8-cf13-4e10-9513-5e3fa7d72788)
    도서 등록 화면에서 파일 추가 및 삭제가 가능하다.(관리자 가능)
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/46a718ec-511c-4e3e-b871-edc2b790c759)
    도서 수정 화면에서 기존 파일 및 새 파일에 대한 추가, 삭제가 가능하다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/daa77c7e-26ed-4fbe-8035-0d8d9c203944)
    도서 조회 화면에서 파일을 다운로드 할 수 있다.(관리자, 회원 가능)
    
 </details>

 <details>
    <summary>회원 관련</summary>   
   
    **1. 회원가입**
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/74c3fd1a-cf76-44c9-bae8-09166a8923c6)
    이용약관, 개인정보 수집에 동의할 경우 회원가입을 진행할 수 있다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/4f265613-8dfa-482a-8361-7bbae322d204)
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/d87e8d59-4c63-4aa4-90d8-dc9dc4480e3c)
    ID중복확인, 이메일 인증, 비밀번호 유효성 검사를 진행하며 완료시 회원 정보를 저장하고
    메인 화면으로 redirect한다.

    **2. 로그인**
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/04b5c153-6e47-44ac-b8a8-c2c2269ebae1)
    자동로그인 클릭 시 7일 동안 로그인이 유지된다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/2552977e-a3fc-4cff-83b1-7d0797efdad6)
    로그인 실패 시 빨간 글씨로 안내문구가 출력된다.
    로그인 실패 유무와 상관없이 아이디 찾기/비밀번호 재발급 화면 이동은 언제든지 가능하다.
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/c054435c-8843-48e1-a5aa-7e3b20d361fb)
    로그인에 성공하면 로그인 직전에 봤던 페이지로 이동하며 회원가입일 경우 접근이 거부된다.

    **3. ID/PWD 찾기** 
    ![image](https://github.com/yoonclass/PrivateProject/assets/135006470/342792c8-9302-40be-bcdc-d7b1a532f268)
    회원가입 시 입력한 이메일로 아이디 및 임시 비밀번호 발급이 가능하다.
    
    **4. 프로필 수정** 
    
