import { useState } from 'react';
import SwipeableDrawer from '@mui/material/SwipeableDrawer';
import CommentInput from './CommentInput';
import { Chat } from './CommentList';
import { CommentUserInput } from './CommentInput';
import { reportWebtoonComment } from '@/pages/api/detail';
import { getCookie } from '@/util/cookie';
import { useSelector } from 'react-redux';
import { RootState } from '@/store';

interface ChatListModalProps {
  webtoonId: number;
  chat: Chat;
  open: boolean;
  key: number;
  close: () => void;
  deleteComment: (chat: any) => Promise<boolean>;
  modifyComment: (
    chatId: number,
    oldComment: Chat,
    newComment: Chat,
    key: number,
  ) => Promise<boolean>;
}

function CommentListModal({
  webtoonId,
  chat,
  open,
  key,
  close,
  deleteComment,
  modifyComment,
}: ChatListModalProps) {
  const token = getCookie('accessToken');

  const user = useSelector((state: RootState) => state.isLogin);
  const myName = user.nickname;
  const [modalState, setModalState] = useState<string>('init');

  const openModal = () => {};
  const closeModal = () => {
    close();
    setModalState('init');
  };

  const changeModalState = (e: string) => {
    setModalState(e);
  };
  const deleteChat = async () => {
    const result = await deleteComment(chat);
    closeModal();
  };
  const modify = async (e: CommentUserInput) => {
    const oldComment = chat;
    const newComment = {
      id: chat.id,
      content: e.content,
      isSpoiler: e.spoiler,
      report: chat.report,
      createTime: chat.createTime,
      user: {
        id: chat.user.id,
        nickname: chat.user.nickname,
        imagePath: chat.user.imagePath,
      },
    };
    const result = await modifyComment(chat.id, oldComment, newComment, key);
    if (result) {
      closeModal();
      return true;
    } else {
      return false;
    }
  };

  const [reportMessage, setReportMessage] = useState<string>('');
  const reportChat = async () => {
    const data = await reportWebtoonComment(webtoonId, chat.id, token);
    if (data && data.isSuccess) {
      setReportMessage('신고가 접수되었습니다.');
      setModalState('afterReport');
    } else {
      setReportMessage('이미 신고한 웹툰입니다.');
      setModalState('afterReport');
    }
  };

  const popupDelete = (
    <div className="flex justify-center">
      <div className="my-6 flex w-2/3 flex-col items-center justify-center">
        <p className="text-center text-2xl text-PrimaryLight">이 댓글을 정말 삭제하시겠습니까?</p>
        <hr className="my-2 w-full border border-PrimaryLight bg-PrimaryLight" />
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={deleteChat}
        >
          <p className="text-center">삭제하기</p>
        </button>
      </div>
    </div>
  );

  const popupModify = (
    <div className="flex justify-center">
      <div className="my-6 flex w-[80%] flex-col items-center justify-center">
        <p className="text-center text-2xl text-PrimaryLight">리뷰 수정</p>
        <hr className="my-2 w-full border border-PrimaryLight bg-PrimaryLight" />
        <CommentInput
          defaultValue={{ content: chat.content, spoiler: chat.isSpoiler }}
          comment={modify}
        />
      </div>
    </div>
  );

  const popupReport = (
    <div className="flex justify-center">
      <div className="my-6 flex w-2/3 flex-col items-center justify-center">
        <p className="text-center text-2xl text-PrimaryLight">이 댓글을 정말 신고하시겠습니까?</p>
        <hr className="my-2 w-full border border-PrimaryLight bg-PrimaryLight" />
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={reportChat}
        >
          <p className="text-center">신고하기</p>
        </button>
      </div>
    </div>
  );

  const afterReport = (
    <div className="flex justify-center">
      <div className="my-6 flex w-2/3 flex-col items-center justify-center">
        <p className="text-center text-2xl text-PrimaryLight">{reportMessage}</p>
      </div>
    </div>
  );

  const popupListForUser = (
    <div className="flex justify-center">
      <div className="my-6 flex w-2/3 flex-col items-center justify-center">
        <p className="text-center text-2xl text-PrimaryLight">댓글 설정</p>
        <hr className="my-2 w-full border border-PrimaryLight bg-PrimaryLight" />
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={() => {
            changeModalState('del');
          }}
        >
          <p className="text-center">삭제하기</p>
        </button>
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={() => {
            changeModalState('modify');
          }}
        >
          <p className="text-center">수정하기</p>
        </button>
      </div>
    </div>
  );

  const popupListForElse = (
    <div className="flex justify-center">
      <div className="my-6 flex w-2/3 flex-col items-center justify-center">
        <p className="text-center text-2xl text-PrimaryLight">댓글 설정</p>
        <hr className="my-2 w-full border border-PrimaryLight bg-PrimaryLight" />
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={() => {
            changeModalState('report');
          }}
        >
          <p className="text-center">신고하기</p>
        </button>
      </div>
    </div>
  );

  const popupForUser = () => {
    if (modalState === 'init') {
      return popupListForUser;
    } else if (modalState === 'del') {
      return popupDelete;
    } else if (modalState === 'modify') {
      return popupModify;
    }
  };
  const popupForElse = () => {
    if (modalState === 'init') {
      return popupListForElse;
    } else if (modalState === 'report') {
      return popupReport;
    } else if (modalState === 'afterReport') {
      return afterReport;
    }
  };

  return (
    <SwipeableDrawer anchor={'bottom'} open={open} onOpen={openModal} onClose={closeModal}>
      {chat?.user.nickname === myName ? popupForUser() : popupForElse()}
    </SwipeableDrawer>
  );
}

export default CommentListModal;
