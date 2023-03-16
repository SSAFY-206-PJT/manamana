import { useRouter } from 'next/router';
import Image from 'next/image';

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

function DetailPage() {
  // route
  const router = useRouter();
  const { webtoon_id } = router.query;
  console.log(webtoon_id);

  // 더미 웹툰 정보
  const dummyWebtoon = {
    name: '1초',
    authors: '시니/광운',
    plot: '구조율 100%의 전설적인 소방관.\n그의 특별한 능력은 긴장하는 순간, 미래가 보인다는 것!\n촌각을 다투는 진짜 소방관들의 이야기',
    genre: '드라마',
    day: '금요일',
    grade: '전체이용가',
    status: '연재중',
    webtoonURL: 'https://comic.naver.com/webtoon/list?titleId=725586',
    webtoonId: '1',
    startDate: new Date('2019-03-14'),
    totalEp: 123,
    colorHsl: '0,100,20',
    imagePath:
      'https://i.namu.wiki/i/1HFSZdDzi4R794o8YDRy6hqWPrwmDvjXM_DzQRoQG330IyvGufKbi-washsh1zjq2ixQeZV4CMheA57p_G3C5Idc5AobqXmiERS_HFLUDqqd3oye4WHQQCGgwejnVtBxx7zF0B0NQyzThORYiKCc-w.webp',
  };
  const WEBTOON_IMAGE_URL = dummyWebtoon.imagePath;
  const WEBTOON_COLOR = dummyWebtoon.colorHsl;

  // 그라데이션 스타일
  const hsls = WEBTOON_COLOR.split(',');
  const WEBTOON_THEME_COLOR = `hsl(${hsls[0]}, ${hsls[1]}%, 20%)`;
  const WEBTOON_GRADATION_COLOR = `linear-gradient(180deg, transparent, ${WEBTOON_THEME_COLOR} 600px)`;
  const coverStyle = {
    background: WEBTOON_GRADATION_COLOR,
  };

  // 태그로 만들 것들
  const tagList = [dummyWebtoon.genre, dummyWebtoon.day, dummyWebtoon.grade];
  const tagListDiv = tagList.map(tag => (
    <div className="mr-3 w-fit flex-initial rounded-3xl border border-white" key={tag}>
      <p className="mx-3 text-FontPrimaryDark">{tag}</p>
    </div>
  ));

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
        <div className="mx-3">
          <p className="text-xl text-FontPrimaryDark">{dummyWebtoon.name}</p>
          <p className="text-FontPrimaryDark">{dummyWebtoon.authors}</p>
          <p className="text-sm text-FontPrimaryDark">{dummyWebtoon.plot}</p>
          <div className="flex">{tagListDiv}</div>
          <p className="text-FontPrimaryDark">{dummyWebtoon.status}</p>
          <p className="text-FontPrimaryDark">{dummyWebtoon.totalEp}</p>
          <hr className="white h-px border-white bg-white" />
        </div>
        <div className="h-96">
          <hr className="white h-px border-white bg-white" />
        </div>
        <div className="h-96">
          <hr className="white h-px border-white bg-white" />
        </div>
        <div className="h-96">
          <hr className="white h-px border-white bg-white" />
        </div>
      </div>
    </div>
  );
}

export default DetailPage;
