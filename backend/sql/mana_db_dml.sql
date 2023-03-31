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

select * from users;
-- 테스트용 유저
insert into users(email,nickname,image_path,gender,age,is_deleted,create_time,update_time,provider_id) 
value("ssafy80165","ssafy","https://manamana-bucket.s3.ap-northeast-2.amazonaws.com/webtoon_provider_image/kakao_page.png","남","27",false,now(),now(),1);


select count(*) from users where id = 2;

        
insert into webtoons(name,image_path,plot,grade_id,status_id,webtoon_url,webtoon_id,start_date,total_ep,is_deleted,create_time,update_time,color_hsl,provider_id)
values("뷰티풀 군바리","https://image-comic.pstatic.net/webtoon/648419/thumbnail/thumbnail_IMAG21_d9398229-cbfd-47dc-9208-0a6fb936f3a7.jpg","'여자도 군대에 간다면?'본격 여자도 군대 가는 만화!",1,1,"/webtoon/list?titleId=648419","648419","15.02.15",380,false,now(),now(),"40,5,22",1);

insert into webtoon_days(code_id,webtoon_id) values(1,1);
insert into webtoon_days(code_id,webtoon_id) values(4,1);
insert into authors(name, webtoon_id) values("윤성원",1);
insert into authors(name, webtoon_id) values("설이",1);

select * from webtoon_days;

update users set is_deleted = false where id = 1;

select * from webtoons;
delete from webtoons where id = 2;

insert into comments(content,is_spoiler,report,is_deleted,create_time,update_time,webtoon_id,user_id) 
values("댓글임둥",false,5,false,now(),now(),1,1);

select * from users_and_webtoons;

insert into users_and_webtoons(user_id,webtoon_id,score,is_liked,is_deleted,create_time,update_time) 
values(1,1,3,true,false,now(),now());

update users_and_webtoons set is_liked = true where user_id =1;

select * from users_and_genres;

select * from webtoons_and_genres;

insert into webtoons_and_genres(webtoon_id,genre_id) value(1,1);
insert into webtoons_and_genres(webtoon_id,genre_id) value(1,4);

select * from webtoon_days;

select * from sorts;
select * from webtoon_additions;
insert into webtoon_additions(view,total_score,score_count,create_time,update_time,webtoon_id) 
values(10,20,4,now(),now(),1);

select * from login_providers;
select * from prefer_genres;

insert into prefer_genres(genre_id,is_canceled, user_id,create_time, update_time) values(1,false,2,now(),now());
insert into prefer_genres(genre_id,is_canceled, user_id,create_time, update_time) values(3,false,2,now(),now());

update prefer_genres set is_canceled = true where id = 2;
insert into prefer_genres(genre_id,is_canceled, user_id,create_time, update_time) values(3,false,1,now(),now());

select * from users_and_genres;

insert into users_and_genres(weight, create_time, update_time,user_id, genre_id) values(5,now(),now(),1,4);
