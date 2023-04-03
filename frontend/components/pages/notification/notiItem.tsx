type Props = {
  imageUrl: string;
  webtoonName: string;
  lastTurn: number;
};

export default function notiItem(props: Props) {
  return (
    <div className="mx-4 my-4 flex flex-row justify-between">
      <div className="ml-3 flex">
        <img src={props.imageUrl} alt="" className="h-18 w-12"></img>
        <div className="ml-3 flex flex-col">
          <div className="font-bold">{props.webtoonName}</div>
          <div>{props.lastTurn}회가 등록되었습니다.</div>
        </div>
      </div>
      <div className="flex items-center justify-end font-bold">보러가기</div>
    </div>
  );
}
