import Navbar from '../../components/common/Navbar';
import Headerbar from '@/components/common/Headerbar';
import Image from 'next/image';
import Link from 'next/link';

export default function ProfilePage() {

  const info = {
    nickname: '임시완',
    email: 'mana@ssafy.com',
    loveCount: 2,
    starCount: 3,
    commentCount: 4
  }

  const onSettingClick = () => {
    console.log("설정 클릭됨");
  }

  const onLogOutClick = () => {
    console.log("로그아웃 클릭됨");
  }

  return (
    <div className='h-screen'>
      <Headerbar showBackBtn={false} pageName='마이페이지' rightBtn='NOTI' />
      <div className='w-full h-full bg-BackgroundLight p-2 flex flex-col gap-4'>
        <div className='flex items-center bg-BackgroundLightComponent p-8 rounded-2xl'>
          <div className='w-full flex gap-4'>
            <Image
              src={'/images/Temp_Profile.jpg'}
              alt='프로필 이미지'
              width={100}
              height={100}
              className="rounded-full"
            />
            <div className='flex flex-col w-full gap-2 justify-center'>
              <div className='text-xl font-medium text-FontPrimaryLight'>임시완</div>
              <div className='text-base text-FontSecondaryLight'>mana@ssafy.com</div>
              <button className='bg-PrimaryLight rounded-2xl p-4 pt-2 pb-2 font-bold text-FontPrimaryDark text-base w-24' onClick={onLogOutClick}>로그아웃</button>
            </div>
          </div>
          <div className='flex-grow-1' onClick={onSettingClick}>
            <Image
              src={'/images/Setting.png'}
              alt='설정 이미지'
              width={50}
              height={50}
            />
          </div>
        </div>
        <div className='flex justify-center bg-BackgroundLightComponent p-8 rounded-2xl'>
          <div className='w-full flex flex-col gap-2 justify-center items-center'>
            <div>
              <Image
                src={'/images/Heart_Logo.png'}
                alt="내 웹툰"
                width={50}
                height={50}
              />
            </div>
            <div className='font-bold text-2xl'>{info.loveCount}</div>
            <Link href={'/my-webtoon'}>
              <button className='bg-PrimaryLight rounded-2xl text-FontPrimaryDark p-2 font-medium text-xl'>관심 웹툰</button>
            </Link>
          </div>
          <div className='w-full flex flex-col gap-2 justify-center items-center'>
            <div>
              <Image
                src={'/images/Star_Logo.png'}
                alt="내 별점"
                width={45}
                height={45}
              />
            </div>
            <div className='font-bold text-2xl'>{info.starCount}</div>
            <Link href={'/my-star'}>
            <button className='border-2 border-PrimaryLight rounded-2xl text-PrimaryLight p-2 font-medium text-xl'>내 별점</button>
            </Link>
          </div>
          <div className='w-full flex flex-col gap-2 justify-center items-center'>
            <div>
              <Image
                src={'/images/Comment_Logo.png'}
                alt="내 댓글"
                width={45}
                height={45}
              />
            </div>
            <div className='font-bold text-2xl'>{info.commentCount}</div>
            <Link href={'/my-comment'}>
            <button className='bg-PrimaryLight rounded-2xl text-FontPrimaryDark p-2 font-medium text-xl'>내 댓글</button>
            </Link>
          </div>
        </div>
        <div className='flex flex-col w-full justify-center items-center mt-4'>
          
        </div>
      </div>
      <Navbar />
    </div>
  );
}
