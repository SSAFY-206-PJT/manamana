import { useRouter } from 'next/router';
import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../store/index';
import Headerbar from '../../components/common/Headerbar';
import ConfirmBtn from '../../components/confirmBtn';
import { changeGenreTaste } from '@/store/GenreTasteSlice';

export default function GenreTastePage() {
  const router = useRouter();
  const dispatch = useDispatch();
  const genreTastes = useSelector((state: RootState) => state.genreTasteList);

  const [selectedBlocks, setSelectedBlocks] = useState<string[]>([]);

  const genres = [
    {
      value: '개그',
    },
    {
      value: '공포스릴러',
    },
    {
      value: '드라마',
    },
    {
      value: '로맨스',
    },
    {
      value: '스포츠',
    },
    {
      value: '액션',
    },
    {
      value: '옴니버스',
    },
    {
      value: '일상',
    },
    {
      value: '무협',
    },
    {
      value: '판타지',
    },
    {
      value: 'SF',
    },
    {
      
      value: 'BL',
    },
  ];

  const selectBlock = (value: string) => {
    // selectedBlocks에 value가 이미 있으면 제외하고, 없으면 추가
    setSelectedBlocks(selectblocks =>
      selectblocks.includes(value)
        ? selectblocks.filter(block => block !== value)
        : [...selectblocks, value],
    );
  };

  const onConfirmClick = () => {
    // 지금까지 선택된 selectedBlocks 데이터를 보낸다.
    dispatch(changeGenreTaste(selectedBlocks));
    // search로 이동한다.
    router.replace(
      {
        pathname: '/',
      },
      '/',
    );
  };

  const onCancelClick = () => {
    router.back();
  };

  useEffect(() => {
    setSelectedBlocks([...genreTastes.genreTasteList]);
  }, []);

  return (
    <div className="flex h-full w-full flex-col gap-4 bg-BackgroundLight">
      <Headerbar showBackBtn={true} pageName={'취향선택'} />
      <div className="flex justify-center">선호하는 장르를 선택해주세요</div>
      <div className="text-center">
        {genres.map(genre => {
          let status: boolean = false;
          return (
            // 클릭된 상태의 버튼 UI
            <div key={genre.value} className="m-2 inline-block h-24 w-24">
              {selectedBlocks.includes(genre.value) ? (
                <button
                  className="text-bold flex h-full w-full items-center justify-center rounded-xl bg-PrimaryLight text-xl text-FontPrimaryDark"
                  onClick={() => selectBlock(genre.value)}
                >
                  {genre.value}
                </button>
              ) : (
                <button
                  className="text-bold flex h-full w-full items-center justify-center rounded-xl bg-BackgroundLightComponent text-xl"
                  onClick={() => selectBlock(genre.value)}
                >
                  {genre.value}
                </button>
              )}
            </div>
          );
        })}
      </div>
      <ConfirmBtn cancel={onCancelClick} confirm={onConfirmClick} />
    </div>
  );
}
