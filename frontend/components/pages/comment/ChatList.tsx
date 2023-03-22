import { useState, useEffect, useRef } from 'react';
import ChatListItem from './ChatListItem';
import { CircularProgress } from '@mui/material';

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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
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
        nickname: '싸피', // 닉네임
        imagePath: 'url', //유저 프로필
      },
    },
  ];

  const [oldScrollHeight, setOldScrollHeight] = useState<number>(0);
  const [scrollLoading, setScrollLoading] = useState<Boolean>(false);
  const [oldScrollTop, setOldScrollTop] = useState<number>(0);
  const [scrollEnd, setScrollEnd] = useState<Boolean>(false);
  const scrollRef = useRef<any>(null);

  // 함수
  // 스크롤 맨 아래로
  const toBottom = () => {
    scrollRef.current.scrollTop = scrollRef.current.scrollHeight - scrollRef.current.clientHeight;
  };
  // 새로운 댓글 추가 후 화면 스크롤 유지
  const scrollSame = (e: number) => {
    setTimeout(function () {
      scrollRef.current.scrollTop = e;
      setScrollLoading(false);
    }, 1000);
  };
  // 현재 스크롤 정보 출력(개발용)
  const printScrollInfo = () => {
    console.log('=============================');
    console.log('div 윗면 높이', scrollRef.current.scrollHeight);
    console.log('div 아랫면 높이', scrollRef.current.clientHeight);
    console.log('보이는 화면 윗면 높이', scrollRef.current.scrollTop);
    console.log('=============================');
  };
  // 스크롤 위치 감지 후 맨 위에서 로딩시작
  const scrollFn = () => {
    if (scrollRef.current.scrollTop === 0 && !scrollEnd && !scrollLoading) {
      setScrollLoading(true);
    }
  };
  // 채팅리스트 바꾸기
  const addChatList = () => {
    setOldScrollHeight(scrollRef.current.scrollHeight);
    setOldScrollTop(scrollRef.current.scrollTop);
    setDummyChatList([...dummyChatList, ...dummyChatList2]);
  };

  // cycle
  // 채팅리스트 갱신되면 스크롤 위치 유지하기
  useEffect(() => {
    const newScrollHeight = scrollRef.current.scrollHeight;
    scrollSame(oldScrollTop + newScrollHeight - oldScrollHeight);
  }, [dummyChatList]);
  // 로딩이 시작되면 채팅 리스트 바꾸기
  useEffect(() => {
    if (scrollLoading) {
      addChatList();
    }
  }, [scrollLoading]);
  // 첫화면에서 맨 아래로 스크롤 내리기
  useEffect(() => {
    toBottom();
  }, []);

  return (
    <div className="mx-3 overflow-auto bg-slate-100" ref={scrollRef} onScroll={scrollFn}>
      <div className="flex justify-center">{scrollLoading ? <CircularProgress /> : null}</div>
      <div className="m-2 flex min-h-screen flex-col-reverse">
        {dummyChatList.map((item: Chat) => (
          <ChatListItem chat={item} />
        ))}
      </div>
      {/* 아래는 개발용 버튼들 */}
      <div className="fixed top-36 right-0" onClick={toBottom}>
        맨밑으로
      </div>
      <div
        className="fixed top-56 right-0"
        onClick={() => {
          setScrollEnd(true);
        }}
      >
        로딩그만
      </div>
      <div className="fixed top-96 right-0" onClick={printScrollInfo}>
        현재정보
      </div>
    </div>
  );
}

export default ChatList;
