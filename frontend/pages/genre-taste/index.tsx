import { useRouter } from 'next/router';
import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../store/index';
import Headerbar from '../../components/common/Headerbar';
import ConfirmBtn from '../../components/confirmBtn';
import { changeGenreTaste } from '@/store/GenreTasteSlice';
import { GetServerSideProps } from 'next';
import axios from 'axios';
import { getCookie } from '@/util/cookie';
import Swal from 'sweetalert2';

type Genres = {
  id: number;
  name: string;
};

export default function GenreTastePage({ genreLists }: any) {
  const router = useRouter();
  const dispatch = useDispatch();
  const genreTastes = useSelector((state: RootState) => state.genreTasteList);

  const [selectedBlocks, setSelectedBlocks] = useState<number[]>([]);
  const genres: Genres[] = genreLists;

  const selectBlock = (id: number) => {
    // selectedBlocks에 id가 이미 있으면 제외하고, 없으면 추가
    setSelectedBlocks(selectedBlocks =>
      selectedBlocks.includes(id)
        ? selectedBlocks.filter(block => block !== id)
        : [...selectedBlocks, id],
    );
  };

  const onConfirmClick = () => {
    axiosPost();
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

  // 토큰 가져오기
  const token = getCookie('accessToken');
  // 선택된 선호 장르 POST  if (token) {
  const axiosPost = () => {
    // 임의 데이터 (추후에 선택된 데이터를 담는 로직도 필요함)
    const data = selectedBlocks;
    axios
      // user_id 가져오는 로직도 짜야함
      .post(`https://j8b206.p.ssafy.io/api/users/1/genre/select`, data, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + token,
        },
      })
      .then(response => {
        Swal.fire({
          title: '선호 장르가 변경되었습니다.',
          icon: 'success',
        });
      })
      .catch(error => {
        // console.error(error);
      });
  };

  return (
    <div className="flex h-full w-full flex-col gap-4 bg-BackgroundLight">
      <Headerbar showBackBtn={true} pageName={'취향선택'} />
      <div className="flex justify-center">선호하는 장르를 선택해주세요</div>
      <div className="text-center">
        {genres.map(genre => {
          let status: boolean = false;
          return (
            // 클릭된 상태의 버튼 UI
            <div key={genre.id} className="m-2 inline-block h-24 w-24">
              {selectedBlocks.includes(genre.id) ? (
                <button
                  className="text-bold flex h-full w-full items-center justify-center rounded-xl bg-PrimaryLight text-xl text-FontPrimaryDark"
                  onClick={() => selectBlock(genre.id)}
                >
                  {genre.name}
                </button>
              ) : (
                <button
                  className="text-bold flex h-full w-full items-center justify-center rounded-xl bg-BackgroundLightComponent text-xl"
                  onClick={() => selectBlock(genre.id)}
                >
                  {genre.name}
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

export const getServerSideProps: GetServerSideProps = async context => {
  const token = context.req.cookies.accessToken;
  try {
    const response = await axios.get('https://j8b206.p.ssafy.io/api/webtoons/list/genres', {
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + token,
      },
    });
    const genreLists: Genres[] = response.data.result;
    return {
      props: { genreLists },
    };
  } catch (error) {
    // console.error(error);
    return {
      props: {
        genreLists: null,
      },
    };
  }
};
