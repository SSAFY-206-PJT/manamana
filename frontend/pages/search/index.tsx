import Navbar from '../../components/navBar';
import Headerbar from '../../components/headerBar';
import SearchBar from '@/components/pages/search/SearchBar';
import Image from 'next/image';
import AngleDown from '../../public/images/fi-rs-angle-small-down.svg';
import Link from 'next/link';
import { useEffect } from 'react';
import { withRouter } from 'next/router';

export default function SearchPage() {
  const totalWebtoonCount = 1000;

  /*
  * @Method
  * 필터가 클릭됐을 경우 실행되는 메소드
  * */
  const onfilterClick = () => {
    console.log("필터 클릭됨");
  }

  /*
  * @Method
  * 배치 순서 버튼이 클릭됐을 경우 실행되는 메소드
  * */
  const onOrderButtonClick = () => {
    alert("배치 순서 버튼 클릭됨");
  }

  /*
  * @Method
  * Seach Bar에 변화가 있을 시 서버와 통신할 수 있도록 호출되는 메소드
  * */
  const changeSearchContent = (content: string) => {

  }

  useEffect(
    () => {
      console.log()
    },
    []
  )

  return (
    <div className='bg-BackgroundLight h-screen'>
      <Headerbar showBackBtn={true} pageName="내 웹툰" rightBtn="EDIT" />
      <div className='bg-BackgroundLightComponent m-2 p-4 rounded-2xl'>
        <SearchBar sendData={changeSearchContent} />
        <div className='flex flex-row justify-between items-center'>
          <div className='font-bold text-xl pl-2 pr-2'>
            <span>전체</span>
            <span className='ml-1 text-PrimaryLight'>{(totalWebtoonCount > 999 ? '999+' : totalWebtoonCount)}</span>
            <span className='ml-1'>개</span>
          </div>
          <div className='flex flex-row mr-2'>
            <button
              className='mr-2 flex border-2 pl-2 rounded-2xl border-BackgroundLightComponentBorder'
              onClick={onOrderButtonClick}
            >
              <span>조회순</span>
              <AngleDown width={20} height={20} />
            </button>
            <Link href="/search/filter">
              <Image
                src={'/images/filter-img.svg'}
                alt='filter'
                width='20'
                height='20'
                className='ml-2'
                onClick={onfilterClick}
              />
            </Link>
          </div>
        </div>
      </div>
      <div className='bg-BackgroundLightComponent m-2 p-4 rounded-2xl'>
        ㅎㅎ
      </div>
      <Navbar />
    </div>
  );
}
