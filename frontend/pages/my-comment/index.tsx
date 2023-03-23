import { useState } from 'react';
import ChatList from '@/components/pages/comment/ChatList';
import Headerbar from '@/components/common/Headerbar';
import { Chat } from '@/components/pages/comment/ChatList';

const dummyChatList: Chat[] = [
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
];
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

export default function MyCommentPage() {
  // 댓글 리스트
  const [commentList, setCommentList] = useState<Chat[]>(dummyChatList);
  // 더이상 로딩할 댓글이 없으면 true
  const [commentEnd, setCommentEnd] = useState<boolean>(false);

  // 댓글 로딩
  const loadComment = () => {
    console.log('댓글 로딩');
    setCommentList([...commentList, ...dummyChatList2]);
  };

  // 댓글 삭제
  const deleteComment = (ee: any) => {
    for (let i = 0; i < commentList.length; i++) {
      if (commentList[i] === ee) {
        commentList.splice(i, 1);
        break;
      }
    }
    setCommentList([...commentList]);
  };

  // 댓글 수정
  const modifyComment = (oldComment: Chat, newComment: Chat) => {
    for (let i = 0; i < commentList.length; i++) {
      if (commentList[i] === oldComment) {
        commentList[i] = newComment;
        break;
      }
    }
    setCommentList([...commentList]);
  };

  return (
    <div>
      <div className="flex h-screen w-full flex-col">
        <Headerbar showBackBtn={true} pageName={''} rightBtn={'NOTI'} />
        <ChatList
          commentList={commentList}
          commentEnd={commentEnd}
          loadComment={loadComment}
          deleteComment={deleteComment}
          modifyComment={modifyComment}
        />
        <div className="m-1.5"></div>
      </div>
    </div>
  );
}
