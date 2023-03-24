import Navbar from '../../components/common/Navbar';
import Headerbar from '@/components/common/Headerbar';
import Image from 'next/image';
import Link from 'next/link';
import { useState, useEffect } from 'react';

type Info = {
  profileImgUrl: string;
  nickname: string;
  email: string;
  loveCount: number;
  starCount: number;
  commentCount: number;
};

export default function ProfilePage() {
  const [isEditState, setIsEditState] = useState<boolean>(false);
  const [info, setInfo] = useState<Info>({
    profileImgUrl: '/images/Temp_Profile.jpg',
    nickname: '임시완',
    email: 'mana@ssafy.com',
    loveCount: 2,
    starCount: 3,
    commentCount: 4,
  });

  const onEditClick = () => {
    setIsEditState(!isEditState);
  };

  const onLogOutClick = () => {
    console.log('로그아웃 클릭됨');
  };

  // 저장 눌렀을 때 바뀐 정보 저장
  const changeInfo = (e: React.ChangeEvent<HTMLInputElement>) => {
    // 추후에는 API로 변경해야함
    setInfo({ ...info, nickname: e.target.value });
  };

  // 처음 프로필 페이지 들어오면 수정중이 아닌 상태
  useEffect(() => {
    setIsEditState(false);
  }, []);

  return (
    <div className="h-screen overflow-hidden">
      <Headerbar showBackBtn={false} pageName="마이페이지" rightBtn="NOTI" />
      <div className="flex h-full w-full flex-col gap-4 bg-BackgroundLight p-2">
        <div className="flex items-center rounded-2xl bg-BackgroundLightComponent p-8">
          <div className="relative flex w-full gap-4">
            <Image
              src={info.profileImgUrl}
              alt="프로필 이미지"
              width={100}
              height={100}
              className="rounded-full"
            />
            {isEditState ? (
              <>
                <Image
                  src={'/images/Profile_Img_Edit.png'}
                  alt="프로필 이미지 수정"
                  width={48}
                  height={48}
                  className="absolute left-[26px] top-[26px]"
                />
                <div
                  className="absolute h-[100px] w-[100px] rounded-full bg-black opacity-30"
                  onClick={() => {
                    console.log('프로필 이미지 수정');
                  }}
                ></div>
              </>
            ) : (
              <></>
            )}
            <div className="flex w-full flex-col justify-center gap-2">
              {isEditState ? (
                <input
                  className="w-full border-[1px] text-xl font-medium text-FontPrimaryLight"
                  type="text"
                  value={info.nickname}
                  onChange={changeInfo}
                ></input>
              ) : (
                <div className="text-xl font-medium text-FontPrimaryLight">{info.nickname}</div>
              )}
              <div className="text-base text-FontSecondaryLight">mana@ssafy.com</div>
            </div>
          </div>
          <div className="flex-grow-1 w-8" onClick={onEditClick}>
            {isEditState ? (
              <div className="font-bold">저장</div>
            ) : (
              <Image src={'/images/Profile_Edit.png'} alt="설정 이미지" width={24} height={24} />
            )}
          </div>
        </div>
        <div className="flex justify-center rounded-2xl bg-BackgroundLightComponent p-8">
          <div className="flex w-full flex-col items-center justify-center gap-2">
            <div>
              <Image src={'/images/Heart_Logo.png'} alt="내 웹툰" width={50} height={50} />
            </div>
            <div className="text-xl font-bold">{info.loveCount}</div>
            <Link href={'/my-webtoon'}>
              <button className="rounded-xl bg-PrimaryLight p-1 pl-2 pr-2 text-lg font-medium text-FontPrimaryDark">
                내 웹툰
              </button>
            </Link>
          </div>
          <div className="flex w-full flex-col items-center justify-center gap-2">
            <div>
              <Image src={'/images/Comment_Logo.png'} alt="내 댓글" width={45} height={45} />
            </div>
            <div className="text-xl font-bold">{info.commentCount}</div>
            <Link href={'/my-comment'}>
              <button className="rounded-xl bg-PrimaryLight p-1 pl-2 pr-2 text-lg font-medium text-FontPrimaryDark">
                내 댓글
              </button>
            </Link>
          </div>
        </div>
        <button
          className="w-24 rounded-2xl bg-PrimaryLight p-4 pt-2 pb-2 text-base font-bold text-FontPrimaryDark"
          onClick={onLogOutClick}
        >
          로그아웃
        </button>
        <div className="mt-4 flex w-full flex-col items-center justify-center"></div>
      </div>
      <Navbar />
    </div>
  );
}
