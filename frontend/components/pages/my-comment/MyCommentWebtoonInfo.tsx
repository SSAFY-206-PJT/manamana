import { WebtoonDetail, getWebtoonDetail } from '@/pages/api/detail';
import { getCookie } from '@/util/cookie';
import { useEffect, useState } from 'react';

interface webtoonInfoProps {
  webtoonId: number;
}
function MyCommentWebtoonInfo({ webtoonId }: webtoonInfoProps) {
  const token = getCookie('accessToken');
  const day = ['', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일', '기타'];

  const [webtoonInfo, setWebtoonInfo] = useState<WebtoonDetail | null>(null);

  const getDetail = async () => {
    const detail = await getWebtoonDetail(webtoonId, token);
    if (detail.success) {
      setWebtoonInfo(detail.result);
    }
  };

  useEffect(() => {
    getDetail();
  }, []);

  if (webtoonInfo) {
    return (
      <div className="mb-1 flex w-fit flex-row-reverse items-center">
        <div>
          <div className="text-lg">{webtoonInfo.name}</div>
          <div className="whitespace-nowrap text-sm">
            {webtoonInfo.genres[0].name}|{day[webtoonInfo.days[0].codeId]}|{webtoonInfo.status}
          </div>
        </div>
        <img
          className="mr-1 h-12 w-1/3 min-w-[50%] rounded object-cover"
          src={webtoonInfo.imagePath}
          alt="웹툰 이미지"
        />
      </div>
    );
  } else {
    return <div></div>;
  }
}

export default MyCommentWebtoonInfo;
