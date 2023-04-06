import { useState, useEffect, useRef } from 'react';
import CommentListItem from './CommentListItem';
import { CircularProgress } from '@mui/material';
import CommentListModal from './CommentListModal';

export interface Chat {
  id: number;
  content: string;
  spoiler: boolean;
  report: number;
  createTime: string;
  user: {
    id: number;
    nickname: string;
    imagePath: string;
  };
}

interface CommentListProps {
  webtoonId: number;
  commentList: Chat[];
  commentEnd: boolean;
  loadComment: () => void;
  deleteComment: (chat: any, key: number) => Promise<boolean>;
  modifyComment: (
    chatId: number,
    oldComment: Chat,
    newComment: Chat,
    key: number,
  ) => Promise<boolean>;
}

function CommentList({
  webtoonId,
  commentList,
  commentEnd,
  loadComment,
  deleteComment,
  modifyComment,
}: CommentListProps) {
  const [oldScrollHeight, setOldScrollHeight] = useState<number>(0);
  const [scrollLoading, setScrollLoading] = useState<string>('no');
  const [oldScrollTop, setOldScrollTop] = useState<number>(0);
  const scrollRef = useRef<any>(null);

  // 함수
  // 스크롤 맨 아래로
  const toBottom = () => {
    scrollRef.current.scrollTop = scrollRef.current.scrollHeight - scrollRef.current.clientHeight;
  };
  // 스크롤 유지
  const scrollSame = (e: number) => {
    setTimeout(function () {
      scrollRef.current.scrollTop = e;
      setScrollLoading('no');
    }, 1000);
  };
  // 스크롤 위치 감지, 맨 위에서 로딩시작
  const scrollFn = () => {
    if (scrollRef.current.scrollTop === 0 && !commentEnd && scrollLoading === 'no') {
      setScrollLoading('add');
    }
  };

  // cycle
  // 채팅리스트 갱신되면
  // 1. 추가 로딩, 수정, 삭제의 경우엔 스크롤 유지
  // 2. 댓글 입력해서 추가했을때는 맨 아래로 스크롤
  useEffect(() => {
    if (scrollLoading !== 'no') {
      const newScrollHeight = scrollRef.current.scrollHeight;
      scrollSame(oldScrollTop + newScrollHeight - oldScrollHeight);
    } else {
      toBottom();
    }
  }, [commentList]);

  // 로딩이 시작되면 (추가 로딩, 수정, 삭제)시작
  useEffect(() => {
    setOldScrollHeight(scrollRef.current.scrollHeight);
    setOldScrollTop(scrollRef.current.scrollTop);
    if (scrollLoading === 'add') {
      loadComment();
    } else if (scrollLoading === 'del') {
      // deleteComment('ee');
    }
  }, [scrollLoading]);

  // 첫화면에서 맨 아래로 스크롤 내리기
  useEffect(() => {
    toBottom();
  }, []);

  const [selectedChat, setSelectedChat] = useState<any>();
  const [selectedKey, setSelectedKey] = useState<number>(0);
  const [openModal, setOpenModal] = useState<boolean>(false);
  const itemInfo = (chat: Chat, key: number) => {
    setSelectedChat(chat);
    setOpenModal(true);
    setSelectedKey(key);
  };

  const closeModal = () => {
    setOpenModal(false);
  };

  const modifyCommentM = (chatId: number, oldComment: Chat, newComment: Chat) => {
    return modifyComment(chatId, oldComment, newComment, selectedKey);
  };

  const deleteCommentM = (chat: any) => {
    return deleteComment(chat, selectedKey);
  };

  return (
    <div className="mx-3 max-w-full overflow-auto bg-slate-100" ref={scrollRef} onScroll={scrollFn}>
      <div className="flex justify-center">
        {scrollLoading === 'add' ? <CircularProgress /> : null}
      </div>
      <div className="m-2 flex min-h-screen max-w-full flex-col-reverse">
        {commentList?.reverse().map((item: Chat, idx) => (
          <CommentListItem chat={item} itemInfo={itemInfo} key={item.id} idx={idx} />
        ))}
      </div>
      {openModal ? (
        <CommentListModal
          webtoonId={webtoonId}
          chat={selectedChat}
          open={openModal}
          close={closeModal}
          deleteComment={deleteCommentM}
          modifyComment={modifyCommentM}
        />
      ) : null}
    </div>
  );
}

export default CommentList;
