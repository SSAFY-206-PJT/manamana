import Headerbar from '@/components/common/Headerbar';
import { motion } from 'framer-motion';
import { useState, useEffect } from 'react';
import styled from '@emotion/styled';
import Loading from '@/components/common/Loading';
import Cover from '@/components/pages/managola/Cover';
import Link from 'next/link';
import { managolaInit, managolaEnd } from '../api/managola';
import { useRouter } from 'next/router';
import Lottie from 'react-lottie-player';
import yesEffect from '../../public/lottie/138218-check-icon.json';
import noEffect from '../../public/lottie/138219-x-icon.json';

interface WebtoonData {
  id: number;
  imagePath: string;
  name: string;
}

interface ImageData {
  imagePath: string;
}

export default function ManagolaPage() {
  /*
   * @Variable
   * 현재 위치하고 있는 단계 저장할 변수
   * */
  const [presentIdx, setPresentIdx] = useState<number>(-1);
  /*
   * @Variable
   * 현재 위치하고 있는 단계의 Element 저장할 변수
   * */
  const [presentData, setPresentData] = useState<any>();

  /*
   * @Variable
   * 표지를 띄운 상황인지 아닌지 저장할 변수
   * */
  const [cover, setCover] = useState<boolean>(true);

  /*
   * @Variable
   * 로딩상태인지 아닌지 저장할 변수
   * */
  const [isLoading, setIsLoading] = useState<boolean>(false);

  /*
   * @Variable
   * 웹툰 데이터를 가져와서 저장할 변수
   * */
  const [webtoonDataList, setWebtoonDataList] = useState<WebtoonData[]>([]);

  /*
   * @Variable
   * 선택된 웹툰 데이터를 가져와서 저장할 변수
   * */
  const [choiceResult, setChoiceResult] = useState<number[]>([]);

  const [effect, setEffect] = useState<any>(null);

  /*
   * @Variable
   * 흐린 배경을 나타내기 위해 표현
   * */
  const Blur = styled.div`
    filter: blur(10px);
    -webkit-filter: blur(10px);
    background-image: url('${(p: ImageData) => p.imagePath}');
    overflow: hidden;
    background-size: cover;
  `;

  /*
   * @Method
   * - 초기값을 0으로 지정
   * - 표지 생성
   * - 웹툰 값 다시 받아옴
   * - 선택된 결과 초기화
   * */
  const onReplayClick = () => {
    managolaInit().then(value => {
      if (value != null) setWebtoonDataList(value);
    });
    setPresentIdx(0);
    setCover(true);
    setChoiceResult([]);
    // 웹툰 정보를 다시 받아와서 data 변수 갱신
  };

  // 보러가기 클릭
  const goSeeClick = (value: any) => {
    const router = useRouter();
    router.push(`/details/${value.id}`);
  };
  /*
   * @Method
   * - 다음 단계로 이동하는 메소드
   * */
  const nextWebtoon = () => {
    if (presentIdx < webtoonDataList.length - 1) {
      // data 배열의 크기 이하면, idx 증가
      setPresentIdx(presentIdx + 1);
    } else {
      // data 배열의 크기 이상이면 종료해야 하므로, 최종 결과 화면 띄워줌

      // console.log(choiceResult);
      // 서버에 결과를 보내고 해당 결과에 대한 결과 값을 도출받음
      // 도출이 되면 isLoading을 다시 false로 변환
      // 서버 연결 전까지는 timeout으로 임의로 진행
      // API 통신 결과로 얻게된 웹툰 데이터

      setIsLoading(true);
      managolaEnd(choiceResult).then(res => {
        setIsLoading(false);
        let value: WebtoonData = res;
        if (value != null) {
          setPresentData(
            <div className="absolute flex h-full w-full flex-col items-center justify-center overflow-hidden">
              <div className="fixed z-20 h-full w-full bg-FontSecondaryLight"></div>
              <Blur className="absolute h-full w-full text-center" imagePath={value.imagePath} />
              <div className="z-20 flex flex-col items-center justify-center">
                <div className="m-4 text-2xl text-FontPrimaryDark">당신을 위한 웹툰은</div>
                <Link href={`/detail/${value.id}`}>
                  <div className="flex items-center justify-center">
                    <img
                      src={value.imagePath}
                      alt={value.name}
                      className={'h-2/4 w-2/4 rounded-2xl'}
                    />
                  </div>
                  <div className="m-4 text-center text-2xl text-FontPrimaryDark">{value.name}</div>
                </Link>
                <div className="flex">
                  <div className="m-2 rounded-xl border-2 bg-PrimaryLight pl-4 pr-4 pt-2 pb-2">
                    <button className="text-2xl text-FontPrimaryDark" onClick={onReplayClick}>
                      다시하기
                    </button>
                  </div>
                  <div className="m-2 rounded-xl border-2 bg-PrimaryLight pl-4 pr-4 pt-2 pb-2">
                    <button className="text-2xl text-FontPrimaryDark">
                      <Link href={`/detail/${value.id}`}>보러가기</Link>
                    </button>
                  </div>
                </div>
              </div>
            </div>,
          );
        }
      });
    }
  };

  /*
   * @Method
   * - 선택 결과 저장 메소드
   * */
  const saveResult = (id: number) => {
    choiceResult.push(id);
  };

  /*
   * @useEffect
   * - 초기값 세팅
   * */
  useEffect(() => {
    setChoiceResult([]);
    managolaInit().then(value => {
      if (value != null) setWebtoonDataList(value);
    });
  }, []);

  /*
   * @useEffect
   * - webtoonDataList가 변경되었을 때 호출됨
   * */
  useEffect(() => {
    if (webtoonDataList != undefined && webtoonDataList.length > 0) {
      setPresentIdx(0);
    }
  }, [webtoonDataList]);

  /*
   * @UseEffect
   * - 드래그 가능한 웹툰 이미지를 presentIdx가 변환될 때 마다 갱신해줌
   * */
  useEffect(() => {
    if (webtoonDataList != undefined && webtoonDataList.length > 0) {
      setPresentData(
        <div>
          <div className="absolute z-20 flex h-1/6 w-full items-center justify-center">
            <div className="rounded-3xl bg-BackgroundLightComponent p-2 opacity-90">
              {presentIdx + 1} / {webtoonDataList != undefined ? webtoonDataList.length : 0}
            </div>
          </div>
          <motion.div
            drag="x"
            dragConstraints={{ left: 0, right: 0, top: 0, bottom: 0 }}
            dragElastic={0.14}
            onDragEnd={(event, info) => {
              const width = window.innerWidth;
              const dist = width / 6;
              if (info.point.x < width / 2 - dist) {
                nextWebtoon();
                if (presentIdx != webtoonDataList.length - 1) {
                  setEffect(
                    <Lottie
                      loop={false}
                      speed={8.0}
                      animationData={noEffect}
                      play
                      onComplete={() => setEffect(null)}
                      className="h-2/3 w-2/3"
                    />,
                  );
                }
              } else if (info.point.x > width / 2 + dist) {
                saveResult(webtoonDataList[presentIdx].id);
                nextWebtoon();
                if (presentIdx != webtoonDataList.length - 1) {
                  setEffect(
                    <Lottie
                      loop={false}
                      speed={8.5}
                      animationData={yesEffect}
                      play
                      onComplete={() => setEffect(null)}
                      className="h-2/3 w-2/3"
                    />,
                  );
                }
              }
            }}
            className="h-full w-full bg-white"
          >
            <img
              alt="웹툰 이미지"
              src={webtoonDataList[presentIdx].imagePath}
              className="h-full w-full object-cover"
            />
          </motion.div>
        </div>,
      );
    }
  }, [presentIdx]);

  return (
    <div className="h-screen w-full">
      <div className="pointer-events-none absolute z-50 flex h-full w-full items-center justify-center">
        {effect}
      </div>
      <Headerbar showBackBtn={true} pageName={'마나골라'} rightBtn={undefined} key={'마나골라'} />
      <div className="w-full">
        <Cover
          status={cover}
          unCover={() => {
            setCover(false);
          }}
        />
        <Loading status={isLoading} />
        <div className="fixed z-0 flex h-full w-full">
          <div className="flex h-full w-1/2 flex-col items-start justify-center bg-blue-600">
            <div className="ml-4 text-3xl font-semibold text-FontPrimaryDark ">좋아요</div>
            <div>
              <img src={'/images/Like.png'} alt="좋아요" className="h-[100px] w-[100px]"></img>
            </div>
          </div>
          <div className="flex h-full w-1/2 flex-col items-end justify-center bg-red-600">
            <div className="mr-4 text-3xl font-semibold text-FontPrimaryDark">별로에요</div>
            <div>
              <img src={'images/Dislike.png'} alt="별로예요" className="h-[100px] w-[100px]"></img>
            </div>
          </div>
        </div>
        <div className="fixed z-10 flex h-full w-full">{presentData}</div>
      </div>
    </div>
  );
}
