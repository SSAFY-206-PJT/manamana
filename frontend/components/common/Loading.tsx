import Lottie from "react-lottie-player";
import LoadingImg from "../../public/lottie/99680-3-dots-loading.json"

interface Props {
    status: boolean
}

export default function Loading(props: Props) {
    if(props.status){   // true일 경우 Loading 화면 출력
        return (
            <div className="w-full h-full z-30 bg-BackgroundLightComponent opacity-60 fixed">
                <Lottie
                loop
                play
                animationData={LoadingImg}
                />
            </div>
        )
    }
    else{   // false일 경우 null을 반환
        return null;
    }
}