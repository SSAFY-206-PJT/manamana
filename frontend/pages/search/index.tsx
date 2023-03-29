import Navbar from '../../components/common/Navbar';
import Headerbar from '../../components/common/Headerbar';
import SearchBar from '@/components/pages/search/SearchBar';
import Image from 'next/image';
import AngleDown from '../../public/images/fi-rs-angle-small-down.svg';
import Link from 'next/link';
import { ChangeEvent, useEffect, useState } from 'react';
import { RootState } from "../../store/index";
import { useDispatch, useSelector } from "react-redux";
import { deleteDayTag, deleteGenreTag, deleteGradeTag, deleteStatusTag } from '@/store/CurSearchTagSlice';
import SearchTag from '@/components/pages/search/SearchTag';
import Lottie from "react-lottie-player";
import EmptyLottie from "../../public/lottie/51382-astronaut-light-theme.json";
import WebtoonItem from '@/components/common/WebtoonItem';
import { getWebtoons } from '../api/webtoon';

interface Data {
  key: number,
  value: string
}

export default function SearchPage() {
  const dispatch = useDispatch();
  const curSearchTag = useSelector((state: RootState) => state.searchTag);  // Redux에 있는 값

  const [selectedDaysElement, setSelectedDaysElement] = useState<any | null>(null);  // Tag 정보를 이용하여 Element로 변환한 값 
  const [selectedGenresElement, setSelectedGenresElement] = useState<any | null>(null);  // Tag 정보를 이용하여 Element로 변환한 값 
  const [selectedGradesElement, setSelectedGradesElement] = useState<any | null>(null);  // Tag 정보를 이용하여 Element로 변환한 값 
  const [selectedStatusElement, setSelectedStatusElement] = useState<any | null>(null);  // Tag 정보를 이용하여 Element로 변환한 값 

  const [webtoonList, setWebtoonList] = useState<any[]>([]); // 웹툰 정보 리스트
  const [webtoonListElement, setWebtoonListElement] = useState<any | null>(null); // 웹툰 정보를 이용하여 Element로 변환한 값

  const [searchText, setSearchText] = useState<string>('');
  const [tempSearchText, setTempSearchText] = useState(searchText);

  let pageNum = 0;

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
  * Tag 삭제 버튼 클릭시 태그 삭제
  * */
  const deleteDay = (data: Data) => {
    dispatch(deleteDayTag(data));
    reloadTag();
  }

  /*
  * @Method
  * Tag 삭제 버튼 클릭시 태그 삭제
  * */
  const deleteGenre = (data: Data) => {
    dispatch(deleteGenreTag(data));
    reloadTag();
  }

  /*
  * @Method
  * Tag 삭제 버튼 클릭시 태그 삭제
  * */
  const deleteGrade = (data: Data) => {
    dispatch(deleteGradeTag(data));
    reloadTag();
  }

  /*
  * @Method
  * Tag 삭제 버튼 클릭시 태그 삭제
  * */
  const deleteStatus = (data: Data) => {
    dispatch(deleteStatusTag(data));
    reloadTag();
  }

  const reloadTag = () => {
    console.dir(curSearchTag);
    setSelectedDaysElement(
      curSearchTag.days.map((v) => <SearchTag tagData={v} deleteTag={deleteDay} />)
    );
    setSelectedGenresElement(
      curSearchTag.genres.map((v) => <SearchTag tagData={v} deleteTag={deleteGenre} />)
    );
    setSelectedGradesElement(
      curSearchTag.grades.map((v) => <SearchTag tagData={v} deleteTag={deleteGrade} />)
    );
    setSelectedStatusElement(
      curSearchTag.status.map((v) => <SearchTag tagData={v} deleteTag={deleteStatus} />)
    );

  }

  const onSearchBarChange = (e: ChangeEvent<HTMLInputElement>) => setTempSearchText(e.target.value);

  useEffect(() => {
    const debounce = setTimeout(() => {
      return setSearchText(tempSearchText);
    }, 300);

    return () => clearTimeout(debounce);
  }, [tempSearchText]);

  useEffect(() => {
    if (searchText !== '') {
      // 각 세팅 + 검색어(searchText) 를 통해 웹툰을 검색한다.
      getWebtoons({
        keyword: searchText,
        page: pageNum,   // 페이지 숫자
        size: 21,   // 한 페이지에 몇 개를 받을 건지
        sortType: 1,
        statusId: curSearchTag.status.map((v) => v.key),
        genreId: curSearchTag.genres.map((v) => v.key),
        gradeId: curSearchTag.grades.map((v) => v.key),
        dayId: curSearchTag.days.map((v) => v.key)
      })
        .then((res) => {
          if (res != null) {
            setWebtoonList(res);
          }
        });
    }
  }, [searchText]);

  useEffect(
    () => {
      reloadTag();
      // 필터값을 이용하여 웹툰을 검색한다.
      getWebtoons({
        keyword: '',
        page: pageNum,   // 페이지 숫자
        size: 21,   // 한 페이지에 몇 개를 받을 건지
        sortType: 1,
        statusId: curSearchTag.status.map((v) => v.key),
        genreId: curSearchTag.genres.map((v) => v.key),
        gradeId: curSearchTag.grades.map((v) => v.key),
        dayId: curSearchTag.days.map((v) => v.key)
      })
        .then((res) => {
          if (res != null) {
            setWebtoonList(res);
          }
        });
    },
    [curSearchTag]);

  useEffect(
    () => {
      setWebtoonListElement(
        webtoonList.map((data) => {
          return <WebtoonItem id={data.id} imageUrl={data.imagePath} status={data.status} webtoonName={data.name} key={data.id} />
        })
      );
    },
    [webtoonList]);

  return (
    <div className='bg-BackgroundLight w-full h-full pb-12'>
      <Headerbar showBackBtn={true} pageName="탐색" rightBtn="EDIT" />
      <div className='bg-BackgroundLightComponent m-2 p-4 pb-2 rounded-2xl'>
        <SearchBar onSearchBarChange={onSearchBarChange} />
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
          {selectedDaysElement}
          {selectedGenresElement}
          {selectedGradesElement}
          {selectedStatusElement}
        </div>
      </div>
      {webtoonList.length == 0 ?
        <div className='w-full h-2/3 flex flex-col justify-center items-center'>
          <Lottie loop animationData={EmptyLottie} play className='w-2/3 h-2/3' />
        </div>
        :
        <div className='bg-BackgroundLightComponent m-2 p-4 rounded-2xl text-center'>
          {webtoonListElement}
        </div>
      }
      <Navbar />
    </div>
  );
}