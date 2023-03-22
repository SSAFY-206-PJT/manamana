import Headerbar from '../../components/common/Headerbar';
import Image from 'next/image';
import { useState, useEffect } from "react"
import PublishDayBlock from '@/components/pages/search/filter/PublishDayBlock';
import PublishStateBlock from '@/components/pages/search/filter/PublishStateBlock';

enum FocusState {
  DAY,
  END,
  NULL
}

export default function MyWebtoonPage() {
  const [focus, setFocus] = useState<FocusState>(FocusState.NULL);
  const [selectedBlocks, setSelectedBlocks] = useState<string[]>([]);
  const [detailElement, setDetailElement] = useState<any>();

  const onDayClick = () => {
    if (focus === FocusState.DAY) {
      setFocus(FocusState.NULL);
    }
    else {
      setFocus(FocusState.DAY);
    }
  };

  const onEndClick = () => {
    if (focus === FocusState.END) {
      setFocus(FocusState.NULL);
    }
    else {
      setFocus(FocusState.END);
    }
  };

  const selectButton = (value: string) => {
    selectedBlocks.push(value);
  };

  const unSelectBlock = (value: string) => {
    for (let i = 0; i < selectedBlocks.length; i++) {
      if (selectedBlocks[i] === value) {
        selectedBlocks.splice(i, 1);
        break;
      }
    }
  };

  useEffect(() => {
    if (focus === FocusState.NULL) {
      setDetailElement(null);
    }
    else if (focus === FocusState.DAY) {
      setDetailElement(
        <div className='w-full text-center mt-4'>
          {
            ['월', '화', '수', '목', '금', '토', '일'].map(
              (value) => {
                let status = false;
                for (let i = 0; i < selectedBlocks.length; i++) {
                  if (selectedBlocks[i] === value) {
                    status = true;
                    break;
                  }
                }
                return <PublishDayBlock status={status} key={value} value={value} selectBlock={selectButton} unSelectBlock={unSelectBlock} />
              }
            )
          }
        </div>
      );
    }
    else { // FocusState.END
      setDetailElement(
        <div className='flex mt-4 gap-2'>
          {
            ['완결', '미완결'].map(
              (value) => {
                let status = false;
                for (let i = 0; i < selectedBlocks.length; i++) {
                  if (selectedBlocks[i] === value) {
                    status = true;
                    break;
                  }
                }
                return <PublishStateBlock status={status} key={value} value={value} selectBlock={selectButton} unSelectBlock={unSelectBlock} />
              }
            )
          }
        </div>
      )
    }
  }, [focus]);

  return (
    <div>
      <Headerbar showBackBtn={true} pageName="내 웹툰" rightBtn="EDIT"></Headerbar>
      <div className='flex flex-col pt-4 bg-BackgroundLight w-full h-[94vh]'>
        <div className='bg-BackgroundLightComponent m-4 p-4 rounded-xl'>
          <div className='flex'>
            <div onClick={onDayClick} className='w-full text-center font-bold text-lg'>
              {
                FocusState.DAY === focus ?
                <span className='text-PrimaryLight'>요일</span>
                :
                <span>요일</span>
              }
            </div>
            <div onClick={onEndClick} className='w-full text-center font-bold text-lg'>
            {
                FocusState.END === focus ?
                <span className='text-PrimaryLight'>완결여부</span>
                :
                <span>완결여부</span>
              }
            </div>
          </div>
          {
            detailElement
          }
        </div>
        <div className='bg-BackgroundLightComponent m-4 p-4 rounded-xl'>
          <div className='w-full text-center font-semibold text-lg'>필터링 결과</div>
          <div>
            이곳에.. 웹툰들을..
          </div>
        </div>
        <div className='bg-BackgroundLightComponent m-4 p-4 rounded-xl'>
          <div className='w-full text-center font-semibold text-lg'>그 외</div>
          <div>
            이곳에.. 웹툰들을..
          </div>
        </div>
      </div>
    </div>
  );
}
