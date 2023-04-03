import Link from 'next/link';
import Image from 'next/image';
import { useEffect, useRef, useState } from 'react';
import styled from '@emotion/styled';
import Navbar from '../components/common/Navbar';
import WebtoonContainer from '../components/common/WebtoonContainer';
import WebtoonItem from '../components/common/WebtoonItem';
import Top10 from '../components/common/Top10';
import axios from 'axios';
import * as api from '@/pages/api/detail';
import { getCookie } from '@/util/cookie';
import { useRouter } from 'next/router';

interface Props {
  home: any;
}

function Home({ home }: Props) {
  const router = useRouter();
  const token = getCookie('accessToken');
  //// api
  // 관심웹툰
  const defaultLikeWebtoon = [
    {
      id: 0,
      name: '등록해보세요',
      imagePath: '/icon-192x192.png',
      status: '연재중',
    },
  ];
  const [likeWebtoons, setLikeWebtoons] = useState<Array<any>>(defaultLikeWebtoon);
  const getUserLike = async () => {
    const res = await api.getUserLike(token);
    console.log(res.result);
    if (res.result && res.result.length > 0) {
      setLikeWebtoons(res.result);
    }
  };

  // 스크롤 이동 함수
  const scrollToCoordinate = (x: number, y: number) => {
    window.scrollTo({
      top: y,
      left: x,
      behavior: 'smooth',
    });
  };

  // home화면 초기화
  useEffect(() => {
    // api 요청
    getUserLike();

    let prevScrollPosition = 0;
    const handleScroll = () => {
      // 현재 스크롤 위치를 인식
      const { scrollTop } = document.documentElement;
      // 현재 스크롤 위치와 이동한 스크롤 위치를 비교해 스크롤 방향을 판단
      const scrollDirection = scrollTop > prevScrollPosition ? 'down' : 'up';
      prevScrollPosition = scrollTop;

      // 스크롤이 내려가다가 20~56에서 멈추는 경우, 56으로 이동
      if (scrollDirection === 'down' && scrollTop > 20 && scrollTop < 56) {
        scrollToCoordinate(0, 56);
      } // 스크롤이 올라가는 경우
      else if (scrollDirection === 'up' && scrollTop === 56) {
        scrollToCoordinate(0, 0);
      }
    };
    // 스크롤 이벤트가 발생했을 때, handleScroll 함수를 실행
    window.addEventListener('scroll', handleScroll);
    return () => {
      // 언마운트될 때 이벤트 리스너를 제거하는 함수를 실행
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  // 웹툰 더미데이터
  let myWebtoonDummy = [
    {
      id: 1,
      name: '1조',
      imagePath: '/images/Temp_Webtoon_Thumnail.jpg',
      status: '휴재중',
    },
    {
      id: 2,
      name: '호랑이행님',
      imagePath: '/images/Temp_Webtoon_Thumnail.jpg',
      status: '휴재중',
    },
    {
      id: 3,
      name: '신의 탑',
      imagePath: '/images/Temp_Webtoon_Thumnail.jpg',
      status: '연재중',
    },
    {
      id: 4,
      name: '웹툰4',
      imagePath: '/images/Temp_Webtoon_Thumnail.jpg',
      status: '완결',
    },
    {
      id: 5,
      name: '웹툰5',
      imagePath: '/images/Temp_Webtoon_Thumnail.jpg',
      status: '연재중',
    },
    {
      id: 6,
      name: '웹툰6',
      imagePath: '/images/Temp_Webtoon_Thumnail.jpg',
      status: '연재중',
    },
  ];

  // 웹툰 취향 가는 컴포넌트 스타일
  const BannerStyle1 = styled.div`
    background: linear-gradient(120deg, #f9dc5c 0%, #f9dc5c 50%, #f4d03f 50%, #f4d03f 100%);
    color: white;
    text-align: center;
    transform: perspective(1000px) rotateX(10deg);
    box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.15), 0px 5px 5px rgba(0, 0, 0, 0.1);
    transform: perspective(1000px) rotateX(10deg);
  `;
  // 마나마나 가는 컴포넌트 스타일
  const BannerStyle2 = styled.div`
    background: linear-gradient(120deg, #92c83e 0%, #92c83e 50%, #6ebe44 50%, #6ebe44 100%);
    color: white;
    text-align: center;
    transform: perspective(1000px) rotateX(10deg);
    box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.15), 0px 5px 5px rgba(0, 0, 0, 0.1);
    transform: perspective(1000px) rotateX(10deg);
  `;

  // 웹툰 아이템을 감싸는 컨테이너의 스크롤바 숨기기
  const WebtoonItemContainer = styled.div`
    margin-bottom: 16px;
    overflow-x: auto;
    overflow-y: clip;
    white-space: nowrap;
    scrollbar-width: none;
    -ms-overflow-style: none; /* IE 및 Edge 용 스크롤바 숨김 */

    /* 웹킷 엔진 기반 브라우저의 스크롤바 숨김 */
    &::-webkit-scrollbar {
      display: none;
    }
  `;

  return (
    <div className=" h-full w-full bg-BackgroundLight pb-12">
      {/* 최상위 헤더 */}
      <div className="sticky top-0 z-10 flex h-14 w-screen items-center justify-between bg-PrimaryLight px-5">
        <div className="h-6 w-6"></div>
        <img src="/images/MNMN_Logo_White.png" alt="Logo" className="h-10 w-10"></img>
        <Link href="/notification">
          <img src="/images/HeaderBar_Noti.png" alt="Noti"></img>
        </Link>
      </div>
      {/* TOP 10 */}
      <div className=" z-0 flex h-14 w-full items-center justify-center rounded-b-xl bg-PrimaryLight p-2">
        <div className="flex h-full w-11/12 items-center justify-between rounded-2xl bg-white px-6">
          <Top10 />
        </div>
      </div>
      <div className="mb-3 flex justify-center">
        <div className="w-11/12 rounded-lg bg-BackgroundLightComponent px-4 pt-4">
          <WebtoonContainer categoryTitle={'내가 보는 웹툰'} route={'my-webtoon'} />
          <WebtoonItemContainer>
            {likeWebtoons.map((webtoon: any) => (
              <WebtoonItem
                key={webtoon.id}
                id={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </WebtoonItemContainer>
        </div>
      </div>
      <div className="mb-4 flex justify-center">
        <div className="grid h-32 w-11/12 grid-cols-3 gap-2">
          <BannerStyle1 className="col-span-1 rounded-lg bg-white px-4">
            <div className="flex h-full w-full items-center justify-center">
              <Link href="/genre-taste">
                <div className="p-2 font-bold">
                  선호취향
                  <br />
                  설정하기
                </div>
              </Link>

              <div className="absolute bottom-1 right-2">
                <Link href="/genre-taste">
                  <Image
                    src={'/images/goOtherPageWhite.png'}
                    alt="선호취향 이동"
                    width={24}
                    height={24}
                  />
                </Link>
              </div>
            </div>
          </BannerStyle1>
          <BannerStyle2 className="relative col-span-2 rounded-lg bg-[#0B99FF] px-4">
            <div className="z-10 flex flex-col justify-between">
              <div className="z-10 flex items-center justify-center pt-4">
                <img className="z-10 h-24 w-24 p-2" src="/images/character.png" alt="hi"></img>
                <Link href="/managola">
                  <div className="flex items-center p-2 font-bold">
                    취향검사<br></br>하러가기
                  </div>
                </Link>
              </div>
              <div className="absolute bottom-1 right-2">
                <Link href="/managola">
                  <Image
                    src={'/images/goOtherPageWhite.png'}
                    alt="마나골라 이동"
                    width={24}
                    height={24}
                  />
                </Link>
              </div>
            </div>
          </BannerStyle2>
        </div>
      </div>
      <div className="mb-3 flex justify-center">
        <div className="w-11/12 rounded-lg bg-BackgroundLightComponent px-4 pt-4">
          <WebtoonContainer categoryTitle={'마나마나가 준비했어요'} />
          <WebtoonItemContainer>
            {myWebtoonDummy.map(webtoon => (
              <WebtoonItem
                key={webtoon.id}
                id={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </WebtoonItemContainer>
        </div>
      </div>
      <div className="mb-3 flex justify-center">
        <div className="w-11/12 rounded-lg bg-BackgroundLightComponent px-4 pt-4">
          <WebtoonContainer categoryTitle={'이 달의 신작'} />
          <WebtoonItemContainer>
            {myWebtoonDummy.map(webtoon => (
              <WebtoonItem
                key={webtoon.id}
                id={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </WebtoonItemContainer>
        </div>
      </div>
      <Navbar />
    </div>
  );
}

export default Home;

export async function getServerSideProps(context: any) {
  const token = context.req.cookies.accessToken;
  if (!token) {
    return {
      redirect: {
        destination: '/login',
        permanent: false,
      },
    };
  }

  return { props: { home: null } };
}
