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

  return (
    <div className="inline-block">
      <div className="mr-2 inline-block">
        <img src={imageUrl} alt="imageURL" className="h-30 mt-2 w-24"></img>
        <div className="flex flex-row h-6 text-sm">
          {status === '휴재중' ? <WebtoonBreakLabel /> : <></>}
          {status === '완결' ? <WebtoonCompleteLabel /> : <></>}
          <span>{webtoonName}</span>
        </div>
      </div>
    </div>
  );
}
