import ChatList from '@/components/pages/comment/ChatList';

export default function ChatTest() {
  return (
    <div className="h-screen">
      <div className="flex justify-center">댓글 테스트 하는 곳</div>
      <div className="m-3 h-3/4 border border-PrimaryLight bg-slate-200">
        <ChatList />
      </div>
    </div>
  );
}
