import { useState, useEffect, useRef } from 'react';
import ChatListItem from './ChatListItem';

export interface Chat {
  id: number;
  content: string;
  isSpoiler: boolean;
  report: number;
  createTime: string;
  user: {
    id: number;
    nickname: string;
    imagePath: string;
  };
}

function ChatList() {
  const [dummyChatList, setDummyChatList] = useState<Chat[]>([
    {
      id: 1, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 2, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 3, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 4, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-12 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 5, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-12 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 6, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-11 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 7, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-11 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 8, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-10 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 9, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-10 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 10, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-09 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
  ]);
  const dummyChatList2: Chat[] = [
    {
      id: 1, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-08 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 2, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-05 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 3, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-05 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 4, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-03-04 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 5, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-02-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 6, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2023-01-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 7, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2022-03-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 8, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2021-03-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 9, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: false, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2020-03-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
    {
      id: 10, // 댓글 식별자
      content: '이것은 댓글 내용요요용요요요요요요용', // 내용
      isSpoiler: true, // 스포 여부
      report: 0, // 신고 횟수
      createTime: '2019-03-13 11:22:33',
      user: {
        id: 1, // 유저 식별자
        nickname: '김태학', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
  ];

  const [scrollLoading, setScrollLoading] = useState<Boolean>(false);
  const [scrollY, setScrollY] = useState<Number>(346);
  const te = (e: any) => {
    setScrollY(e.target.scrollTop);
  };

  const scrollRef = useRef<any>(null);
  const refresh = () => {
    scrollRef.current.scrollIntoView({ behavior: 'smooth' });
    console.log('버튼 클릭');
  };

  useEffect(() => {
    console.log(scrollY);
  }, [scrollY]);

  return (
    <div className="h-full overflow-auto p-2" onScroll={te}>
      <div onClick={refresh}>버튼</div>
      <div className="flex flex-col-reverse" ref={scrollRef}>
        {dummyChatList.map((item: Chat) => (
          <ChatListItem chat={item} />
        ))}
      </div>
    </div>
  );
}

export default ChatList;
