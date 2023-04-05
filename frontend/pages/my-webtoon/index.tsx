import Headerbar from '../../components/common/Headerbar';
import { useState, useEffect } from 'react';
import { GetServerSideProps } from 'next';
import PublishDayBlock from '@/components/pages/search/filter/PublishDayBlock';
import PublishStateBlock from '@/components/pages/search/filter/PublishStateBlock';
import axios from 'axios';
import { getUserLike } from '../api/detail';
import { getCookie } from '@/util/cookie';
import WebtoonItem from '@/components/common/WebtoonItem';

enum FocusState {
  DAY,
  END,
  NULL,
}

const defaultLikeWebtoon = [
  {
    id: 0,
    name: '등록해보세요',
    imagePath: '/icon-192x192.png',
    status: '연재중',
  },
];

interface Data {
  key: number;
  value: string;
}

interface Props {
  days: Data[];
  status: Data[];
  myRes: any;
}

export default function MyWebtoonPage(props: Props) {
  const token = getCookie('accessToken');
  const [focus, setFocus] = useState<FocusState>(FocusState.NULL);
  const [detailElement, setDetailElement] = useState<any>();

  const [selectedDays, setSelectedDays] = useState<Data[]>([]);
  const [selectedStatus, setSelectedStatus] = useState<Data[]>([]);
  // 좋아하는 웹툰 있는 경우에도 default로 들어감
  const [likeWebtoons, setLikeWebtoons] = useState<Array<any>>(props.myRes);
  const [resWebtoons, setResWebtoons] = useState<Array<any>>([]);
  const [elseWebtoons, setElseWebtoons] = useState<Array<any>>([]);

  // const getMyWebtoon = async () => {
  //   const res = await getUserLike(token);
  //   if (res.result.length > 0) {
  //     const result = res.result;
  //     console.log(result);
  //     setLikeWebtoons([...result]);
  //   } else {
  //     const result = defaultLikeWebtoon;
  //     setLikeWebtoons([...result]);
  //   }
  // };

  // useEffect(() => {
  //   getMyWebtoon();
  // }, []);

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

  const selectDayButton = (data: Data) => {
    selectedDays.push(data);
    setSelectedDays([...selectedDays]);
  };

  const selectStatusButton = (data: Data) => {
    selectedStatus.push(data);
    setSelectedStatus([...selectedStatus]);
  };
  console.log(selectedDays);
  console.log(selectedStatus);

  const unSelectDayButton = (data: Data) => {
    for (let i = 0; i < selectedDays.length; i++) {
      if (selectedDays[i].key === data.key) {
        selectedDays.splice(i, 1);
        break;
      }
    }
    setSelectedDays([...selectedDays]);
  };

  const unSelectStatusButton = (data: Data) => {
    for (let i = 0; i < selectedStatus.length; i++) {
      if (selectedStatus[i].key === data.key) {
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
              if (selectedDays[i].key === data.key) {
                status = true;
                break;
              }
            }
            return (
              <PublishDayBlock
                key={data.key}
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
              if (selectedStatus[i].key === data.key) {
                status = true;
                break;
              }
            }
            return (
              <PublishStateBlock
                key={data.key}
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
    const newRes: Array<any> = [];
    const newElse: Array<any> = [];
    console.log('selectedStatus', selectedStatus);
    likeWebtoons.map((webtoon: any) => {
      console.log(webtoon.status);
      let hasDayMatch =
        selectedDays.length === 0 || selectedDays.some(day => webtoon.days.includes(day.key));
      let hasEndMatch =
        selectedStatus.length === 0 ||
        selectedStatus.some(
          status =>
            status.key === webtoon.status ||
            (Array.isArray(status.key) && status.key.includes(webtoon.status)),
        );

      if (
        !(selectedDays.length === 0 && selectedStatus.length === 0) &&
        hasDayMatch &&
        hasEndMatch
      ) {
        newRes.push(webtoon);
      } else {
        newElse.push(webtoon);
      }

      // webtoon.days 중 하나라도 selectedDayKeys에 포함되어 있으면 newRes.push(webtoon)
      // webtoon.days 중 하나도 없으면 selectedDayKeys에 포함되어 있으면 newElse.push(webtoon)
    });
    setElseWebtoons([...newElse]);
    setResWebtoons([...newRes]);
  }, [selectedDays, selectedStatus]);

  return (
    <div className="min-h-screen min-w-screen h-full w-full bg-BackgroundLight">
      <Headerbar showBackBtn={true} pageName="내 웹툰"></Headerbar>
      <div>
        <div className="mx-4 my-6 rounded-xl bg-BackgroundLightComponent p-4">
          <div className="flex">
            <div onClick={onDayClick} className="w-full text-center text-lg font-bold">
              {FocusState.DAY === focus ? (
                <span className="text-PrimaryLight">요일</span>
              ) : (
                <span>요일</span>
              )}
            </div>
            <div onClick={onEndClick} className="w-full text-center text-lg font-bold">
              {FocusState.END === focus ? (
                <span className="text-PrimaryLight">완결여부</span>
              ) : (
                <span>완결여부</span>
              )}
            </div>
          </div>
          {detailElement}
        </div>
        <div className="m-4 rounded-xl bg-BackgroundLightComponent p-4">
          <div className="w-full text-center text-lg font-semibold">필터링 결과</div>
          <div>
            {resWebtoons.map((webtoon: any) => (
              <WebtoonItem
                key={webtoon.id}
                id={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </div>
        </div>
        <div className="m-4 rounded-xl bg-BackgroundLightComponent p-4">
          <div className="w-full text-center text-lg font-semibold">그 외</div>
          <div>
            {elseWebtoons.map((webtoon: any) => (
              <WebtoonItem
                key={webtoon.id}
                id={webtoon.id}
                webtoonName={webtoon.name}
                imageUrl={webtoon.imagePath}
                status={webtoon.status}
              />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export const getServerSideProps: GetServerSideProps = async context => {
  const token = context.req.cookies.accessToken;

  const daysRes = await axios.get('webtoons/list/days', {
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  });
  const statusRes = await axios.get('webtoons/list/status', {
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  });
  let myRes = [defaultLikeWebtoon];
  if (token) {
    const result = await getUserLike(token);
    myRes = result?.result;
  }

  let dayProp: any[] = [];
  daysRes.data.result.map((day: any) => {
    const oneday = {
      key: day.id,
      value: day.day,
    };
    dayProp.push(oneday);
  });

  let statusProp: any[] = [];
  statusRes.data.result.map((state: any) => {
    const onestate = {
      key: state.id,
      value: state.status,
    };
    statusProp.push(onestate);
  });

  return { props: { days: dayProp, status: statusProp, myRes } };
};
