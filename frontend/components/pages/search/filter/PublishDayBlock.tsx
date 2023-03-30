import { useState } from 'react';

export interface DayData {
  id: number;
  day: string;
}

interface Props {
  value: DayData;
  status: boolean;
  selectBlock: (data: DayData) => void;
  unSelectBlock: (data: DayData) => void;
}

export default function PublishDayBlock(props: Props) {
  const [selectStatus, setSelectStatus] = useState<boolean>(props.status);
  /*
   * @Method
   * 버튼을 클릭했을 때 실행되는 메소드
   *  */
  const onButtonClick = () => {
    if (!selectStatus) {
      props.selectBlock({ id: props.value.id, day: props.value.day });
      setSelectStatus(true);
    } else {
      props.unSelectBlock({ id: props.value.id, day: props.value.day });
      setSelectStatus(false);
    }
  };
  console.log(selectStatus);
  return (
    <div className="m-2 inline-block">
      {selectStatus ? (
        <button
          className="h-12 w-12 rounded-full bg-SecondaryLight text-center text-FontPrimaryDark"
          onClick={onButtonClick}
        >
          {props.value.day}
        </button>
      ) : (
        <button
          className="h-12 w-12 rounded-full bg-BackgroundLightComponent text-center text-FontPrimaryLight"
          onClick={onButtonClick}
        >
          {props.value.day}
        </button>
      )}
    </div>
  );
}
