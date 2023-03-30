import Headerbar from '../../components/common/Headerbar';
import { useState, useEffect } from 'react';
import PublishDayBlock from '@/components/pages/search/filter/PublishDayBlock';
import PublishStateBlock from '@/components/pages/search/filter/PublishStateBlock';
import axios from 'axios';
import { StateData } from '@/components/pages/search/filter/PublishStateBlock';
import { DayData } from '@/components/pages/search/filter/PublishDayBlock';

enum FocusState {
  DAY,
  END,
  NULL,
}

interface Props {
  days: DayData[];
  status: StateData[];
}

export default function MyWebtoonPage(props: Props) {
  const [focus, setFocus] = useState<FocusState>(FocusState.NULL);
  const [detailElement, setDetailElement] = useState<any>();

  const [selectedDays, setSelectedDays] = useState<DayData[]>([]);
  const [selectedStatus, setSelectedStatus] = useState<StateData[]>([]);

  const onDayClick = () => {
    if (focus === FocusState.DAY) {
      setFocus(FocusState.NULL);
    } else {
      setFocus(FocusState.DAY);
    }
  };

  const onEndClick = () => {
    if (focus === FocusState.END) {
      setFocus(FocusState.NULL);
    } else {
      setFocus(FocusState.END);
    }
  };

  const selectDayButton = (data: DayData) => {
    selectedDays.push(data);
    setSelectedDays([...selectedDays]);
  };

  const selectStatusButton = (data: StateData) => {
    selectedStatus.push(data);
    setSelectedStatus([...selectedStatus]);
  };

  const unSelectDayButton = (data: DayData) => {
    for (let i = 0; i < selectedDays.length; i++) {
      if (selectedDays[i].id === data.id) {
        selectedDays.splice(i, 1);
        break;
      }
    }
    setSelectedDays([...selectedDays]);
  };

  const unSelectStatusButton = (data: StateData) => {
    for (let i = 0; i < selectedStatus.length; i++) {
      if (selectedStatus[i].id === data.id) {
        selectedStatus.splice(i, 1);
        break;
      }
    }
    setSelectedStatus([...selectedStatus]);
  };

  useEffect(() => {
    if (focus === FocusState.NULL) {
      setDetailElement(null);
    } else if (focus === FocusState.DAY) {
      setDetailElement(
        <div className="mt-4 w-full text-center">
          {props.days.map(data => {
            let status = false;
            for (let i = 0; i < selectedDays.length; i++) {
              if (selectedDays[i].id === data.id) {
                status = true;
                break;
              }
            }
            return (
              <PublishDayBlock
                key={data.id}
                status={status}
                value={data}
                selectBlock={selectDayButton}
                unSelectBlock={unSelectDayButton}
              />
            );
          })}
        </div>,
      );
    } else {
      // FocusState.END
      setDetailElement(
        <div className="mt-4 flex gap-2">
          {props.status.map(data => {
            let status = false;
            for (let i = 0; i < selectedStatus.length; i++) {
              if (selectedStatus[i].id === data.id) {
                status = true;
                break;
              }
            }
            return (
              <PublishStateBlock
                key={data.id}
                status={status}
                value={data}
                selectBlock={selectStatusButton}
                unSelectBlock={unSelectStatusButton}
              />
            );
          })}
        </div>,
      );
    }
  }, [focus]);

  useEffect(() => {
    console.log('props.days', props.days);
    console.log('props.status', props.status);
    console.log('selectedDays', selectedDays);
    console.log('selectedStatus', selectedStatus);
  }, []);

  useEffect(() => {
    console.log('selectedDays', selectedDays);
  }, [selectedDays]);

  return (
    <div>
      <Headerbar showBackBtn={true} pageName="내 웹툰" rightBtn="EDIT"></Headerbar>
      <div className="flex h-[94vh] w-full flex-col bg-BackgroundLight pt-4">
        <div className="m-4 rounded-xl bg-BackgroundLightComponent p-4">
          <div className="flex">
            <div onClick={onDayClick} className="w-full text-center text-lg font-bold">
              {FocusState.DAY === focus ? <span className="text-PrimaryLight">요일</span> : <span>요일</span>}
            </div>
            <div onClick={onEndClick} className="w-full text-center text-lg font-bold">
              {FocusState.END === focus ? <span className="text-PrimaryLight">완결여부</span> : <span>완결여부</span>}
            </div>
          </div>
          {detailElement}
        </div>
        <div className="m-4 rounded-xl bg-BackgroundLightComponent p-4">
          <div className="w-full text-center text-lg font-semibold">필터링 결과</div>
          <div>이곳에.. 웹툰들을..</div>
        </div>
        <div className="m-4 rounded-xl bg-BackgroundLightComponent p-4">
          <div className="w-full text-center text-lg font-semibold">그 외</div>
          <div>이곳에.. 웹툰들을..</div>
        </div>
      </div>
    </div>
  );
}

export async function getServerSideProps() {
  const daysRes = await axios.get('https://j8b206.p.ssafy.io/api/webtoons/list/days');
  const statusRes = await axios.get('https://j8b206.p.ssafy.io/api/webtoons/list/status');

  return {
    props: {
      days: daysRes.data.result,
      status: statusRes.data.result,
    },
  };
}
