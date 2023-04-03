import { useState } from "react";

interface Data {
    key: number,
    value: string
}

interface Props {
    value: Data;
    status: boolean;
    selectBlock: (data: Data) => void;
    unSelectBlock: (data: Data) => void;
}

export default function GenreStateBlock(props: Props) {
    const [selectStatus, setSelectStatus] = useState<boolean>(props.status);
    /*
    * @Method
    * 버튼을 클릭했을 때 실행되는 메소드
    *  */
    const onButtonClick = () => {
        if(!selectStatus){
            props.selectBlock({key: props.value.key, value: props.value.value});
            setSelectStatus(true);
        }
        else{
            props.unSelectBlock({key: props.value.key, value: props.value.value});
            setSelectStatus(false);
        }
    }

    return (
        <div className="inline-block m-2">
            {
                selectStatus ?

                    <button className="w-24 h-12 rounded-md bg-SecondaryLight text-FontPrimaryDark text-center" onClick={onButtonClick}>
                        {props.value.value}
                    </button>
                    :
                    <button className="w-24 h-12 rounded-md bg-BackgroundLightComponent text-FontPrimaryLight text-center" onClick={onButtonClick}>
                        {props.value.value}
                    </button>
            }
        </div>
    )
}