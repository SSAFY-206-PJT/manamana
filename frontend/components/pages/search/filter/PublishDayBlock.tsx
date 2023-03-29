import { useState } from "react";

interface Data {
    id: number,
    day: string
}

interface Props {
    value: Data;
    status: boolean;
    selectBlock: (data: Data) => void;
    unSelectBlock: (data: Data) => void;
}

export default function PublishDayBlock(props: Props) {
    const [selectStatus, setSelectStatus] = useState<boolean>(props.status);
    /*
    * @Method
    * 버튼을 클릭했을 때 실행되는 메소드
    *  */
    const onButtonClick = () => {
        if(!selectStatus){
            props.selectBlock({id: props.value.id, day: props.value.day});
            setSelectStatus(true);
        }
        else{
            props.unSelectBlock({id: props.value.id, day: props.value.day});
            setSelectStatus(false);
        }
    }
    console.log(selectStatus)
    return (
        <div className="inline-block m-2">
            {selectStatus ?
                <button className="w-12 h-12 rounded-full bg-SecondaryLight text-FontPrimaryDark text-center" onClick={onButtonClick}>
                    {props.value.day}
                </button>
                :
                <button className="w-12 h-12 rounded-full bg-BackgroundLightComponent text-FontPrimaryLight text-center" onClick={onButtonClick}>
                    {props.value.day}
                </button>
            }

        </div>
    )
}