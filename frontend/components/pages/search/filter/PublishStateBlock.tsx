import { useState } from "react";

interface Props {
    value: string;
    status: boolean;
    key: string;
    selectBlock: (value: string) => void;
    unSelectBlock: (value: string) => void;
}

export default function PublishStateBlock(props: Props) {
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
        <div className="inline-block w-full">
            {
                selectStatus ?
                    <button className="w-full h-12 rounded-md bg-SecondaryLight text-FontPrimaryDark text-center" onClick={onButtonClick}>
                        {props.value}
                    </button>
                    :
                    <button className="w-full h-12 rounded-md bg-BackgroundLightComponent text-FontPrimaryLight text-center" onClick={onButtonClick}>
                        {props.value}
                    </button>
            }
        </div>
    )
}