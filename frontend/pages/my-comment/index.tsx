import ChatList from '@/components/pages/comment/ChatList';
import Headerbar from '@/components/common/Headerbar';

export default function MyCommentPage() {
  return (
    <div>
      <div className="flex h-screen w-full flex-col">
        <Headerbar showBackBtn={true} pageName={''} rightBtn={'NOTI'} />
        <ChatList />
        <div className="m-1.5"></div>
      </div>
    </div>
  );
}
