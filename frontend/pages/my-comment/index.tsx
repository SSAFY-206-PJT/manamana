import { useState } from 'react';
import Headerbar from '@/components/common/Headerbar';
import MyCommentList from '@/components/pages/my-comment/MyCommentList';
import { MyChat } from '@/components/pages/my-comment/MyCommentList';

const dummyChatList = () => {
  let result: MyChat[] = new Array();
  for (let i = 0; i < 10; i++) {
    const dummyMyChat: MyChat = {
      id: i,
      content: '내 댓글 내용임 ㅎㅎ 옆에 이미지 추가해야함 화이팅',
      createTime: '2023-03-13 11:22:33',
      isSpoiler: false,
      webtoons: {
        id: 1,
        name: '1초',
        imagePath:
          'https://i.namu.wiki/i/0FPGuCn5XVDyejAOiSHqb_45uo-E4kwWkZQzS6YMYEwv4hHTPBNqTxD311G9nRYF9hsSkGh1IKVHsXcGUlXd_a-gEbRGbc0-3rWFQVian9aGOfj0NDrX4-qV5mRkMrEktPSaCH6_FjuIDatrhZnnGQ.webp',
      },
    };
    result.push(dummyMyChat);
  }
  return result;
};
const dummyChatList2 = () => {
  let result2: MyChat[] = new Array();
  for (let i = 10; i < 20; i++) {
    const dummyMyChat: MyChat = {
      id: i,
      content: '내 댓글 내용임 ㅎㅎ 옆에 이미지 추가해야함 화이팅',
      createTime: '2023-03-13 11:22:33',
      isSpoiler: false,
      webtoons: {
        id: 1,
        name: '1초',
        imagePath:
          'https://i.namu.wiki/i/0FPGuCn5XVDyejAOiSHqb_45uo-E4kwWkZQzS6YMYEwv4hHTPBNqTxD311G9nRYF9hsSkGh1IKVHsXcGUlXd_a-gEbRGbc0-3rWFQVian9aGOfj0NDrX4-qV5mRkMrEktPSaCH6_FjuIDatrhZnnGQ.webp',
      },
    };
    result2.push(dummyMyChat);
  }
  return result2;
};

export default function MyCommentPage() {
  // 댓글 리스트
  const [commentList, setCommentList] = useState<MyChat[]>(dummyChatList());
  // 더이상 로딩할 댓글이 없으면 true
  const [commentEnd, setCommentEnd] = useState<boolean>(false);

  // 댓글 로딩
  const loadComment = () => {
    console.log('댓글 로딩');
    setCommentList([...commentList, ...dummyChatList2()]);
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
  const modifyComment = (oldComment: MyChat, newComment: MyChat) => {
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
        <Headerbar showBackBtn={true} pageName={'내 댓글'} rightBtn={'NOTI'} />
        <MyCommentList
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
