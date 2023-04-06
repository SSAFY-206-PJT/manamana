interface Props {
  key: number;
  image: any;
  name: string;
  episode: number;
  webtoonId: number;
}
import { useRouter } from 'next/router';
export default function notiItem(props: Props) {
  const router = useRouter();
  console.log('props', props);
  return (
    <div className="mx-4 my-4 flex flex-row justify-between">
      <div className="ml-3 flex">
        <img src={props.image} alt="" className="h-18 w-12"></img>
        <div className="ml-3 flex flex-col">
          <div className="font-bold">{props.name}</div>
          <div>{props.episode}회가 등록되었습니다.</div>
        </div>
      </div>
      <div
        className="flex items-center justify-end font-bold"
        onClick={() => {
          router.push(`/detail/${props.webtoonId}`);
        }}
      >
        보러가기
      </div>
    </div>
  );
}
