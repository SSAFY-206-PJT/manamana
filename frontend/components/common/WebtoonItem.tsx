import WebtoonBreakLabel from './WebtoonBreakLabel';
import WebtoonCompleteLabel from './WebtoonCompleteLabel';

type Props = {
  imageUrl: string;
  webtoonName: string;
  status: string;
};

export default function WebtoonItem(props: Props) {
  let imageUrl = props.imageUrl;
  let webtoonName = props.webtoonName;
  let status = props.status;

  const onWebtoonClick = () => {
    
  };

  return (
    <div className="inline-block" onClick={onWebtoonClick}>
      <div className="mr-2 inline-block flex flex-col">
        <img src={imageUrl} alt="imageURL" className="h-30 mt-2 w-24"></img>
        <div className="mt-1 flex h-4 flex-row items-center text-sm">
          {status === '휴재중' ? <WebtoonBreakLabel /> : <></>}
          {status === '완결' ? <WebtoonCompleteLabel /> : <></>}
          <div className="flex items-center justify-center text-[16px] text-semibold">{webtoonName}</div>
        </div>
      </div>
    </div>
  );
}
