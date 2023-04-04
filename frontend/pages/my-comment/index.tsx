import { useState } from 'react';
import { GetServerSideProps } from 'next';
import Headerbar from '@/components/common/Headerbar';
import MyCommentList from '@/components/pages/my-comment/MyCommentList';
import { MyChat } from '@/components/pages/my-comment/MyCommentList';
import axios from 'axios';

interface Props {
  myComments: MyChat[];
}

function MyCommentPage({ myComments }: Props) {
  console.log(myComments);
  // 댓글 리스트
  const [commentList, setCommentList] = useState<MyChat[]>(myComments);
  // 더이상 로딩할 댓글이 없으면 true
  const [commentEnd, setCommentEnd] = useState<boolean>(false);

  // 댓글 로딩
  const loadComment = () => {
    setCommentList([...commentList]);
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
  const modifyComment = async (oldComment: MyChat, newComment: MyChat) => {
    for (let i = 0; i < commentList.length; i++) {
      if (commentList[i] === oldComment) {
        commentList[i] = newComment;
        break;
      }
    }
    setCommentList([...commentList]);
    return true;
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
export default MyCommentPage;
export const getServerSideProps: GetServerSideProps = async context => {
  const token = context.req.cookies.accessToken;

  const commentRes = await axios.get('https://j8b206.p.ssafy.io/api/users/1/comments', {
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  });
  const myComments = commentRes.data.result;

  return { props: { myComments } };
};
