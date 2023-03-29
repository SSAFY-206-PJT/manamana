import WebtoonBreakLabel from './WebtoonBreakLabel';
import WebtoonCompleteLabel from './WebtoonCompleteLabel';
import { useRouter } from 'next/router';

type Props = {
  imageUrl: string;
  webtoonName: string;
  status: string;
  id: number;
};

export default function WebtoonItem(props: Props) {
  let imageUrl = props.imageUrl;
  let webtoonName = props.webtoonName;
  let status = props.status;
  const router = useRouter();

  const onWebtoonClick = () => {
    router.push(`/detail/${props.id}`);
  };

  return (
    <div className="inline-block" onClick={onWebtoonClick}>
      <div className="mr-2 inline-block flex flex-col">
        <img src={imageUrl} alt="imageURL" className="h-30 mt-2 w-24"></img>
        <div className="mt-1 flex h-4 flex-row items-center text-sm overflow-hidden">
          {status === '휴재중' ? <WebtoonBreakLabel /> : <></>}
          {status === '완결' ? <WebtoonCompleteLabel /> : <></>}
          <div className="flex items-center justify-center text-[16px] text-semibold">
            {/* 길이가 길면 잘라서 출력하도록 */}
            {webtoonName.length > 6 ?
              webtoonName.substring(0, 6).concat("...")
              : webtoonName
            }
          </div>
        </div>
      </div>
    </div>
  );
}
