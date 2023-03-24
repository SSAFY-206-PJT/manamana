interface Props {
    status: boolean;
    unCover: () => void;
}

export default function Cover(props: Props) {

    if (props.status) {
        return (
            <div>
                <div className="flex flex-col justify-center items-center w-full h-full bg-slate-600 z-30 fixed">
                    <video src="/movies/managola.mp4" autoPlay loop muted className="h-full w-full object-cover absolute" />
                    <div className="z-40">
                        <div className="flex flex-col items-center justify-center mb-4">
                            <span className="text-2xl text-FontSecondaryLight font-semibold">웹툰 취향 테스트</span>
                            <span className="text-xl text-FontSecondaryLight font-semibold">당신을 위한 웹툰을 찾아보세요!</span>
                        </div>
                        <div className="mb-4 flex justify-center items-center">
                            <span className="text-6xl text-FontPrimaryLight font-bold">마나골라</span>
                        </div>
                        <div className="flex justify-center m-12">
                            <button
                                className="font-semibold text-2xl bg-BackgroundLightComponent p-2 rounded-lg opacity-80 mb-4 text-FontPrimaryLight drop-shadow-lg"
                                onClick={() => { props.unCover(); }}>시작하기</button>
                        </div>
                        <div className="flex flex-col items-center text-FontSecondaryLight">
                            <div>좋아하는 웹툰은 오른쪽으로</div>
                            <div>별로인 웹툰은 왼쪽으로 밀어보세요.</div>
                            <div>한번 선택하면 되돌릴 수 없어요.</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
    else{
        return null;
    }

}