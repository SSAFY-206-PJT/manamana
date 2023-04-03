import { getWebtoonProviders } from '@/pages/api/detail';
import { getCookie } from '@/util/cookie';
import Link from 'next/link';
import { useEffect, useState } from 'react';

interface Props {
  webtoonId: number;
}

function GoSee({ webtoonId }: Props) {
  const token = getCookie('accessToken');
  const [provider, setProvider] = useState<any>(null);

  const getProviders = async () => {
    const res = await getWebtoonProviders(webtoonId, token);
    console.log(res);
    if (res.result) {
      setProvider(res.result);
    }
  };

  useEffect(() => {
    getProviders();
  }, []);

  if (!provider) {
    return <div>로딩중</div>;
  }
  return (
    <div className="absolute bottom-1/2 right-1/2 translate-x-1/2 translate-y-1/2 rounded bg-white p-4">
      <p className="mb-2 whitespace-nowrap">클릭시 해당 플랫폼으로 이동 합니다</p>
      <div className="flex justify-evenly">
        <div className="inline-block">
          <Link href={provider.url} target="_blank">
            <img className="mx-auto h-12 w-12" src={provider.provider_image}></img>
            <div className="mx-auto text-sm font-bold">{provider.name}</div>
            <div className="mx-auto w-fit text-sm font-bold">보러가기</div>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default GoSee;
