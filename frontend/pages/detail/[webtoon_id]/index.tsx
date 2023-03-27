import axios from 'axios';
import { useEffect, useState } from 'react';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { Rating } from '@mui/material';
import Modal from '@mui/material/Modal';
import SwipeableDrawer from '@mui/material/SwipeableDrawer';

import WebtoonItem from '@/components/common/WebtoonItem';
import ConfirmBtn from '@/components/confirmBtn';
import GoSee from '@/components/pages/detail/GoSee';
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
  startDate: string;
  totalEpisode: number;
  colorHsl: string;
  authors: Array<IdName>;
  genres: Array<IdName>;
  days: Array<Day>;
  additions: {
    view: number;
    scoreCount: number;
    scoreAverage: string;
  };
}

interface Props {
  webtoon: Webtoon | null;
}

function DetailPage({ webtoon }: Props) {
  if (webtoon === null) {
    return <div>axios error</div>;
  } else {
    const router = useRouter();

    // 그라데이션 스타일
    const hsls = webtoon.colorHsl.split(',');
    const WEBTOON_THEME_COLOR = `hsl(${hsls[0]}, ${hsls[1]}%, 20%)`;
    const WEBTOON_GRADATION_COLOR = `linear-gradient(180deg, transparent, ${WEBTOON_THEME_COLOR} 530px)`;
    const coverStyle = { background: WEBTOON_GRADATION_COLOR };
    const coverStyle0 = { background: WEBTOON_THEME_COLOR };

    // 좋아요
    const [likeWebtoon, setLikeWebtoon] = useState<boolean>(false);
    const likeInput = async () => {
      if (!likeWebtoon) {
        const res = await likeWebtoonAPI(webtoon.id);
        if (res.isSuccess) {
          setLikeWebtoon(true);
        } else {
          alert(res.message);
        }
      } else {
        const res = await unLikeWebtoonAPI(webtoon.id);
        console.log(res);
        if (res.isSuccess) {
          setLikeWebtoon(false);
        } else {
          alert(res.message);
        }
      }
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
              ? 'whitespace-pre-wrap break-all text-sm font-bold text-FontPrimaryDark'
              : 'whitespace-pre-wrap break-all text-sm font-bold text-FontPrimaryDark line-clamp-2'
          }
        >
          {webtoon.plot}
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
    const tagList = [webtoon.genres[0].name, webtoon.days[0].codeId, webtoon.grade];
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
        <p className="text-2xl font-bold text-FontPrimaryDark">{webtoon.name}</p>
        <p className="text-FontPrimaryDark">
          {webtoon.authors[0].name}
          {webtoon.authors[1] ? `/${webtoon.authors[1].name}` : ''}
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
        >
          <div>
            <GoSee webtoonProvider={webtoon.webtoonUrl} />
          </div>
        </Modal>
      </div>
    );

    // 연재정보
    const infoList = [
      {
        name: '연재시작일',
        content: webtoon.startDate,
      },
      {
        name: '연재정보',
        content: webtoon.status,
      },
      {
        name: '조회수',
        content: webtoon.additions.view,
      },
      {
        name: '평가수',
        content: webtoon.additions.scoreCount,
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
          {infoList.map((info, index) => (
            <p className="mr-1 text-sm text-FontPrimaryDark" key={index}>
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
        pathname: `/detail/${webtoon.id}/comment`,
        query: {
          WEBTOON_THEME_COLOR,
          imagePath: webtoon.imagePath,
          name: webtoon.name,
        },
      });
    };

    // 현재 평점
    const scoreDiv = (
      <div>
        <div className="flex justify-center">
          <p className="text-FontPrimaryDark">평점</p>
        </div>
        <div className="flex justify-center">
          <p className="text-FontPrimaryDark">{webtoon.additions.scoreAverage}</p>
        </div>
        <div>
          <Rating defaultValue={Number(webtoon.additions.scoreAverage)} precision={0.5} readOnly />
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
          <p className="mr-1">{webtoon.additions.scoreCount}명 참여</p>
        </div>
        <p className="text-center">별점</p>
        <div className="flex justify-center">
          <Rating
            value={ratingInput}
            defaultValue={Number(webtoon.additions.scoreAverage)}
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
          {webtoon.name}의 리뷰를 모아봤어요
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
    const similarWebtoon = [
      {
        id: 4,
        imageUrl:
          'https://i.namu.wiki/i/0FPGuCn5XVDyejAOiSHqb_45uo-E4kwWkZQzS6YMYEwv4hHTPBNqTxD311G9nRYF9hsSkGh1IKVHsXcGUlXd_a-gEbRGbc0-3rWFQVian9aGOfj0NDrX4-qV5mRkMrEktPSaCH6_FjuIDatrhZnnGQ.webp',
        webtoonName: '역대급 영지 설계사',
        status: '연재중',
      },
      {
        id: 2,
        imageUrl:
          'https://i.namu.wiki/i/0FPGuCn5XVDyejAOiSHqb_45uo-E4kwWkZQzS6YMYEwv4hHTPBNqTxD311G9nRYF9hsSkGh1IKVHsXcGUlXd_a-gEbRGbc0-3rWFQVian9aGOfj0NDrX4-qV5mRkMrEktPSaCH6_FjuIDatrhZnnGQ.webp',
        webtoonName: '역대급 영지 설계사',
        status: '연재중',
      },
      {
        id: 3,
        imageUrl:
          'https://i.namu.wiki/i/0FPGuCn5XVDyejAOiSHqb_45uo-E4kwWkZQzS6YMYEwv4hHTPBNqTxD311G9nRYF9hsSkGh1IKVHsXcGUlXd_a-gEbRGbc0-3rWFQVian9aGOfj0NDrX4-qV5mRkMrEktPSaCH6_FjuIDatrhZnnGQ.webp',
        webtoonName: '역대급 영지 설계사',
        status: '연재중',
      },
    ];
    const elseWebtoons = (
      <div>
        <p className="text-2xl font-bold text-FontPrimaryDark">이런 웹툰은 어때요?</p>
        <div>
          {similarWebtoon.map(webtoon => (
            <WebtoonItem
              key={webtoon.id}
              webtoonName={webtoon.webtoonName}
              imageUrl={webtoon.imageUrl}
              status={webtoon.status}
            />
          ))}
        </div>
      </div>
    );

    return (
      <div>
        {/* <Image
        className="absolute h-auto w-full"
        src={webtoon.imagePath}
        alt="웹툰 이미지"
        width={300}
        height={500}
        priority
      /> */}
        <div style={coverStyle0} className="absolute h-full w-full"></div>
        <div className="flex justify-center">
          <img src={webtoon.imagePath} alt="웹툰이미지" className="absolute h-[550px]" />
        </div>
        <div style={coverStyle} className="absolute h-auto w-full">
          <div className="m-3 flex h-12 justify-between">
            <img src="/images/HeaderBar_Back.png" alt="goBack" onClick={() => router.back()}></img>
            <button className="h-full" onClick={likeInput}>
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
}

export default DetailPage;

// api
// detail 정보
export const getServerSideProps: GetServerSideProps = async context => {
  // const DETAIL_URL = 'https://j8b206.p.ssafy.io/api';
  const DETAIL_URL = process.env.API_URL;
  const { webtoon_id } = context.query;
  const URL = `/webtoons/${webtoon_id}`;
  try {
    const res = await axios.get(DETAIL_URL + URL);
    const webtoon = res.data.result;
    return { props: { webtoon } };
  } catch (error) {
    return { props: { webtoon: null } };
  }
};

/**
 * 관심등록
 * @param webtoon_id 웹툰 아이디
 * @returns
 */
const likeWebtoonAPI = async (webtoon_id: number) => {
  // const DETAIL_URL = process.env.API_URL;
  const DETAIL_URL = 'https://j8b206.p.ssafy.io/api';
  try {
    const res = await axios.patch(DETAIL_URL + `/webtoons/${webtoon_id}/like`);
    return res.data;
  } catch (error) {
    return null;
  }
};

/**
 * 관심등록 해제
 * @param webtoon_id 웹툰 아이디
 * @returns
 */
const unLikeWebtoonAPI = async (webtoon_id: number) => {
  // const DETAIL_URL = process.env.API_URL;
  const DETAIL_URL = 'https://j8b206.p.ssafy.io/api';
  try {
    const res = await axios.delete(DETAIL_URL + `/users/1/webtoons`, {
      data: {
        id: [webtoon_id],
      },
    });
    return res.data;
  } catch (erorr) {
    return null;
  }
};

// 사용자 평점 가져오기
// const getUserScore = async (webtoon_id: number) => {
//   const APIURL = 'https://j8b206.p.ssafy.io/api';
//   const URL = `/webtoons/${webtoon_id}/scores`;
//   try {
//     const res = await axios.get(APIURL + URL);
//     return res.data.result.score;
//   } catch (error) {
//     return null;
//   }
// };

// 평점 입력
