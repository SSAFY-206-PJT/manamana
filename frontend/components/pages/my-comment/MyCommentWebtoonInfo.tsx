import Image from 'next/image';

const dummyWebtoon = {
  id: 1,
  name: '1초',
  imagePath:
    'https://i.namu.wiki/i/0FPGuCn5XVDyejAOiSHqb_45uo-E4kwWkZQzS6YMYEwv4hHTPBNqTxD311G9nRYF9hsSkGh1IKVHsXcGUlXd_a-gEbRGbc0-3rWFQVian9aGOfj0NDrX4-qV5mRkMrEktPSaCH6_FjuIDatrhZnnGQ.webp',
  plot: '구조율 100%의 전설적인 소방관.\n그의 특별한 능력은 긴장하는 순간, 미래가 보인다는 것!\n촌각을 다투는 진짜 소방관들의 이야기구조율 100%의 전설적인 소방관.\n그의 특별한 능력은 긴장하는 순간, 미래가 보인다는 것!\n촌각을 다투는 진짜 소방관들의 이야기구조율 100%의 전설적인 소방관.\n그의 특별한 능력은 긴장하는 순간, 미래가 보인다는 것!\n촌각을 다투는 진짜 소방관들의 이야기구조율 100%의 전설적인 소방관.\n그의 특별한 능력은 긴장하는 순간, 미래가 보인다는 것!\n촌각을 다투는 진짜 소방관들의 이야기',
  grade: '전체이용가',
  status: '연재중',
  webtoonUrl: 'https://m.comic.naver.com/webtoon/list?titleId=725586',
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
    scoreCount: 12,
    scoreAverage: 4.5,
  },
};

interface webtoonInfoProps {
  webtoonId: number;
}

function MyommentWebtoonInfo({ webtoonId }: webtoonInfoProps) {
  const day = [
    '',
    '월요일 웹툰',
    '화요일 웹툰',
    '수요일 웹툰',
    '목요일 웹툰',
    '금요일 웹툰',
    '토요일 웹툰',
    '일요일 웹툰',
  ];
  const webtoon = dummyWebtoon;

  return (
    <div className="mb-1 flex items-center">
      <Image
        className="mr-1 h-12 w-1/3 rounded object-cover"
        src={webtoon.imagePath}
        alt="웹툰 이미지"
        width={300}
        height={500}
        priority
      />
      <div>
        <div className="text-lg">{webtoon.name}</div>
        <div className="text-sm">
          {webtoon.genres[0].name}|{day[webtoon.days[0].codeId]}|{webtoon.status}
        </div>
      </div>
    </div>
  );
}

export default MyommentWebtoonInfo;
