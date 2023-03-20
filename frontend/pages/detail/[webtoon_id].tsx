import { useState } from 'react';
import { useRouter } from 'next/router';
import Image from 'next/image';

// 응답 result
interface IdName {
  id: number;
  name: string;
}

interface Day {
  id: number;
  codeId: number;
}

interface Webtoon {
  id: number;
  name: string;
  imagePath: string;
  plot: string;
  grade: string;
  status: string;
  webtoonUrl: string;
  webtoonId: number;
  startDate: Date;
  totalEpisode: number;
  colorHsl: string;
  authors: Array<IdName>;
  genres: Array<IdName>;
  days: Array<Day>;
  additions: {
    id: number;
    view: number;
    scoreCount: number;
    scoreAverage: number;
  };
}

// 더미 웹툰 정보
const dummyWebtoon: Webtoon = {
  id: 1,
  name: '1초',
  imagePath:
    'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
  plot: '구조율 100%의 전설적인 소방관.\n그의 특별한 능력은 긴장하는 순간, 미래가 보인다는 것!\n촌각을 다투는 진짜 소방관들의 이야기',
  grade: '전체이용가',
  status: '연재중',
  webtoonUrl: 'https://comic.naver.com/webtoon/list?titleId=725586',
  webtoonId: 123,
  startDate: new Date('2019-03-14'),
  totalEpisode: 123,
  colorHsl: '0,100,20',
  authors: [
    { id: 1, name: '시니' },
    { id: 2, name: '광운' },
  ],
  genres: [{ id: 1, name: '드라마' }],
  days: [{ id: 1, codeId: 5 }],
  additions: {
    id: 1,
    view: 123123,
    scoreCount: 321321,
    scoreAverage: 4.5,
  },
};

function DetailPage() {
  // route
  const router = useRouter();
  const { webtoon_id } = router.query;
  console.log(webtoon_id);

  const WEBTOON_IMAGE_URL = dummyWebtoon.imagePath;
  const WEBTOON_COLOR = dummyWebtoon.colorHsl;

  // 그라데이션 스타일
  const hsls = WEBTOON_COLOR.split(',');
  const WEBTOON_THEME_COLOR = `hsl(${hsls[0]}, ${hsls[1]}%, 20%)`;
  const WEBTOON_GRADATION_COLOR = `linear-gradient(180deg, transparent, ${WEBTOON_THEME_COLOR} 600px)`;
  const coverStyle = { background: WEBTOON_GRADATION_COLOR };

  // 태그로 만들 것들
  const tagList = [dummyWebtoon.genres[0].name, dummyWebtoon.days[0].codeId, dummyWebtoon.grade];
  const tagListDiv = (
    <div className="my-2 flex">
      {tagList.map(tag => (
        <div className="mr-3 w-fit flex-initial rounded-3xl border border-white" key={tag}>
          <p className="mx-3 my-0.5 text-xs text-FontPrimaryDark">#{tag}</p>
        </div>
      ))}
    </div>
  );

  // 줄거리
  const [morePlot, setMoerPlot] = useState<Boolean>(false);
  const handleMoreBtn = () => {
    setMoerPlot(true);
  };
  const plotDiv = (
    <div>
      <p
        className={
          morePlot
            ? 'text-sm font-bold text-FontPrimaryDark'
            : 'line-clamp-2 text-sm font-bold text-FontPrimaryDark'
        }
      >
        {dummyWebtoon.plot}
      </p>
      <div className="flex">
        <button
          className={
            morePlot
              ? 'hidden'
              : 'mx-auto my-0.5 text-xs font-semibold text-FontPrimaryDark opacity-80'
          }
          onClick={handleMoreBtn}
        >
          자세히보기
        </button>
      </div>
    </div>
  );

  // 연재정보
  const infoList = [
    {
      name: '연재시작일',
      content: dummyWebtoon.startDate.toISOString().split('T')[0],
    },
    {
      name: '연재정보',
      content: dummyWebtoon.status,
    },
    {
      name: '조회수',
      content: dummyWebtoon.additions.view,
    },
    {
      name: '평가수',
      content: dummyWebtoon.additions.scoreCount,
    },
  ];
  const infoDiv = (
    <div className="grid w-fit grid-cols-2 content-center">
      <div>
        {infoList.map(info => (
          <p className="text-sm font-semibold text-FontPrimaryDark opacity-80" key={info.name}>
            {info.name}
          </p>
        ))}
      </div>
      <div>
        {infoList.map(info => (
          <p className="mr-1 text-sm text-FontPrimaryDark" key={info.content}>
            {info.content}
          </p>
        ))}
      </div>
    </div>
  );

  // 웹툰 정보
  const webtoonInfoDiv = (
    <div>
      <p className="text-2xl font-bold text-FontPrimaryDark">{dummyWebtoon.name}</p>
      <p className="text-FontPrimaryDark">
        {dummyWebtoon.authors[0].name}
        {dummyWebtoon.authors[1] ? `/${dummyWebtoon.authors[1].name}` : ''}
      </p>
      {plotDiv}
      {tagListDiv}
      <div className="flex w-auto rounded-3xl border-2 border-white">
        <p className="mx-auto my-0.5 text-xl font-bold text-FontPrimaryDark">감상하러 가기</p>
      </div>
    </div>
  );

  // 평점
  const scoreDiv = (
    <div>
      <div className="flex justify-center">
        <p className="text-FontPrimaryDark">평점</p>
      </div>
      <div className="flex justify-center">
        <p className="text-FontPrimaryDark">{dummyWebtoon.additions.scoreAverage}</p>
      </div>
      <div className="flex justify-center">
        <p className="text-sm text-FontPrimaryDark opacity-80">평가하기</p>
      </div>
    </div>
  );

  return (
    <div>
      <Image
        className="absolute h-auto w-full"
        src={WEBTOON_IMAGE_URL}
        alt="웹툰 이미지"
        width={300}
        height={500}
        priority
      />
      <div style={coverStyle} className="absolute h-auto w-full">
        <div className="text-FontPrimaryDark">(뒤로가기버튼) 상단컴포넌트 (관심웹툰버튼)</div>
        <div className="h-96"></div>
        <div className="mx-3">{webtoonInfoDiv}</div>
        <hr className="white my-6 border-white bg-white" />
        <div className="grid grid-cols-2">
          <div className="flex justify-center">{infoDiv}</div>
          <div className="flex justify-center">{scoreDiv}</div>
        </div>
        <hr className="white my-6 border-white bg-white" />
        <div className="h-96"></div>
        <hr className="white my-6 border-white bg-white" />
        <div className="h-96"></div>
      </div>
    </div>
  );
}

export default DetailPage;
