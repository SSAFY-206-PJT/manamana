import { Chat } from './ChatList';
import Image from 'next/image';

interface ChatProp {
  chat: Chat;
}

function ChatListItem({ chat }: ChatProp) {
  return (
    <div className="my-2 flex max-w-[60%] items-center">
      <div>{chat.user.imagePath}</div>
      <div>
        <div className="flex max-w-full rounded bg-BackgroundLightComponent p-1.5">
          <p>{chat.content}</p>
          <div>
            <button
              className="mt-1"
              onClick={() => {
                console.log('더보기클릭함');
              }}
            >
              <Image src="/images/More.png" width={20} height={20} alt="#" />
            </button>
          </div>
        </div>
        <div>
          <p>
            {chat.id}|{chat.user.nickname} | {chat.createTime.split(' ')[0]}
          </p>
        </div>
      </div>
    </div>
  );
}

export default ChatListItem;
