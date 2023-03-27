import Headerbar from "@/components/common/Headerbar";
import { motion } from "framer-motion";
import Image from "next/image";
import { useState, useEffect } from "react";
import styled from "@emotion/styled";
import Loading from "@/components/common/Loading";
import Cover from "@/components/pages/managola/Cover";
import Link from "next/link";
import { managolaInit, managolaEnd } from "../api/managola";
import useSWR from 'swr';
import Lottie from "react-lottie-player";
import yesEffect from "../../public/lottie/138218-check-icon.json";
import noEffect from "../../public/lottie/138219-x-icon.json";

interface WebtoonData {
  id: number,
  imagePath: string,
  name: string
}

interface ImageData {
  imagePath: string
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
  background-image: url("${(p: ImageData) => p.imagePath}");
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
    managolaInit()
      .then((value) => {
        if (value != null)
          setWebtoonDataList(value);
      });
    setPresentIdx(0);
    setCover(true);
    setChoiceResult([]);
    // 웹툰 정보를 다시 받아와서 data 변수 갱신
  }

  /*
  * @Method
  * - 다음 단계로 이동하는 메소드
  * */
  const nextWebtoon = () => {
    if (presentIdx < webtoonDataList.length - 1) { // data 배열의 크기 이하면, idx 증가
      setPresentIdx(presentIdx + 1);
    }
    else {  // data 배열의 크기 이상이면 종료해야 하므로, 최종 결과 화면 띄워줌

      console.log(choiceResult);
      // 서버에 결과를 보내고 해당 결과에 대한 결과 값을 도출받음
      // 도출이 되면 isLoading을 다시 false로 변환
      // 서버 연결 전까지는 timeout으로 임의로 진행
      // API 통신 결과로 얻게된 웹툰 데이터

      setIsLoading(true);
      managolaEnd(choiceResult)
        .then(
          (res) => {
            setIsLoading(false);
            let value: WebtoonData = res;
            if (value != null) {
              setPresentData(
                <div
                  className="w-full h-full flex flex-col absolute justify-center items-center overflow-hidden"
                >
                  <div className="w-full h-full fixed z-20 bg-FontSecondaryLight">
                  </div>
                  <Blur className="w-full h-full absolute text-center" imagePath={value.imagePath} />
                  <div className="flex flex-col justify-center items-center z-20">
                    <div className="text-FontPrimaryDark text-2xl m-4">
                      당신을 위한 웹툰은
                    </div>
                    <Link href={`/detail/${value.id}`}>
                      <div className="flex justify-center items-center">
                        <img
                          src={value.imagePath}
                          alt={value.name}
                          className={"rounded-2xl w-2/4 h-2/4"}
                        />
                      </div>
                      <div className="text-FontPrimaryDark text-center text-2xl m-4">{value.name}</div>
                    </Link>
                    <div className="border-2 bg-PrimaryLight pl-4 pr-4 pt-2 pb-2 rounded-xl">
                      <button className="text-FontPrimaryDark text-2xl" onClick={onReplayClick}>다시하기</button>
                    </div>
                  </div>
                </div>
              );
            }
          }
        );
    }
  }

  /*
  * @Method
  * - 선택 결과 저장 메소드
  * */
  const saveResult = (id: number) => {
    choiceResult.push(id);
  }

  /*
  * @useEffect
  * - 초기값 세팅
  * */
  useEffect(() => {
    setChoiceResult([]);
    managolaInit()
      .then((value) => {
        if (value != null)
          setWebtoonDataList(value);
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
          <div className="absolute z-20 w-full h-1/6 flex justify-center items-center">
            <div className="bg-BackgroundLightComponent rounded-3xl p-2 opacity-90">{presentIdx + 1} / {webtoonDataList != undefined ? webtoonDataList.length : 0}</div>
          </div>
          <motion.div
            drag="x"
            dragConstraints={{ left: 0, right: 0, top: 0, bottom: 0 }}
            dragElastic={0.14}
            onDragEnd={
              (event, info) => {
                const width = window.innerWidth;
                const dist = width / 6;
                if (info.point.x < (width / 2) - dist) {
                  nextWebtoon();
                  if (presentIdx != webtoonDataList.length - 1) {
                    setEffect(
                      <Lottie loop={false} speed={8.0} animationData={noEffect} play onComplete={() => setEffect(null)} className='w-2/3 h-2/3' />
                    );
                  }
                }
                else if (info.point.x > (width / 2) + dist) {
                  saveResult(webtoonDataList[presentIdx].id);
                  nextWebtoon();
                  if (presentIdx != webtoonDataList.length - 1) {
                    setEffect(
                      <Lottie loop={false} speed={8.5} animationData={yesEffect} play onComplete={() => setEffect(null)} className='w-2/3 h-2/3' />
                    );
                  }
                }
              }
            }
            className="w-full h-full bg-white">
            <img
              alt="웹툰 이미지"
              src={webtoonDataList[presentIdx].imagePath}
              className="w-full h-full object-cover" />
          </motion.div>
        </div>);
    }
  }, [presentIdx]);


  useEffect(
    () => {
      console.log(effect);
      // if(effect != null){
      //   setTimeout(() => {
      //     setEffect(null);
      //   }, 1500);
      // }
    }, [effect]);


  return (
    <div className="h-screen w-full">
      <div className="absolute w-full h-full flex items-center justify-center z-50 pointer-events-none">
        {effect}
      </div>
      <Headerbar showBackBtn={true} pageName={"마나골라"} rightBtn={undefined} key={"마나골라"} />
      <div className="w-full">
        <Cover status={cover} unCover={() => { setCover(false); }} />
        <Loading status={isLoading} />
        <div className="w-full h-full z-0 fixed flex">
          <div className="w-1/2 h-full bg-blue-600 flex flex-col justify-center items-start">
            <div className="ml-4 text-3xl text-FontPrimaryDark font-semibold ">좋아요</div>
            <div>
              <Image
                src={"/images/Like.png"}
                alt="좋아요"
                width={100}
                height={100}
              />
            </div>
          </div>
          <div className="w-1/2 h-full bg-red-600 flex flex-col justify-center items-end">
            <div className="mr-4 text-3xl text-FontPrimaryDark font-semibold">별로에요</div>
            <div>
              <Image
                src={"/images/Dislike.png"}
                alt="별로에요"
                width={100}
                height={100}
              />
            </div>
          </div>
        </div>
        <div className="h-full w-full z-10 fixed flex">
          {presentData}
        </div>
      </div>
    </div>
  );
}