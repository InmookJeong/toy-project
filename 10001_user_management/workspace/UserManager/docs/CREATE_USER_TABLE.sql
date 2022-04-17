-- mvc_board Table 생성
create table User_ (
    id BIGINT(10) PRIMARY KEY,
    userId VARCHAR(75) unique not null comment '사용자 아이디',
    password varchar(1000) not null comment '비밀번호',
    isTempPw tinyint(4) default 0 not null comment '임시 비밀번호 여부',
    name varchar(1000) not null comment '이름',
    phoneNumber varchar(100) unique not null comment '전화번호',
    email varchar(750) unique not null comment '이메일',
    birth varchar(75) not null comment '생년월일',
    gender varchar(75) not null comment '성별',
    address longtext not null comment '주소',
    nation varchar(75) comment '국가 코드',
    description longtext comment '설명',
    status varchar(75) not null comment '상태 코드',
    profileImageId bigint(10) default 0 not null comment '프로필 이미지 아이디',
    createDate datetime default now() comment '생성(가입) 일자',
    modifiedDate datetime comment '수정(회원정보 수정) 일자',
    lastLoginDate datetime comment '마지막 로그인 날짜',
    lastLoginIp varchar(100) comment '마지막 로그인 IP',
    failedLoginAttemts bigint(10) default 0 comment '로그인 실패 횟수',
    lockout tinyint(4) default 0 comment '잠긴 상태',
    lockoutDate datetime comment '잠긴 날짜',
    emailVerified tinyint(4) default 0 comment '이메일 검증 여부',
    siteTermsOfUse tinyint(4) default 0 comment '사이트 이용 약관',
    userInfoTermsOfUse tinyint(4) default 0 comment '개인정보 제공 약관',
    eventTermsOfUse tinyint(4) default 0 comment '이벤트 약관 동의'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='회원 관리 프로젝트의 회원 테이블';
-- DB ENGINE : InnoDB
-- DB 엔진의 종류와 그 차이점에 대해 학습하자
-- Unique Index의 최대 길이는 3072
-- utf8은 3byte를 기준, utf8mb4는 4byte를 기준으로 
-- email 컬럼의 경우 unique-index를 생성할 때 utf8면 varchar(1000)이 가능하지만 utf8mb4는 길이가 초과하므로 varchar(750)으로 변경

ALTER TABLE User_ MODIFY id BIGINT(10) comment '숫자(시퀀스) 형식의 아이디';

-- ALTER TABLE User_ ALTER COLUMN failedLoginAttemts SET default 0;

commit;