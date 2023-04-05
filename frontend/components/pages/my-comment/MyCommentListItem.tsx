import { Avatar } from '@mui/material';
import { MyChat } from './MyCommentList';
import { useSelector } from 'react-redux';
import { RootState } from '@/store';
import MyCommentWebtoonInfo from './MyCommentWebtoonInfo';

interface ChatProp {
  chat: MyChat;
  itemInfo: any;
}

function MyCommentListItem({ chat, itemInfo }: ChatProp) {
  const user = useSelector((state: RootState) => state.isLogin);
  const myName = user.nickname;
  const userImage = user.imagePath;

  // 날짜 변환
  const timeForToday = (value: string) => {
    const today = new Date();
    const timeValue = new Date(value);
    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
    if (betweenTime < 1) return '방금전';
    if (betweenTime < 60) {
      return `${betweenTime}분전`;
    }
    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
      return `${betweenTimeHour}시간전`;
    }
    const betweenTimeDay = Math.floor(betweenTimeHour / 24);
    if (betweenTimeDay < 30) {
      return `${betweenTimeDay}일전`;
    }
    const betweenTimeMonth = Math.floor(betweenTimeDay / 30);
    if (betweenTimeMonth < 12) {
      return `${betweenTimeMonth}달전`;
    }
    return `${Math.floor(betweenTimeDay / 365)}년전`;
  };

  return (
    <div className="my-2 flex justify-end">
      <div className="max-w-[65%]">
        <MyCommentWebtoonInfo webtoonId={chat.webtoon.id} />
        <div className="flex rounded bg-BackgroundLightComponent p-1.5">
          <p className="whitespace-pre-wrap break-all">{chat.content}</p>
          <div className="ml-auto">
            <button
              className="w-4"
              onClick={() => {
                itemInfo(chat);
                // console.log('더보기클릭함', chat);
              }}
            >
              <img src="/images/More.png" className="h-5 w-5" alt="#"></img>
            </button>
          </div>
        </div>

        <div>
          <p className="text-right">
            {timeForToday(chat.createTime)}|{myName}
          </p>
        </div>
      </div>
      <div className="ml-1 flex items-center">
        <Avatar alt="Remy Sharp" src={userImage} />
      </div>
    </div>
  );
}

export default MyCommentListItem;
