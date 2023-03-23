import { useRouter } from 'next/router';
import { useState } from 'react';
import Image from 'next/image';

import Headerbar from '@/components/common/Headerbar';
import ChatList from '@/components/pages/comment/ChatList';
import CommentInput from '@/components/pages/comment/CommentInput';
import { Chat } from '@/components/pages/comment/ChatList';
import { CommentUserInput } from '@/components/pages/comment/CommentInput';

const defaultValue: CommentUserInput = {
  content: '',
  spoiler: false,
};

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

export default function CommentPage() {
  const router = useRouter();
  // 웹툰 정보 처리 방안
  // 1. detail에서 query로 수신(현재) - 주소를 깔끔하게 하기위해 query as 속성으로 바꾸면 새로고침했을 때 갱신이 안됨
  // 2. 댓글 페이지에서 api 통신 - 웹툰 detail 정보를 받아오는 api 통신을 더 해서 그만큼의 로딩? 서버에 부하? 가 걸릴거같음
  const { WEBTOON_THEME_COLOR, imagePath, name } = router.query;

  // 웹툰 헤더 style
  let coverStyle;
  if (typeof WEBTOON_THEME_COLOR === 'string') {
    coverStyle = { background: WEBTOON_THEME_COLOR };
  } else {
    coverStyle = { background: 'black' };
  }
  let imageUrl;
  if (typeof imagePath === 'string') {
    imageUrl = imagePath;
  } else {
    imageUrl = '';
  }

  const commentHeader = (
    <div className="flex h-16">
      <div>
        <Image
          className="h-full w-auto"
          src={imageUrl}
          alt="웹툰 이미지"
          width={100}
          height={200}
          priority
        />{' '}
      </div>
      <div className="ml-3 flex items-center">
        <p className="text-FontPrimaryDark">{name}</p>
      </div>
    </div>
  );

  // 댓글 리스트
  const [commentList, setCommentList] = useState<Chat[]>(dummyChatList);
  // 더이상 로딩할 댓글이 없으면 true
  const [commentEnd, setCommentEnd] = useState<boolean>(false);

  // 댓글 로딩
  const loadComment = () => {
    console.log('댓글 로딩');
    setCommentList([...commentList, ...dummyChatList2]);
  };

  // 댓글 입력
  const comment = (commentInput: CommentUserInput) => {
    // dummy
    const newComment = {
      id: 3323,
      content: commentInput.content,
      isSpoiler: commentInput.spoiler,
      report: 0,
      createTime: '2023-03-13 11:22:33',
      user: {
        id: 1,
        nickname: '김태학',
        imagePath: 'url',
      },
    };
    setCommentList([newComment, ...commentList]);
  };

  // 댓글 삭제
  const deleteComment = (ee: any) => {
    for (let i = 0; i < commentList.length; i++) {
      if (commentList[i] === ee) {
        commentList.splice(i, 1);
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
        <div style={coverStyle} className="w-full px-3 py-1 drop-shadow-xl">
          {commentHeader}
        </div>
        <ChatList
          commentList={commentList}
          commentEnd={commentEnd}
          loadComment={loadComment}
          deleteComment={deleteComment}
          modifyComment={modifyComment}
        />
        <div className="m-2">
          <CommentInput defaultValue={defaultValue} comment={comment} />
        </div>
      </div>
    </div>
  );
}
