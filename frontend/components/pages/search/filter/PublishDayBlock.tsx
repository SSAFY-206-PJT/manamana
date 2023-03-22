import { useState } from "react";

interface Props {
    value: string;
    status: boolean;
    key: string;
    selectBlock: (value: string) => void;
    unSelectBlock: (value: string) => void;
}

export default function PublishDayBlock(props: Props) {
    const [selectStatus, setSelectStatus] = useState<boolean>(props.status);
    /*
    * @Method
    * 버튼을 클릭했을 때 실행되는 메소드
    *  */
    const onButtonClick = () => {
        if(!selectStatus){
            props.selectBlock(props.value);
            setSelectStatus(true);
        }
        else{
            props.unSelectBlock(props.value);
            setSelectStatus(false);
        }
    }

    return (
        <div className="inline-block m-2">
            {selectStatus ?
                <button className="w-12 h-12 rounded-full bg-SecondaryLight text-FontPrimaryDark text-center" onClick={onButtonClick}>
                    {props.value}
                </button>
                :
                <button className="w-12 h-12 rounded-full bg-BackgroundLightComponent text-FontPrimaryLight text-center" onClick={onButtonClick}>
                    {props.value}
                </button>
            }

        </div>
    )
}