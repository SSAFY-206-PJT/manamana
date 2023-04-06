interface Props {
  id: number;
  image: any;
  name: string;
  episode: number;
  webtoonId: number;
}

import { useRouter } from 'next/router';
import axios from 'axios';
import { getCookie } from '@/util/cookie';

export default function notiItem(props: Props) {
  const router = useRouter();

  // console.log(props.webtoonId);
  // 보러가기 누르면 해당 알림은 삭제되면 API
  const deleteNoti = async (id: number) => {
    const token = getCookie('accessToken');
    const options = {
      method: 'DELETE',
      url: `/users/1/webtoon/alarms/${id}`,
      headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    };
    try {
      const res = await axios.request(options);
      return res;
    } catch (error) {
      return null;
    }
  };

  const onClickGoDetail = (id: number, webtoonId: number) => {
    deleteNoti(id);
    router.push(`/detail/${webtoonId}`);
  };

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
        onClick={() => onClickGoDetail(props.id, props.webtoonId)}
      >
        보러가기
      </div>
    </div>
  );
}
