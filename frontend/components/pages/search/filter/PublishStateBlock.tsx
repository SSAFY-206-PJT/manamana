import { useState } from 'react';

export interface StateData {
  id: number;
  status: string;
}

interface Props {
  value: StateData;
  status: boolean;
  selectBlock: (data: StateData) => void;
  unSelectBlock: (data: StateData) => void;
}

export default function PublishStateBlock(props: Props) {
  const [selectStatus, setSelectStatus] = useState<boolean>(props.status);
  /*
   * @Method
   * 버튼을 클릭했을 때 실행되는 메소드
   *  */
  const onButtonClick = () => {
    if (!selectStatus) {
      props.selectBlock({ id: props.value.id, status: props.value.status });
      setSelectStatus(true);
    } else {
      props.unSelectBlock({ id: props.value.id, status: props.value.status });
      setSelectStatus(false);
    }
  };

  return (
    <div className="inline-block w-full">
      {selectStatus ? (
        <button
          className="h-12 w-full rounded-md bg-SecondaryLight text-center text-FontPrimaryDark"
          onClick={onButtonClick}
        >
          {props.value.status}
        </button>
      ) : (
        <button
          className="h-12 w-full rounded-md bg-BackgroundLightComponent text-center text-FontPrimaryLight"
          onClick={onButtonClick}
        >
          {props.value.status}
        </button>
      )}
    </div>
  );
}
