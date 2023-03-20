import { useState } from "react";

interface Props {
    content: string;
    value: string;
    selectBlock: (value: string) => void;
    unSelectBlock: (value: string) => void;
}

export default function GenreStateBlock(props: Props) {
    const [selectStatus, setSelectStatus] = useState<boolean>(false);
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
            {
                selectStatus ?

                    <button className="w-24 h-12 rounded-md bg-SecondaryLight text-FontPrimaryDark text-center" onClick={onButtonClick}>
                        {props.content}
                    </button>
                    :
                    <button className="w-24 h-12 rounded-md bg-BackgroundLightComponent text-FontPrimaryLight text-center" onClick={onButtonClick}>
                        {props.content}
                    </button>
            }
        </div>
    )
}