-- file 생성
create table file_ (
    fileId BIGINT(10) PRIMARY KEY,
    name longtext not null comment '이름',
    isFile tinyint(4) default true not null comment '파일/폴더 여부',
    fileSize bigint(10) default 0 not null comment '파일 사이즈',
    extension varchar(100) comment '확장자',
    mimeType varchar(100) comment 'MIME 타입',
    path longtext not null comment '파일/폴더 경로',
    parentPath longtext not null comment '상위 폴더 경로',
    parentFileId BIGINT(10) default 0 not null comment '상위 폴더 ID',
	userId longtext not null comment '사용자 ID',
    createDate datetime default now() comment '파일 생성(업로드) 일자',
    modifiedDate datetime comment '파일 수정 일자',
    readCount bigint(10) default 0 not null comment '조회수',
    repositoryType varchar(100) comment '저장소 타입(ROOT, SYSTEM, USER, etc)',
    status varchar(75) not null comment '상태 코드'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='파일 관리 프로젝트의 파일 테이블';
-- DB ENGINE : InnoDB
-- DB 엔진의 종류와 그 차이점에 대해 학습하자

ALTER TABLE file_ MODIFY fileId BIGINT(10) comment '숫자(시퀀스) 형식의 아이디';

commit;