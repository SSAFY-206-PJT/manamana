import WebtoonBreakLabel from './WebtoonBreakLabel';
import WebtoonCompleteLabel from './WebtoonCompleteLabel';
import { useRouter } from 'next/router';

type Props = {
  imageUrl: string;
  webtoonName: string;
  status: string;
  id: number;
};

export default function WebtoonItem({ id, imageUrl, webtoonName, status }: Props) {
  const router = useRouter();

  const onWebtoonClick = () => {
    if (id > 0) {
      router.push(`/detail/${id}`);
    } else {
      router.push('/search');
    }
  };

  return (
    <div className="inline-block" onClick={onWebtoonClick}>
      <div className="mr-2 flex flex-col">        
        <div className="mt-2 h-[124px] w-[96px]">
          <img src={imageUrl} alt="imageURL" className="h-full w-full"></img>
        </div>

        <div className="mt-1 flex h-4 flex-row justify-start items-center overflow-hidden text-sm">
          {status === '휴재중' ? <WebtoonBreakLabel /> : <></>}
          {status === '완결' ? <WebtoonCompleteLabel /> : <></>}
          <div className="text-semibold text-[16px]">
            {/* 웹툰제목 길이가 길면 6글자만 출력 */}
            {/* 휴재/완결 버튼 있고 웹툰제목 길이가 길면 4글자만 출력 */}
            {(status === '휴재중' || status === '완결') && webtoonName.length > 4
              ? webtoonName.substring(0, 4).concat('...')
              : webtoonName.length > 6
              ? webtoonName.substring(0, 6).concat('...')
              : webtoonName}
          </div>
        </div>
      </div>
    </div>
  );
}
