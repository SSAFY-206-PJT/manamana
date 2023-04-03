import { GetServerSideProps } from 'next';
import { useEffect, useState } from 'react';
import * as api from '@/pages/api/detail';
// import { useRouter } from 'next/router';
// import Image from 'next/image';

import Headerbar from '@/components/common/Headerbar';
import CommentList from '@/components/pages/comment/CommentList';
import CommentInput from '@/components/pages/comment/CommentInput';
import { Chat } from '@/components/pages/comment/CommentList';
import { CommentUserInput } from '@/components/pages/comment/CommentInput';
import { WebtoonDetail } from '@/pages/api/detail';
import { getCookie } from '@/util/cookie';

const defaultValue: CommentUserInput = {
  content: '',
  spoiler: false,
};

interface Props {
  webtoon: WebtoonDetail;
  comments: Chat[];
}

function CommentPage({ webtoon, comments }: Props) {
  if (webtoon === null || comments === null) {
    return <div>오류</div>;
  } else {
    const token = getCookie('accessToken');
    // 그라데이션 스타일
    const hsls = webtoon.colorHsl.split(',');
    const WEBTOON_THEME_COLOR = `hsl(${hsls[0]}, ${hsls[1]}%, 20%)`;
    const coverStyle = { background: WEBTOON_THEME_COLOR };

    const commentHeader = (
      <div className="flex h-16">
        <div>
          <img src={webtoon.imagePath} alt="웹툰이미지" className="h-full w-auto" />
        </div>
        <div className="ml-3 flex items-center">
          <p className="text-FontPrimaryDark">{webtoon.name}</p>
        </div>
      </div>
    );

    // 댓글 리스트
    const [commentList, setCommentList] = useState<Chat[]>(comments);
    const [commentPage, setCommentPage] = useState<number>(1);
    // 더이상 로딩할 댓글이 없으면 true
    const [commentEnd, setCommentEnd] = useState<boolean>(false);

    // 댓글 로딩
    const loadComment = async () => {
      if (!commentEnd) {
        const nextpage = commentPage + 1;
        setCommentPage(nextpage);
        const data = await api.getWebtoonComments(webtoon.id, nextpage, token);
        // console.log(data);
        if (data && data.isSuccess) {
          const addCommentList = data.result;
          setCommentList([...commentList, ...addCommentList]);
        }
      }
    };

    // 댓글 입력
    const comment = async (commentInput: CommentUserInput) => {
      const data = await api.postWebtoonComment(
        webtoon.id,
        commentInput.content,
        commentInput.spoiler,
        token,
      );
      if (data && data.isSuccess) {
        const newComment = {
          id: data.result.id,
          content: data.result.content,
          isSpoiler: data.result.spoiler,
          report: 0,
          createTime: new Date().toDateString(),
          user: {
            id: 1,
            nickname: '김태학',
            imagePath: 'url',
          },
        };
        setCommentList([newComment, ...commentList]);
        return true;
      } else {
        alert(data);
        return false;
      }
    };

    // 댓글 삭제
    const deleteComment = async (chat: any) => {
      const data = await api.deleteWebtoonComment(webtoon.id, chat.id, token);
      if (data && data.isSuccess) {
        for (let i = 0; i < commentList.length; i++) {
          if (commentList[i] === chat) {
            commentList.splice(i, 1);
          }
        }
        setCommentList([...commentList]);
        return true;
      } else {
        return false;
      }
    };

    // 댓글 수정
    const modifyComment = async (chatId: number, oldComment: Chat, newComment: Chat) => {
      const result = await api.modifyWebtoonComment(
        webtoon.id,
        chatId,
        newComment.content,
        newComment.isSpoiler,
        token,
      );
      if (result) {
        for (let i = 0; i < commentList.length; i++) {
          if (commentList[i] === oldComment) {
            commentList[i] = newComment;
            break;
          }
        }
        setCommentList([...commentList]);
        return true;
      } else {
        return false;
      }
    };

    useEffect(() => {
      console.log(commentList);
    }, [commentList]);

    return (
      <div>
        <div className="flex h-screen w-screen flex-col">
          <Headerbar showBackBtn={true} pageName={''} rightBtn={'NOTI'} />
          <div style={coverStyle} className="w-full px-3 py-1 drop-shadow-xl">
            {commentHeader}
          </div>
          <CommentList
            webtoonId={webtoon.id}
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
}
export default CommentPage;

export const getServerSideProps: GetServerSideProps = async context => {
  const { webtoon_id } = context.query;
  const token = context.req.cookies.accessToken;
  if (token) {
    const webtoonData = await api.getWebtoonDetail(webtoon_id, token);
    const commentData = await api.getWebtoonComments(webtoon_id, 0, token);
    return { props: { webtoon: webtoonData.result, comments: commentData.result } };
  } else {
    return { props: { webtoon: null, comments: null } };
  }
};