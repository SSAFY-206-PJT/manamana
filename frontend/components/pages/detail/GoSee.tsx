import Link from 'next/link';

interface Props {
  webtoonProvider: string;
}

function GoSee({ webtoonProvider }: Props) {
  return (
    <div className="absolute bottom-1/2 right-1/2 translate-x-1/2 translate-y-1/2 rounded bg-white p-4">
      <p className="mb-2 whitespace-nowrap">클릭시 해당 플랫폼으로 이동 합니다</p>
      <div className="flex justify-evenly">
        <div className="inline-block">
          <Link href={webtoonProvider} target="_blank">
            <img className="h-12 w-12" src="/images/Naver_Webtoon_Logo.png"></img>
            <button className="text-sm font-bold">보러가기</button>
          </Link>
        </div>
        {/* <div className="inline-block">
          <Link href={webtoonProvider} target="_blank">
            <img className="h-12 w-12" src="/images/Kakao_Webtoon_Logo.png"></img>
            <button className="text-sm font-bold">보러가기</button>
          </Link>
        </div>
        <div className="inline-block">
          <Link href={webtoonProvider} target="_blank">
            <img className="h-12 w-12" src="/images/Kakao_Page_Logo.png"></img>
            <button className="text-sm font-bold">보러가기</button>
          </Link>
        </div> */}
      </div>
    </div>
  );
}

export default GoSee;
