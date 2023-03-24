import Headerbar from "@/components/common/Headerbar";
import { motion } from "framer-motion"
import Image from "next/image";
import { useState, useEffect } from "react"
import { css } from "@emotion/react"


export default function ManagolaPage() {
  /*
  * @Variable
  * 현재 위치하고 있는 단계 저장할 변수
  * */
  const [presentIdx, setPresentIdx] = useState<number>(0);
  /*
  * @Variable
  * 현재 위치하고 있는 단계의 Element 저장할 변수
  * */
  const [presentData, setPresentData] = useState<any>();

  const [cover, setCover] = useState<boolean>(true);

  /*
  * @Variable
  * 전체 단계의 정보를 저장할 변수
  * */
  const data = [{
    id: 1,
    src: "/images/Temp_Webtoon_Thumnail.jpg",
    name: "가"
  },
  {
    id: 2,
    src: "/images/Temp_Profile.jpg",
    name: "나"
  },
  {
    id: 3,
    src: "/images/Temp_Webtoon_Thumnail.jpg",
    name: "다"
  },
  {
    id: 4,
    src: "/images/Temp_Profile.jpg",
    name: "라"
  },
  {
    id: 5,
    src: "/images/Temp_Webtoon_Thumnail.jpg",
    name: "마"
  },
  {
    id: 6,
    src: "/images/Temp_Profile.jpg",
    name: "바"
  }
  ];

  const choiceResult: number[] = [];

  /*
  * @Method
  * - 초기값을 0으로 지정
  * - 추후에 값을 다시 받아오는 로직 추가해야 함
  * */
  const onReplayClick = () => {
    setPresentIdx(0);
    setCover(true);
    // 웹툰 정보를 다시 받아와서 data 변수 갱신
  }

  /*
  * @Method
  * - 다음 단계로 이동하는 메소드
  * */
  const nextWebtoon = () => {
    if (presentIdx < data.length - 1) { // data 배열의 크기 이하면, idx 증가
      setPresentIdx(presentIdx + 1);
    }
    else {  // data 배열의 크기 이상이면 종료해야 하므로, 최종 결과 화면 띄워줌

      // 서버에 결과를 보내줌
      console.log(choiceResult);

      setPresentData(
        <div
          className="w-full h-full flex flex-col absolute justify-center items-center overflow-hidden"
        >
          <div className="w-full h-full fixed z-10 bg-FontSecondaryLight">

          </div>
          <div className="w-full h-full absolute"
            css={
              css`
                  filter: blur(10px);
                  -webkit-filter: blur(10px);
                  background-image: url("/images/Temp_Webtoon_Thumnail.jpg");
                  overflow: hidden;
                  background-size: cover;
                `
            }></div>
          <div className="flex flex-col justify-center items-center z-20">
            <div className="text-FontPrimaryDark text-2xl m-4">
              당신을 위한 웹툰은
            </div>
            <div>
              <Image
                src={"/images/Temp_Webtoon_Thumnail.jpg"}
                alt="웹툰 추천 결과"
                width={200}
                height={350}
                className={"rounded-2xl"}
              />
            </div>
            <div className="text-FontPrimaryDark text-2xl m-4">내가 키운 S급들</div>
            <div className="border-2 bg-PrimaryLight pl-4 pr-4 pt-2 pb-2 rounded-xl">
              <button className="text-FontPrimaryDark text-2xl" onClick={onReplayClick}>다시하기</button>
            </div>
          </div>
        </div>
      )
    }
  }

  // 현재 작동 안됨 -- 수정 할 예정
  const saveResult = (id: number) => {
    console.log(id, "선택됨");
    choiceResult.push(id);
  }


  /*
  * @UseEffect
  * - 초기값을 0으로 지정
  * - 없어도 되나, 추후에 설명
  * */
  useEffect(() => {
    setPresentIdx(0);
  }, []);

  /*
  * @UseEffect
  * - 드래그 가능한 웹툰 이미지를 presentIdx가 변환될 때 마다 갱신해줌
  * */
  useEffect(() => {
    setPresentData(<motion.div
      drag="x"
      dragConstraints={{ left: 0, right: 0, top: 0, bottom: 0 }}
      dragElastic={0.14}
      onDragEnd={
        (event, info) => {
          const width = window.innerWidth;
          const dist = width / 6;
          if (info.point.x < (width / 2) - dist) {
            console.log(data[presentIdx].name, "별로에요");
            nextWebtoon();
          }
          else if (info.point.x > (width / 2) + dist) {
            console.log(data[presentIdx].name, "좋아요");
            saveResult(data[presentIdx].id);
            nextWebtoon();
          }
          else {
            console.log(data[presentIdx].name, "middle");
          }
        }
      }
      className="w-full h-full bg-white">
      <img src={data[presentIdx].src} className="w-full h-full object-cover" />
    </motion.div>);
  }, [presentIdx]);


  return (
    <div className="h-screen w-full">
      <Headerbar showBackBtn={true} pageName={"마나골라"} rightBtn={undefined} key={"마나골라"} />
      <div className="w-full">
        {
          cover ?
            <div className="flex flex-col justify-center items-center w-full h-full bg-slate-600 z-30 fixed">
              <video src="/movies/managola.mp4" autoPlay loop muted className="h-full w-full object-cover absolute" />
              <div className="z-40">
                <div className="flex flex-col items-center justify-center mb-4">
                  <span className="text-2xl text-FontSecondaryLight font-semibold">웹툰 취향 테스트</span>
                  <span className="text-xl text-FontSecondaryLight font-semibold">당신을 위한 웹툰을 찾아보세요!</span>
                </div>
                <div className="mb-4 flex justify-center items-center">
                  <span className="text-6xl text-FontPrimaryLight font-bold">마나골라</span>
                </div>
                <div className="flex justify-center m-12">
                  <button 
                  className="font-semibold text-2xl bg-BackgroundLightComponent p-2 rounded-lg opacity-80 mb-4 text-FontPrimaryLight drop-shadow-lg"
                  onClick={() => { setCover(false); }}>시작하기</button>
                </div>
                <div className="flex flex-col items-center text-FontSecondaryLight">
                  <div>좋아하는 웹툰은 오른쪽으로</div>
                  <div>별로인 웹툰은 왼쪽으로 밀어보세요.</div>
                  <div>한번 선택하면 되돌릴 수 없어요.</div>
                </div>
              </div>
            </div>
            :
            null
        }
        <div className="absolute z-20 w-full h-1/6 flex justify-center items-center">
          <div className="bg-BackgroundLightComponent rounded-3xl p-2 opacity-90">{presentIdx + 1} / {data.length}</div>
        </div>
        <div className="w-full h-full z-0 fixed flex">
          <div className="w-1/2 h-full bg-blue-400 flex flex-col justify-center items-start">
            <div className="ml-4 text-3xl text-FontPrimaryDark ">좋아요</div>
            <div>
              <Image
                src={"/images/Like.png"}
                alt="좋아요"
                width={100}
                height={100}
              />
            </div>
          </div>
          <div className="w-1/2 h-full bg-red-400 flex flex-col justify-center items-end">
            <div className="mr-4 text-3xl text-FontPrimaryDark">별로에요</div>
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
