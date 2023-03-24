import PublishStateBlock from "@/components/pages/search/filter/PublishStateBlock";
import PublishDayBlock from "@/components/pages/search/filter/PublishDayBlock";
import GenreStateBlock from "@/components/pages/search/filter/GenreStateBlock";
import AgeGradeBlock from "@/components/pages/search/filter/AgeGradeBlock";
import {useState, useEffect} from "react";
import ConfirmBtn from "@/components/confirmBtn";
import { useRouter } from "next/router"
import { RootState } from "../../../store/index";
import { useDispatch, useSelector } from "react-redux";
import { changeCurSearchTag } from "@/store/CurSearchTagSlice";

export default function FilterPage() {
  /*
  * @Variable
  * - 필터에 필요한 데이터 입력
  * */
  const publishState = [
    {
      value: '미완결',
    },
    {
      value: '완결',
    }
  ];
  const publishDayOfTheWeek = [
    {
      value: '월',
    },
    {
      value: '화',
    },
    {
      value: '수',
    },
    {
      value: '목',
    },
    {
      value: '금',
    },
    {
      value: '토',
    },
    {
      value: '일',
    }
  ];
  const genreState = [
    {
      value: '액션',
    },
    {
      value: '판타지',
    },
    {
      value: '학원',
    },
    {
      value: '개그',
    },
    {
      value: '무협',
    },
    {
      value: '공포/스릴러',
    },
    {
      value: '드라마',
    },
    {
      value: '로맨스',
    },
    {
      value: '옴니버스',
    },
    {
      value: '일상',
    },
    {
      value: 'BL',
    },
    {
      value: 'GL',
    },
    {
      value: 'SF',
    },
    {
      value: '스포츠',
    },
    {
      value: '시대극',
    }
  ];
  const ageGrade = [
    {
      value: '전연령',
    },
    {
      value: '성인',
    }
  ];

  /*
  * @Variable
  * */
  const router = useRouter(); // 화면 전환을 위한 라우터
  const dispatch = useDispatch(); // redux 사용을 위한 dispatch
  const curSearchTag = useSelector((state: RootState) => state.searchTag);  // 현재 redux에서 저장한 searchTag 값 가져오기
  const [selectedBlocks, setSelectedBlocks] = useState<string[]>([]);

  const selectBlock = (value: string) => {
    selectedBlocks.push(value);
  }

  const unSelectBlock = (value: string) => {
    for(let i = 0; i < selectedBlocks.length; i++){
      if(selectedBlocks[i] === value){
        selectedBlocks.splice(i, 1);
        break;
      }
    }
  }

  const onConfirmClick = () => {
    // 지금까지 선택된 selectedBlocks 데이터를 보낸다.
    dispatch(changeCurSearchTag(selectedBlocks));
    // search로 이동한다.
    router.replace({
      pathname: '/search'
    },
    "/search"
    );
  }

  const onCancelClick = () => {
    // search로 이동한다.
    router.back();
  }

  useEffect(() => {
    setSelectedBlocks([...curSearchTag.tags]);
  }, []);

  return (
    <div className="flex flex-col gap-4 h-full w-full bg-BackgroundLight">
      <div className="flex justify-center items-center h-16 m-2">
        <span className="text-2xl text-PrimaryLight font-bold">필터</span>
      </div>
      <div className="flex flex-col gap-4 m-4">
        <div className="flex flex-col gap-1">
          <div className="text-lg">완결 여부</div>
          <div className="flex gap-2">
            {publishState.map((v) => {
            let status: boolean = false;
            for(let i = 0; i < curSearchTag.tags.length; i++){
              if(curSearchTag.tags[i] === v.value){
                status = true;
                break;
              }
            }
            return <PublishStateBlock key={v.value} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} status={status} />
            })}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">연재 요일</div>
          <div className="text-center">
            {publishDayOfTheWeek.map((v) => {
              let status: boolean = false;
              for(let i = 0; i < curSearchTag.tags.length; i++){
                if(curSearchTag.tags[i] === v.value){
                  status = true;
                  break;
                }
              }
            return <PublishDayBlock key={v.value} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} status={status} />
          })}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">장르</div>
          <div className="text-center">
            {genreState.map((v) => {
              let status: boolean = false;
              for(let i = 0; i < curSearchTag.tags.length; i++){
                if(curSearchTag.tags[i] === v.value){
                  status = true;
                  break;
                }
              }
              return <GenreStateBlock key={v.value} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} status={status} />
            })}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">연령등급</div>
          <div className="flex w-full justify-center gap-8">
            {ageGrade.map((v) => {
              let status: boolean = false;
              for(let i = 0; i < curSearchTag.tags.length; i++){
                if(curSearchTag.tags[i] === v.value){
                  status = true;
                  break;
                }
              }
              return <AgeGradeBlock key={v.value} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} status={status} />
            })}
          </div>
        </div>
      </div>
      <div className="m-8">
        <ConfirmBtn cancel={onCancelClick} confirm={onConfirmClick} />
      </div>
    </div>
  );
}
