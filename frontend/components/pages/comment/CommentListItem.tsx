import { useState } from 'react';
import Image from 'next/image';
import { Avatar } from '@mui/material';
import { Chat } from './CommentList';

interface ChatProp {
  chat: Chat;
  itemInfo: any;
}

function CommentListItem({ chat, itemInfo }: ChatProp) {
  const userImagePath =
    'https://i.namu.wiki/i/xss2U6BFSuoMjDMssQDkkUNNvzOgpWjkTJ_pgdcRF034Qc_vlAZ6yOVI6ik1rhHBWpxovuBg5MIE55Wcf54uyLI6KplwA5lrYS5Omaa-G1MXvAawlW_QQO0gCR63K_TdrlqX75TyqynnSF89211hqg.webp';
  const myName = '김태학';

  const [isSpoiler, setIsSpoiler] = useState<boolean>(chat.isSpoiler);

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

  // const itemInfo = () => {};
  const spoilerDiv = (
    <div className="flex flex-col">
      <div className="flex justify-center">
        <p className="inline-block font-bold text-red-600">스포일러</p>
        <p className="inline-block">&nbsp;주의</p>
      </div>
      <button
        onClick={() => {
          setIsSpoiler(false);
        }}
      >
        눌러서 보기
      </button>
    </div>
  );

  if (chat.user.nickname === myName) {
    return (
      <div className="my-2 flex justify-end">
        <div className="max-w-[65%]">
          <div className="flex rounded bg-BackgroundLightComponent p-1.5">
            <p className="whitespace-pre-wrap break-all">{chat.content}</p>
            <div className="ml-auto">
              <button
                className="w-4"
                onClick={() => {
                  itemInfo(chat);
                  console.log('더보기클릭함');
                }}
              >
                <Image src="/images/More.png" width={20} height={20} alt="#" />
              </button>
            </div>
          </div>

          <div>
            <p className="text-right">
              {timeForToday(chat.createTime)}|{chat.user.nickname}
            </p>
          </div>
        </div>
        <div className="ml-1 flex items-center">
          <Avatar alt="Remy Sharp" src={userImagePath} />
        </div>
      </div>
    );
  } else {
    return (
      <div className="mjustify-end my-2 flex">
        <div className="mr-1 flex items-center">
          <Avatar alt="Remy Sharp" src={userImagePath} />
        </div>
        <div className="max-w-[65%]">
          <div className="relative flex rounded bg-BackgroundLightComponent p-1.5">
            <p
              className={
                isSpoiler
                  ? 'whitespace-pre-wrap break-all blur-sm'
                  : 'whitespace-pre-wrap break-all'
              }
            >
              {chat.content}
            </p>
            <div className="ml-auto">
              <button
                className="w-4"
                onClick={() => {
                  itemInfo(chat);
                  console.log('더보기클릭함');
                }}
              >
                <Image src="/images/More.png" width={20} height={20} alt="#" />
              </button>
            </div>
            <div className="absolute w-full">{isSpoiler ? spoilerDiv : null}</div>
          </div>
          <div>
            <p>
              {chat.user.nickname}|{timeForToday(chat.createTime)}
            </p>
          </div>
        </div>
      </div>
    );
  }
}

export default CommentListItem;
