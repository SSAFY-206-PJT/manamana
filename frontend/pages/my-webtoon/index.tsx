import Headerbar from '../../components/common/Headerbar';
import Image from 'next/image';
import { useState, useEffect } from "react"
import PublishDayBlock from '@/components/pages/search/filter/PublishDayBlock';
import PublishStateBlock from '@/components/pages/search/filter/PublishStateBlock';
import axios from 'axios';

enum FocusState {
  DAY,
  END,
  NULL
}

interface Data {
  key: number;
  value: string;
}

interface Props {
  days: Data[];
  status: Data[];
}

export default function MyWebtoonPage(props : Props) {
  const [focus, setFocus] = useState<FocusState>(FocusState.NULL);
  const [detailElement, setDetailElement] = useState<any>();

  const [selectedDays, setSelectedDays] = useState<Data[]>([]);
  const [selectedStatus, setSelectedStatus] = useState<Data[]>([]);


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

  const selectDayButton = (data: Data) => {
    selectedDays.push(data);
  };

  const selectStatusButton = (data: Data) => {
    selectedStatus.push(data);
  };

  const unSelectDayButton = (data: Data) => {
    for (let i = 0; i < selectedDays.length; i++) {
      if (selectedDays[i].key === data.key) {
        selectedDays.splice(i, 1);
        break;
      }
    }
  };
  
  const unSelectStatusButton = (data: Data) => {
    for (let i = 0; i < selectedStatus.length; i++) {
      if (selectedStatus[i].key === data.key) {
        selectedStatus.splice(i, 1);
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
            props.days.map(
              (data) => {
                let status = false;
                for (let i = 0; i < selectedDays.length; i++) {
                  if (selectedDays[i].key === data.key) {
                    status = true;
                    break;
                  }
                }
                return <PublishDayBlock key={data.key} status={status} value={data} selectBlock={selectDayButton} unSelectBlock={unSelectDayButton} />
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
            props.status.map(
              (data) => {
                let status = false;
                for (let i = 0; i < selectedStatus.length; i++) {
                  if (selectedStatus[i].key === data.key) {
                    status = true;
                    break;
                  }
                }
                return <PublishStateBlock key={data.key} status={status} value={data} selectBlock={selectStatusButton} unSelectBlock={unSelectStatusButton} />
              }
            )
          }
        </div>
      )
    }
  }, [focus]);

  useEffect(
    () => {
      
      console.log(props.days);
    }, []
  )

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

export async function getServerSideProps() {
  const daysRes = await axios.get("https://j8b206.p.ssafy.io/api/webtoons/list/days");
  const statusRes = await axios.get("https://j8b206.p.ssafy.io/api/webtoons/list/status");

  return {
    props: {
      days: daysRes.data.result,
      status: statusRes.data.result
    }
  }
}