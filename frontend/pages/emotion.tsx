// Emotion 예시
import { css } from '@emotion/react';

function EmotionTest() {
  const color = '#FFFFFF';
  return (
    <div
      css={css`
        padding: 32px;
        background-color: hotpink;
        font-size: 24px;
        border-radius: 4px;
        &:hover {
          color: ${color};
        }
      `}
    >
      Hover to change color.
    </div>
  );
}

export default EmotionTest;
