use mana_db;

-- 정렬 조건 테이블
insert into sorts(id,name) value(1,"조회 순");
insert into sorts(id,name) value(2,"별점 높은 순");
insert into sorts(id,name) value(3,"댓글 많은 순");

-- 연재 연부 테이블
insert into serial_status(id,status) value(1,"연재중");
insert into serial_status(id,status) value(2,"완결");
insert into serial_status(id,status) value(3,"휴재");

-- 연령 등급 테이블
insert into grades(id,grade) value(1,"전체이용가");
insert into grades(id,grade) value(2,"성인");

-- 연재 요일 테이블
insert into day_codes(id,day) value(1,"월");
insert into day_codes(id,day) value(2,"화");
insert into day_codes(id,day) value(3,"수");
insert into day_codes(id,day) value(4,"목");
insert into day_codes(id,day) value(5,"금");
insert into day_codes(id,day) value(6,"토");
insert into day_codes(id,day) value(7,"일");
insert into day_codes(id,day) value(8,"기타");

-- 장르 테이블
insert into genres(id,name) values(1,"액션");
insert into genres(id,name) values(2,"판타지");
insert into genres(id,name) values(3,"학원");
insert into genres(id,name) values(4,"스릴러");
insert into genres(id,name) values(5,"개그");
insert into genres(id,name) values(6,"무협");
insert into genres(id,name) values(7,"공포");
insert into genres(id,name) values(8,"드라마");
insert into genres(id,name) values(9,"로맨스");
insert into genres(id,name) values(10,"옴니버스");
insert into genres(id,name) values(11,"일상");
insert into genres(id,name) values(12,"BL");
insert into genres(id,name) values(13,"GL");
insert into genres(id,name) values(14,"SF");
insert into genres(id,name) values(15,"스포츠");
insert into genres(id,name) values(16,"시대극");

-- 웹툰 제공자
insert into webtoon_providers(id,name,provider_url,provider_image) values(1,"네이버 웹툰","https://comic.naver.com","https://manamana-bucket.s3.ap-northeast-2.amazonaws.com/webtoon_provider_image/naver_webtoon.png");
insert into webtoon_providers(id,name,provider_url,provider_image) values(2,"카카오 웹툰","https://webtoon.kakao.com","https://manamana-bucket.s3.ap-northeast-2.amazonaws.com/webtoon_provider_image/kakao_webtoon.jpg");
insert into webtoon_providers(id,name,provider_url,provider_image) values(3,"카카오 페이지","https://page.kakao.com","https://manamana-bucket.s3.ap-northeast-2.amazonaws.com/webtoon_provider_image/kakao_page.png");

-- 로그인 제공자
insert into login_providers(id,name) values(1,"kakao");
