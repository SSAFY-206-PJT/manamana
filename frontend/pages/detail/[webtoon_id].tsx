import { useState } from 'react';
import { useRouter } from 'next/router';
import Image from 'next/image';
import { Rating } from '@mui/material';
import Modal from '@mui/material/Modal';
import SwipeableDrawer from '@mui/material/SwipeableDrawer';

import ConfirmBtn from '@/components/confirmBtn';
import CommentIcon from '@/public/images/Comment_List.svg';
import Heart from '@/public/images/Heart.svg';

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

function DetailPage() {
  // route
  const router = useRouter();
  const { webtoon_id } = router.query;

  const WEBTOON_IMAGE_URL = dummyWebtoon.imagePath;
  const WEBTOON_COLOR = dummyWebtoon.colorHsl;

  // 그라데이션 스타일
  const hsls = WEBTOON_COLOR.split(',');
  const WEBTOON_THEME_COLOR = `hsl(${hsls[0]}, ${hsls[1]}%, 20%)`;
  const WEBTOON_GRADATION_COLOR = `linear-gradient(180deg, transparent, ${WEBTOON_THEME_COLOR} 600px)`;
  const coverStyle = { background: WEBTOON_GRADATION_COLOR };

  // 좋아요
  const [likeWebtoon, setLikeWebtoon] = useState<boolean>(false);
  const likeInput = () => {
    // api 요청 후
    setLikeWebtoon(!likeWebtoon);
  };

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
            : 'text-sm font-bold text-FontPrimaryDark line-clamp-2'
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

  // 웹툰 정보
  const [openModal, setOpenModal] = useState<boolean>(false);

  const webtoonInfoDiv = (
    <div>
      <p className="text-2xl font-bold text-FontPrimaryDark">{dummyWebtoon.name}</p>
      <p className="text-FontPrimaryDark">
        {dummyWebtoon.authors[0].name}
        {dummyWebtoon.authors[1] ? `/${dummyWebtoon.authors[1].name}` : ''}
      </p>
      {plotDiv}
      {tagListDiv}
      <div
        className="flex w-auto rounded-3xl border-2 border-white"
        onClick={() => {
          setOpenModal(true);
        }}
      >
        <p className="mx-auto my-0.5 text-xl font-bold text-FontPrimaryDark">감상하러 가기</p>
      </div>
      <Modal
        open={openModal}
        onClose={() => {
          setOpenModal(false);
        }}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <div className="absolute bottom-1/2 right-1/2 translate-x-1/2 translate-y-1/2 rounded bg-white p-4">
          <p className="whitespace-nowrap">해당 웹플랫폼으로 이동 합니다</p>
          <a href={dummyWebtoon.webtoonUrl} target="_blank">
            <button>웹툰 이미지</button>
          </a>
        </div>
      </Modal>
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

  // 평점
  const [ratingModal, setRatingModal] = useState<any>(false);
  const [afterRating, setAfterRating] = useState<boolean>(false);
  const [ratingInput, setRatingInput] = useState<number | null>(0);

  const openRatingModal = () => {
    setRatingModal(true);
  };
  const closeModal = () => {
    setRatingModal(false);
    setAfterRating(false);
    setRatingInput(0);
  };
  const postRating = () => {
    console.log(ratingInput, '점을 제출');
    setAfterRating(true);
  };
  const goComment = () => {
    router.push({
      pathname: `/detail/comment/${webtoon_id}`,
      query: {
        WEBTOON_GRADATION_COLOR,
      },
    });
  };

  // 기존 평점
  const scoreDiv = (
    <div>
      <div className="flex justify-center">
        <p className="text-FontPrimaryDark">평점</p>
      </div>
      <div className="flex justify-center">
        <p className="text-FontPrimaryDark">{dummyWebtoon.additions.scoreAverage}</p>
      </div>
      <div>
        <Rating defaultValue={dummyWebtoon.additions.scoreAverage} precision={0.5} readOnly />
      </div>
      <div className="flex justify-center" onClick={openRatingModal}>
        <p className="text-sm text-FontPrimaryDark opacity-80">평가하기</p>
      </div>
    </div>
  );

  // 평가하기
  const ratingInputDiv = (
    <div className="m-3 flex flex-col">
      <div className="h-2"></div>
      <div className="flex items-end justify-start">
        <p className="mr-1 text-lg">웹툰 평가</p>
        <p className="mr-1">{dummyWebtoon.additions.scoreCount}명 참여</p>
      </div>
      <p className="text-center">별점</p>
      <div className="flex justify-center">
        <Rating
          value={ratingInput}
          defaultValue={dummyWebtoon.additions.scoreAverage}
          onChange={(event, newVal) => {
            if (newVal === null) {
              setRatingInput(0);
            } else {
              setRatingInput(newVal);
            }
          }}
          precision={0.5}
          size="large"
          sx={{
            fontSize: '4rem',
          }}
        />
      </div>
      <p className="text-center">{ratingInput}점</p>
      <ConfirmBtn cancel={closeModal} confirm={postRating} />
    </div>
  );
  // 평가이후 댓글이동 확인
  const commentCheckDiv = (
    <div className="m-3 flex flex-col">
      <div className="h-2"></div>
      <div className="flex items-end justify-start">
        <p className="mr-1 text-lg">웹툰 평가</p>
        <p className="mr-1">7명 참여</p>
      </div>
      <p className="text-center">별점</p>
      <div className="grid h-16 grid-cols-1 items-center">
        <p className="text-center">{ratingInput}점이 기록되었습니다!</p>
        <p className="text-center">댓글도 남기시겠어요?</p>
      </div>
      <ConfirmBtn cancel={closeModal} confirm={goComment} />
    </div>
  );

  const popupDiv = (
    <SwipeableDrawer
      anchor={'bottom'}
      open={ratingModal}
      onOpen={openRatingModal}
      onClose={closeModal}
    >
      {afterRating ? commentCheckDiv : ratingInputDiv}
    </SwipeableDrawer>
  );

  // 워드 클라우드, 댓글
  const wordCloudDiv = (
    <div>
      <p className="text-2xl font-bold text-FontPrimaryDark">
        {dummyWebtoon.name}의 리뷰를 모아봤어요
      </p>
      <div className="my-2 h-48 rounded-lg border border-solid border-white bg-CommentBackground"></div>
      <div className="flex h-12 justify-around rounded-lg border border-solid border-white bg-CommentBackground">
        <div className="flex w-1/2 items-center justify-center">
          <div className="flex h-full w-1/2 items-center justify-center">
            <CommentIcon height="100%" fill="white" stroke="white" />
            <p className="whitespace-nowrap text-FontPrimaryDark">리뷰 몇개</p>
          </div>
        </div>
        <button className="flex w-1/2 items-center justify-center" onClick={goComment}>
          <p className="text-FontPrimaryDark">리뷰 보기</p>
        </button>
      </div>
    </div>
  );

  // 유사웹툰 목록
  const elseWebtoons = (
    <div>
      <p className="text-2xl font-bold text-FontPrimaryDark">이런 웹툰은 어때요?</p>
      <div className="h-12"></div>
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
        <div className="m-3 flex h-12 justify-between">
          <img src="/images/HeaderBar_Back.png" alt="goBack" onClick={() => router.back()}></img>
          <button className=" h-full" onClick={likeInput}>
            {likeWebtoon ? (
              <Heart width="100%" height="100%" fill="red" stroke="red" />
            ) : (
              <Heart width="100%" height="100%" fillOpacity="0" stroke="white" />
            )}
          </button>
        </div>
        <div className="h-96"></div>
        <div className="mx-3">{webtoonInfoDiv}</div>
        <hr className="white my-6 border-white bg-white" />
        <div className="grid grid-cols-2">
          <div className="flex justify-center">{infoDiv}</div>
          <div className="flex justify-center">{scoreDiv}</div>
        </div>
        <hr className="white my-6 border-white bg-white" />
        <div className="mx-3 h-fit">{wordCloudDiv}</div>
        <hr className="white my-6 border-white bg-white" />
        <div className="mx-3 h-fit">{elseWebtoons}</div>
      </div>
      {popupDiv}
    </div>
  );
}

export default DetailPage;
