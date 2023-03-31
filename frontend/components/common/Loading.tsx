import Lottie from "react-lottie-player";
import LoadingImg from "../../public/lottie/loading.json"

interface Props {
    status: boolean
}

export default function Loading(props: Props) {
    if(props.status){   // true일 경우 Loading 화면 출력
        return (
            <div className="w-full h-full z-30 flex flex-col justify-center items-center bg-BackgroundLightComponent opacity-80 fixed">
                <Lottie
                loop
                play
                animationData={LoadingImg}
                />
                <div>잠시만 기다려주세요...</div>
            </div>
        )
    }
    else{   // false일 경우 null을 반환
        return null;
    }
}