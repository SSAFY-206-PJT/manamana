import PublishStateBlock from "@/components/pages/search/filter/PublishStateBlock";
import PublishDayBlock from "@/components/pages/search/filter/PublishDayBlock";
import GenreStateBlock from "@/components/pages/search/filter/GenreStateBlock";
import AgeGradeBlock from "@/components/pages/search/filter/AgeGradeBlock";
import {useState, useEffect} from "react";

export default function FilterPage() {
  /*
  * @Data
  * 필터에 필요한 데이터 입력
  * */
  const publishState = [
    {
      content: '미완결',
      value: 'PS_01'
    },
    {
      content: '완결',
      value: 'PS_02'
    }
  ];
  const publishDayOfTheWeek = [
    {
      content: '월',
      value: 'PDOTW_01'
    },
    {
      content: '화',
      value: 'PDOTW_02'
    },
    {
      content: '수',
      value: 'PDOTW_03'
    },
    {
      content: '목',
      value: 'PDOTW_04'
    },
    {
      content: '금',
      value: 'PDOTW_05'
    },
    {
      content: '토',
      value: 'PDOTW_06'
    },
    {
      content: '일',
      value: 'PDOTW_07'
    }
  ];
  const genreState = [
    {
      content: '액션',
      value: 'GS_001'
    },
    {
      content: '판타지',
      value: 'GS_002'
    },
    {
      content: '학원',
      value: 'GS_003'
    },
    {
      content: '개그',
      value: 'GS_004'
    },
    {
      content: '무협',
      value: 'GS_005'
    },
    {
      content: '공포/스릴러',
      value: 'GS_006'
    },
    {
      content: '드라마',
      value: 'GS_007'
    },
    {
      content: '로맨스',
      value: 'GS_008'
    },
    {
      content: '옴니버스',
      value: 'GS_009'
    },
    {
      content: '일상',
      value: 'GS_010'
    },
    {
      content: 'BL',
      value: 'GS_011'
    },
    {
      content: 'GL',
      value: 'GS_012'
    },
    {
      content: 'SF',
      value: 'GS_013'
    },
    {
      content: '스포츠',
      value: 'GS_014'
    },
    {
      content: '시대극',
      value: 'GS_015'
    }
  ];
  const ageGrade = [
    {
      content: '전연령',
      value: 'AG_01'
    },
    {
      content: '성인',
      value: 'AG_02'
    }
  ];

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

  return (
    <div className="flex flex-col gap-4 h-full w-full bg-BackgroundLight">
      <div className="flex justify-center items-center h-16 m-2">
        <span className="text-2xl">필터</span>
      </div>
      <div className="flex flex-col gap-4 m-4">
        <div className="flex flex-col gap-1">
          <div className="text-lg">완결 여부</div>
          <div className="flex gap-2">
            {publishState.map((v) => <PublishStateBlock content={v.content} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} />)}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">연재 요일</div>
          <div className="text-center">
            {publishDayOfTheWeek.map((v) => <PublishDayBlock content={v.content} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} />)}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">장르</div>
          <div className="text-center">
            {genreState.map((v) => <GenreStateBlock content={v.content} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} />)}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">연령등급</div>
          <div className="flex w-full justify-center gap-8">
            {ageGrade.map((v) => <AgeGradeBlock content={v.content} value={v.value} selectBlock={selectBlock} unSelectBlock={unSelectBlock} />)}
          </div>
        </div>
      </div>
      <div>푸터</div>
    </div>
  );
}
