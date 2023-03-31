import Image from 'next/image';
import { ChangeEvent, useState } from 'react';

interface Props {
  onSearchBarChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

export default function SearchBar(props: Props) {
  const [searchContent, setSearchContent] = useState<string>('');

  /*
   * @Method
   * 입력이 변경됐을 경우 실행되는 메소드
   * */
  const onChangeSearchContent = (event: ChangeEvent<HTMLInputElement>) => {
    setSearchContent(event.target.value);
    props.onSearchBarChange(event);
  };

  const onClickDeleteSearchContent = (event: any) => {
    setSearchContent('');
  };

  return (
    <div className="mb-4 flex flex-row rounded-2xl border-2 border-BackgroundLightComponentBorder pl-4 pr-4 pt-1 pb-1">
      <div className="flex items-center justify-center">
        <img src={'/images/search-img.svg'} alt="search bar logo" className="h-5 w-5"></img>
      </div>
      <input
        className="ml-4 w-full outline-none"
        placeholder="웹툰 검색"
        onChange={onChangeSearchContent}
        value={searchContent}
      />
      {searchContent.length > 0 ? (
        <div
          className="top-1 flex items-center justify-center"
          onClick={onClickDeleteSearchContent}
        >
          <img
            alt="delete content"
            src={'/images/Delete_Gray_Circle.png'}
            className="h-5 w-5"
          ></img>
        </div>
      ) : (
        <div></div>
      )}
    </div>
  );
}
