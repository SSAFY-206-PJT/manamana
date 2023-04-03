import Navbar from '../../components/common/Navbar';
import Headerbar from '@/components/common/Headerbar';
import Link from 'next/link';
import { useState, useEffect } from 'react';
import axios from 'axios';
import styled from '@emotion/styled';
import { GetServerSideProps } from 'next';
import Swal from 'sweetalert2';
import { getCookie, setCookie } from '@/util/cookie';
import { userInfo } from '../api/detail';
import { useRouter } from 'next/router';

type User = {
  id: number;
  email: string;
  nickname: string;
  imagePath: string;
  gender: string;
  age: number;
  likeCount: number;
  scoreCount: number;
};

export default function ProfilePage({ userData }: any) {
  const router = useRouter();
  const [isEditState, setIsEditState] = useState<boolean>(true);
  const [info, setInfo] = useState<User>({
    id: userData.result.id,
    email: userData.result.email,
    nickname: userData.result.nickname,
    imagePath: userData.result.imagePath,
    gender: userData.result.gender,
    age: userData.result.age,
    likeCount: userData.result.likeCount,
    scoreCount: userData.result.scoreCount,
  });
  const [selectedFile, setSelectedFile] = useState<File>(); // 프로필 사진 수정시 담을 곳

  const token = getCookie('accessToken');

  const onEditClick = () => {
    setIsEditState(!isEditState);
  };

  // 로그아웃 클릭시 예/아니오 선택지 나옴. 예 클릭시 .then 로직으로 이동
  const onLogOutClick = () => {
    Swal.fire({
      icon: 'warning',
      title: '로그아웃 하시겠습니까?',
      showCancelButton: true,
      confirmButtonColor: '#96999C',
      cancelButtonColor: '#BE3455',
      confirmButtonText: '예',
      cancelButtonText: '아니오',
    }).then(result => {
      if (result.isConfirmed) {
        setCookie('accessToken', '');
        router.push('/login');
        // Swal.fire({ icon: 'success', title: '로그아웃 로직 작성 후 로그인 페이지로 보내!' });
      }
    });
  };

  // 회원탈퇴 클릭시 예/아니오 선택지 나옴. 예 클릭시 .then 로직으로 이동
  const onRemoveUserClick = () => {
    {
      Swal.fire({
        title: '정말 마나마나를 \n 탈퇴하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#96999C',
        cancelButtonColor: '#BE3455',
        confirmButtonText: '예',
        cancelButtonText: '아니오',
      }).then(result => {
        if (result.isConfirmed) {
          removeUserAxios();
          Swal.fire({ icon: 'success', title: '회원탈퇴 완료' });
        }
      });
    }
  };

  // 회원정보 수정 axios
  const editProfileAxios = () => {
    let id = info.id;
    let nickname = info.nickname;
    let targetFile: any = selectedFile;

    let formData = new FormData();
    formData.append('data', JSON.stringify({ nickname: nickname }));
    formData.append('userImg', targetFile);

    axios
      .patch(`https://j8b206.p.ssafy.io/api/users/${id}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: 'Bearer ' + token,
        },
      })
      .then(response => {
        console.log(response.data);
        {
          Swal.fire({
            title: '프로필이 변경되었습니다.',
            icon: 'success',
          });
        }
      })
      .catch(error => {
        console.error(error);
      });
  };

  // 회원탈퇴 axios
  const removeUserAxios = async () => {
    let id = info.id;
    await axios
      .delete(`https://j8b206.p.ssafy.io/api/users/${id}`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + token,
        },
      })
      .then(response => {
        setCookie('accessToken', '');
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  };

  // 저장 눌렀을 때 바뀐 정보 저장
  const changeNickname = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInfo({ ...info, nickname: e.target.value });
  };
  const changeImagePath = (e: React.ChangeEvent<HTMLInputElement>) => {
    const targetFile = e.target.files?.[0];
    if (targetFile) {
      setSelectedFile(targetFile);
      const selectedFileURL = URL.createObjectURL(targetFile);
      console.log('여기가 138번째줄 이미지 경로', selectedFileURL);
      setInfo({ ...info, imagePath: selectedFileURL });
    }
  };

  // 처음 프로필 페이지 들어오면 수정중이 아닌 상태
  useEffect(() => {
    setIsEditState(false);
    // console.log(userData);
    // console.log(info);
  }, []);

  const BtnUpload = styled.div`
    width: 100px;
    height: 100px;
    background-color: #77767a;
    opacity: 40%;
    border: 1px solid rgb(77, 77, 77);
    border-radius: 9999px;
    font-weight: 500;
    cursor: pointer;
    &:hover {
      background: rgb(77, 77, 77);
      color: #fff;
    }
    #file {
      display: none;
    }
  `;
  return (
    <div className="h-screen overflow-hidden">
      <Headerbar showBackBtn={false} pageName="마이페이지" rightBtn="NOTI" />

      <div className="flex h-full w-full flex-col gap-4 bg-BackgroundLight p-2">
        <div className="flex items-center rounded-2xl bg-BackgroundLightComponent p-8">
          <div className="relative flex w-full">
            <img
              src={info.imagePath}
              alt="프로필 이미지"
              className="h-[100px] w-[100px] rounded-full"
            />
            {isEditState ? (
              <>
                <form>
                  <label htmlFor="file">
                    <BtnUpload className="absolute left-[0px]"></BtnUpload>
                    <img
                      src={'/images/Profile_Img_Edit.png'}
                      alt="프로필 이미지 수정"
                      className="absolute left-[26px] top-[26px] h-12 w-12"
                    ></img>
                  </label>
                  <input
                    type="file"
                    name="file"
                    id="file"
                    accept="image/*"
                    onChange={changeImagePath}
                    className="hidden"
                  ></input>
                  {/* <div>
                    <img src={} className='w-12 h-12' alt="profileImg" />
                  </div> */}
                </form>
              </>
            ) : (
              <></>
            )}
            <div className="mx-4 flex w-full flex-col justify-center gap-2">
              {isEditState ? (
                <input
                  className="w-full border-b-[2px] text-xl font-medium text-FontPrimaryLight"
                  type="text"
                  maxLength={8}
                  value={info.nickname}
                  onChange={changeNickname}
                ></input>
              ) : (
                <div className="text-xl font-medium text-FontPrimaryLight">{info.nickname}</div>
              )}
              <div className="text-base text-FontSecondaryLight">{info.email}</div>
            </div>
            <div className="flex w-8 items-center justify-center" onClick={onEditClick}>
              {isEditState ? (
                <div className="w-8 font-bold" onClick={editProfileAxios}>
                  저장
                </div>
              ) : (
                <div className="h-6 w-6">
                  <img src={'/images/Profile_Edit.png'} alt="설정" className="h-6 w-6"></img>
                </div>
              )}
            </div>
          </div>
        </div>
        <div className="flex justify-center rounded-2xl bg-BackgroundLightComponent p-8">
          <div className="flex w-full flex-col items-center justify-center gap-2">
            <div>
              <img src={'/images/Heart_Logo.png'} alt="My webtoon" className="h-12 w-12"></img>
            </div>
            <div className="text-xl font-bold">{info.likeCount}</div>
            <Link href={'/my-webtoon'}>
              <button className="rounded-xl bg-PrimaryLight p-1 pl-2 pr-2 text-lg font-medium text-FontPrimaryDark">
                내 웹툰
              </button>
            </Link>
          </div>
          <div className="flex w-full flex-col items-center justify-center gap-2">
            <div>
              <img src={'/images/Comment_Logo.png'} alt="My comment" className="h-12 w-12"></img>
            </div>
            <div className="text-xl font-bold">{info.scoreCount}</div>
            <Link href={'/my-comment'}>
              <button className="rounded-xl bg-PrimaryLight p-1 pl-2 pr-2 text-lg font-medium text-FontPrimaryDark">
                내 댓글
              </button>
            </Link>
          </div>
        </div>

        <div className="flex justify-center">
          <button
            className="m-4 w-24 rounded-2xl border-2 border-PrimaryLight p-4 pt-2 pb-2 text-base font-bold text-PrimaryLight"
            onClick={onLogOutClick}
          >
            로그아웃
          </button>
          <button
            className="m-4 w-24 rounded-2xl bg-PrimaryLight p-4 pt-2 pb-2 text-base font-bold text-FontPrimaryDark"
            onClick={onRemoveUserClick}
          >
            회원탈퇴
          </button>
        </div>
        <div className="mt-4 flex w-full flex-col items-center justify-center"></div>
      </div>
      <Navbar />
    </div>
  );
}

export const getServerSideProps: GetServerSideProps = async context => {
  const token = context.req.cookies.accessToken;
  if (token) {
    const res = await userInfo(token);
    if (res.success) {
      const userData: User = res.result;
      return { props: { userData } };
    } else {
      return {
        redirect: {
          destination: '/login',
          permanent: false,
        },
      };
    }
  } else {
    return {
      redirect: {
        destination: '/login',
        permanent: false,
      },
    };
  }
};
