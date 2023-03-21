import { useRouter } from 'next/router';
import Image from 'next/image';
import { useState } from 'react';

import { TextField, Switch } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

import Headerbar from '@/components/common/Headerbar';
import ChatList from '@/components/pages/comment/ChatList';

const theme = createTheme({
  palette: {
    primary: {
      // Purple and green play nicely together.
      main: 'rgba(190, 52, 85, 1)',
    },
  },
});

export default function CommentPage() {
  const router = useRouter();
  const { webtoon_id, WEBTOON_THEME_COLOR, imagePath, name } = router.query;

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

  const webtoonHeader = (
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

  //
  const [commentInput, setCommentInput] = useState<string>('');
  const [spoilerInput, setSpoilerInput] = useState<boolean>(false);
  const commentPost = () => {
    console.log(commentInput, spoilerInput);

    // 댓글 작성 성공하면 빈칸으로
    setCommentInput('');
    setSpoilerInput(false);
  };

  const commentInputDiv = (
    <div className="h-fit rounded border border-PrimaryLight bg-BackgroundLightComponent">
      <div className="m-1 h-fit">
        <ThemeProvider theme={theme}>
          <TextField
            fullWidth
            id="standard-multiline-flexible"
            color="primary"
            multiline
            value={commentInput}
            onChange={e => {
              setCommentInput(e.target.value);
            }}
            maxRows={4}
            variant="standard"
          />
        </ThemeProvider>
      </div>
      <div className="flex items-center justify-between">
        <div className="ml-1 flex">
          <div className="flex items-center">
            <p className="text-gray-400">스포일러가 포함</p>
          </div>
          <Switch
            checked={spoilerInput}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
              setSpoilerInput(e.target.checked);
            }}
          />
        </div>
        <div className="flex items-center">
          <button
            className="m-1 rounded-full bg-PrimaryLight py-1 px-4 font-semibold text-FontPrimaryDark"
            onClick={commentPost}
          >
            등록
          </button>
        </div>
      </div>
    </div>
  );

  return (
    <div className="h-[98vh]">
      <div className="absolute flex h-[98vh] w-full flex-col justify-between">
        <div>
          <Headerbar showBackBtn={true} pageName={''} rightBtn={'NOTI'} />
          <div style={coverStyle} className="w-full px-3 py-1 drop-shadow-xl">
            {webtoonHeader}
          </div>
        </div>
        <div className="m-2">{commentInputDiv}</div>
      </div>
      <div className="bottom-0 m-3 h-5/6 bg-slate-200">
        <ChatList />
      </div>
    </div>
  );
}
