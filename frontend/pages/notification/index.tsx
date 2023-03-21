import Headerbar from '../../components/common/Headerbar';
import { useState, useEffect } from 'react';

export default function NotificationPage() {
  const [dates, setDates] = useState<string[]>([]); // 이번 주의 날짜
  const [year, setYear] = useState<number>(0); // 상단에 표시할 년도
  const [month, setMonth] = useState<number>(0); // 상단에 표시할 월
  const [date, setDate] = useState<number>(0); // 알림을 표시할 날짜 (기본값: 오늘)

  const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토']; // 요일 이름 배열

  // 오늘의 날짜를 받아오는 작업을 최초 한 번만 수행하기 위해 useEffect 사용
  useEffect(() => {
    const today = new Date(); // 현재 시간을 나타내는 Date 객체 생성
    const year = today.getFullYear(); // 년도 가져오기
    const month = today.getMonth() + 1; // 월 가져오기 (0부터 시작하므로 +1을 해줌)
    const date = today.getDate(); // 일 가져오기

    const firstDay = new Date(today.setDate(today.getDate() - today.getDay())); // 이번 주의 첫 번째 날짜 계산
    const lastDay = new Date(today.setDate(today.getDate() - today.getDay() + 6)); // 이번 주의 마지막 날짜 계산
    const dates = []; // 이번 주의 날짜 리스트를 담을 배열

    for (let i = firstDay; i <= lastDay; i.setDate(i.getDate() + 1)) {
      const date = new Date(i); // Date 객체 복사
      dates.push(`${date.getDate()}`);
    }

    setYear(year);
    setMonth(month);
    setDates(dates);
    setDate(date);
  }, []);

  const onButtonClick = (notiDate: number) => {
    setDate(notiDate);
  };

  return (
    <div>
      <Headerbar showBackBtn={true} pageName={'알림'} />
      <div className="mt-2 flex justify-center text-lg font-bold">
        {year}년 {month}월
      </div>
      <div className="my-4">
        <div className="mx-12 flex justify-between">
          {daysOfWeek.map(day => (
            <span className="flex w-6 justify-center" key={day}>
              {day}
            </span>
          ))}
        </div>
        {/* 이번 주 일~토 날짜 표시, 클릭한 날짜 표시 */}
        <div className="mx-12 flex justify-between">
          {dates.map(notidate => {
            if (Number(notidate) === date) {
              return (
                <span
                  className="flex h-6 w-6 items-center justify-center rounded-full bg-PrimaryLight text-white"
                  key={notidate}
                  onClick={() => {
                    onButtonClick(Number(notidate));
                  }}
                >
                  {notidate}
                </span>
              );
            } else {
              return (
                <span
                  className="flex h-6 w-6 items-center justify-center"
                  key={notidate}
                  onClick={() => {
                    onButtonClick(Number(notidate));
                  }}
                >
                  {notidate}
                </span>
              );
            }
          })}
        </div>
      </div>
      <hr></hr>
    </div>
  );
}
