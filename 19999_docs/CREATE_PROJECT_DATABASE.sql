-- 'mook_project'라는 이름의 DATABASE 생성.
CREATE DATABASE mook_project;

-- 'mook_project'라는 이름의 User 생성.
CREATE USER 'mook_project'@'localhost' IDENTIFIED BY 'mook0118_project';

CREATE USER 'mook_project'@'%' IDENTIFIED BY 'mook0118_project';

-- 'mook_project' User에게 'mook_project' Database에 대한 권한 부여.
-- mook_project.*는 study DB의 모든 권한을 부여하겠다는 의미.
-- FLUSH PRIVILEGES는 실행된 명령을 RDBMS에 적용하라는 의미.
GRANT ALL PRIVILEGES ON mook_project.* TO 'mook_project'@'localhost';
FLUSH PRIVILEGES;

-- Database의 인코딩을 UTF-8로 변경
-- 바로 아래 주석과 같이 DB 생성 시 처음부터 인코딩을 설정할 수 있음
-- CREATE DATABASE test DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER DATABASE mook_project CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- 아래는 Table의 인코딩을 UTF-8로 변경하는 명령어
-- root 권한으로 해도 되지만 study 권한으로 추후 필요하면 사용하기
-- ALTER TABLE <table_name> CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
