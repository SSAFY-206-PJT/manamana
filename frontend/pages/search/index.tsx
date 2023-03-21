import Navbar from '../../components/navBar';
import Headerbar from '../../components/headerBar';
import SearchBar from '@/components/pages/search/SearchBar';
import Image from 'next/image';
import AngleDown from '../../public/images/fi-rs-angle-small-down.svg';
import Link from 'next/link';
import { useEffect, useState } from 'react';
import { withRouter } from 'next/router';
import { RootState } from "../../store/index";
import { useDispatch, useSelector } from "react-redux";
import { deleteCurSearchOneTag } from '@/store/CurSearchTagSlice';
import SearchTag from '@/components/pages/search/SearchTag';
import Lottie from "react-lottie-player";
import EmptyLottie from "../../public/lottie/51382-astronaut-light-theme.json";

export default function SearchPage() {
  const dispatch = useDispatch();
  const curSearchTag = useSelector((state: RootState) => state.searchTag);  // Redux에 있는 값

  const [selectedTagListElement, setSelectedTagListElements] = useState<any | null>(null);  // Tag 정보를 이용하여 Element로 변환한 값 
  const [webtoonList, setWebtoonList] = useState<any[]>([]); // 웹툰 정보 리스트
  const [webtoonListElement, setWebtoonListElement] = useState<any | null>(null); // 웹툰 정보를 이용하여 Element로 변환한 값

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

  /*
  * @Method
  * Tag 삭제 버튼 클릭시 태그 삭제
  * */
  const deleteTag = (value: string) => {
    dispatch(deleteCurSearchOneTag(value));
    reloadTag();
  }

  const reloadTag = () => {
    return setSelectedTagListElements(curSearchTag.tags.map((v) => <SearchTag tagName={v} deleteTag={deleteTag} />));
  }

  useEffect(
    () => {
      reloadTag();
      // Tag 값에 따라 Webtoon Data를 가져옴


      // 해당 Webtton Data의 길이
    },
    []
  )

  useEffect(
    () => {
      reloadTag();
    },
    [curSearchTag.tags])

  return (
    <div className='bg-BackgroundLight h-screen'>
      <Headerbar showBackBtn={true} pageName="내 웹툰" rightBtn="EDIT" />
      <div className='bg-BackgroundLightComponent m-2 p-4 rounded-2xl'>
        <SearchBar sendData={changeSearchContent} />
        <div className='flex flex-row justify-between items-center'>
          <div className='font-bold text-xl pl-2 pr-2'>
            <span>전체</span>
            <span className='ml-1 text-PrimaryLight'>{(webtoonList.length > 999 ? '999+' : webtoonList.length)}</span>
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
        <div className='flex flex- row flex-wrap gap-2 m-2'>
          {selectedTagListElement}
        </div>
      </div>
      {webtoonList.length == 0 ?
        <div className='w-full h-2/3 flex flex-col justify-center items-center'>
          <Lottie loop animationData={EmptyLottie} play className='w-2/3 h-2/3' />
        </div>
        :
        <div className='bg-BackgroundLightComponent m-2 p-4 rounded-2xl'>
          {webtoonListElement}
        </div>
      }
      <Navbar />
    </div>
  );
}
