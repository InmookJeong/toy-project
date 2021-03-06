# 회원 관리 프로젝트

> 버전 0.0.1
- 회원정보 정합성 체크 및 간단한 정규식 검증을 통한 회원 관리 화면 Prototype 개발
- 로그인 세션, 이메일 또는 휴대전화 번호 검증 등의 상세 기능은 개발 범위에서 제외
- 아이디, 비밀번호 등의 입력 규칙은 하드코딩을 통해 작성

## 요구사항 목록
|No|요구사항|상세 기능|비고|
|---|---|---|---|
|1|회원가입|||
|||1-1. 회원가입 페이지|비기능|
|||1-2. 개인정보 제공 동의 여부 확인|동의한 경우 다음 단계 진행|
|||1-3. ID 중복 확인||
|||1-4. 비밀번호 재입력 시 일치여부 확인||
|||1-5. 이메일 중복 확인||
|||1-6. 휴대전화 번호 중복 확인||
|||1-7. 입력한 사용자 정보가 입력 규칙에 맞을 경우 회원가입||
|||1-8. 입력한 사용자 정보가 입력 규칙에 맞지 않을 경우 안내 문구 제공(알림)||
|2|로그인|||
|||2-1. 로그인 페이지|비기능|
|||2-2. 가입된 ID인지 확인||
|||2-3. 입력한 ID와 비밀번호가 일치하는지 확인||
|||2-4. 입력한 ID와 비밀번호가 일치할 경우 로그인 된 Home 화면으로 이동||
|||2-5. 입력한 ID와 비밀번호가 일치하는지 확인||
|||2-6. 입력한 ID와 비밀번호가 일치할 경우 ||
|3|ID 찾기|||
|||3-1. ID 찾기 페이지|비기능|
|||3-2. 사용자 이름, 이메일을 통해 가입된 사용자인지 확인||
|||3-3. 입력한 사용자 이름과 이메일이 일치하는 경우 ID의 일부 출력||
|||3-4. 입력한 사용자 이름과 이메일이 일치하지 않을 경우 안내 문구 제공(알림)||
|4|비밀번호 찾기|||
|||4-1. 비밀번호 찾기 페이지|비기능|
|||4-2. 비밀번호 변경 페이지|비기능|
|||4-3. ID, 사용자 이름, 이메일을 통해 가입된 사용자인지 확인||
|||4-4. ID, 입력한 사용자 이름과 이메일이 일치하는 경우 비밀번호 변경 화면으로 이동||
|||4-5. ID, 입력한 사용자 이름과 이메일이 일치하지 않을 경우 안내 문구 제공(알림)||
|5|회원관리|||
|||5-1. 회원관리 페이지|비기능|
|||5-2. 테이블 형태로 회원 목록 출력|- 회원 가입일 기준 최신 순 정렬<br>- 검색, 페이지 기능 제외|

## 개발 환경
- IDE
    - Eclipse(2021-03)
- Framework
    - SpringFramework
- Server
    - Apache Tomcat 9.0.59
- Database
    - MySQL 8.0.28