import { useState } from 'react';

import { TextField, Switch } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: 'rgba(190, 53, 85, 1)',
    },
  },
});

export interface CommentUserInput {
  content: string;
  spoiler: boolean;
}

interface CommentInputProps {
  defaultValue: CommentUserInput;
  comment: (commentInput: CommentUserInput) => Promise<boolean>;
}

function CommentInput({ defaultValue, comment }: CommentInputProps) {
  const [commentInput, setCommentInput] = useState<string>(defaultValue.content);
  const [spoilerInput, setSpoilerInput] = useState<boolean>(defaultValue.spoiler);

  const commentPost = async () => {
    console.log(commentInput, spoilerInput);
    const result = await comment({ content: commentInput, spoiler: spoilerInput });
    if (result) {
      setCommentInput('');
      setSpoilerInput(false);
    }
  };

  return (
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
            maxRows={5}
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
}

export default CommentInput;
