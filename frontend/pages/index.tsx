import Link from 'next/link';
import { useEffect } from 'react';

import Navbar from '../components/common/Navbar';
import WebtoonContainer from '../components/common/WebtoonContainer';
import WebtoonItem from '../components/common/WebtoonItem';

// 웹툰
interface Webtoon {
  name: string;
  authors: string;
  plot: string;
  genre: string;
  day: string;
  grade: string;
  status: string;
  webtoonURL: string;
  webtoonId: string;
  startDate: Date;
  totalEp: number;
  colorHsl: string;
  imagePath: string;
}

export default function Home() {
  // 스크롤 이동 함수
  const scrollToCoordinate = (x: number, y: number) => {
    window.scrollTo({
      top: y,
      left: x,
      behavior: 'smooth',
    });
  };

  useEffect(() => {
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

  // 더미 웹툰 정보
  // const dummyWebtoon = {
  //   name: '1초',
  //   authors: '시니/광운',
  //   plot: '구조율 100%의 전설적인 소방관.\n그의 특별한 능력은 긴장하는 순간, 미래가 보인다는 것!\n촌각을 다투는 진짜 소방관들의 이야기',
  //   genre: '드라마',
  //   day: '금요일',
  //   grade: '전체이용가',
  //   status: '연재중',
  //   webtoonURL: 'https://comic.naver.com/webtoon/list?titleId=725586',
  //   webtoonId: '1',
  //   startDate: new Date('2019-03-14'),
  //   totalEp: 123,
  //   colorHsl: '0,100,20',
  //   imagePath:
  //     'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
  // };
  let myWebtoonDummy = [
    {
      id: 1,
      name: '1조',
      imagePath:
        'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
      status: '휴재중',
    },
    {
      id: 2,
      name: '호랑이행님',
      imagePath:
        'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
      status: '휴재중',
    },
    {
      id: 3,
      name: '신의 탑',
      imagePath:
        'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
      status: '연재중',
    },
    {
      id: 4,
      name: '웹툰4',
      imagePath:
        'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
      status: '완결',
    },
    {
      id: 5,
      name: '웹툰5',
      imagePath:
        'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
      status: '연재중',
    },
    {
      id: 6,
      name: '웹툰6',
      imagePath:
        'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
      status: '연재중',
    },
  ];

  return (
    <div className="h-screen w-screen bg-BackgroundLight">
      {/* 최상위 헤더 */}
      <div className="sticky top-0 flex h-14 w-screen items-center justify-between bg-PrimaryLight px-5">
        <div className="h-6 w-6"></div>
        <img src="/images/MNMN_Logo_White.png" alt="Logo" className="h-10 w-10"></img>
        <Link href="/notification">
          <img src="/images/HeaderBar_Noti.png" alt="Noti"></img>
        </Link>
      </div>
      {/* TOP 10 */}
      <div className="flex h-14 w-screen items-center justify-center rounded-b-xl bg-PrimaryLight p-2">
        <div className="flex h-full items-center justify-between rounded-2xl bg-white px-6">
          <div className="flex items-center">
            <img src="/images/Main_Chatgrow.png" alt="top10" className="mx-2 h-4"></img>
            <p className="mx-2">TOP 10</p>
          </div>
          <div className="flex items-center">
            <p className="ml-4 text-PrimaryLight">1</p>
            <p className="mx-2">나 혼자만 레벨업</p>
          </div>
        </div>
      </div>

      {/* 내가 보는 웹툰 */}
      <div className="mb-3 flex justify-center">
        <div className="w-11/12 rounded-lg bg-BackgroundLightComponent px-4 pt-4">
          <WebtoonContainer categoryTitle={'내가 보는 웹툰'} rightBtn={true} />
          <div className="mb-4 overflow-x-auto  whitespace-nowrap">
            {myWebtoonDummy.map(webtoon => (
              <WebtoonItem
                key={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </div>
        </div>
      </div>

      {/* 마나마나가 준비했어요 */}
      <div className="mb-3 flex justify-center">
        <div className="w-11/12 rounded-lg bg-BackgroundLightComponent px-4 pt-4">
          <WebtoonContainer categoryTitle={'마나마나가 준비했어요'} />
          <div className="mb-4 overflow-x-auto whitespace-nowrap">
            {myWebtoonDummy.map(webtoon => (
              <WebtoonItem
                key={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </div>
        </div>
      </div>

      {/* 이 달의 신작 */}
      <div className="mb-3 flex justify-center">
        <div className="w-11/12 rounded-lg bg-BackgroundLightComponent px-4 pt-4">
          <WebtoonContainer categoryTitle={'이 달의 신작'} />
          <div className="mb-4 overflow-x-auto  whitespace-nowrap">
            {myWebtoonDummy.map(webtoon => (
              <WebtoonItem
                key={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </div>
        </div>
      </div>
      <Navbar />
    </div>
  );
}
