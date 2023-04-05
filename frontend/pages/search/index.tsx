import Navbar from '../../components/common/Navbar';
import Headerbar from '../../components/common/Headerbar';
import SearchBar from '@/components/pages/search/SearchBar';
import AngleDown from '../../public/images/fi-rs-angle-small-down.svg';
import Link from 'next/link';
import { ChangeEvent, useEffect, useState, useRef } from 'react';
import { RootState } from '../../store/index';
import { useDispatch, useSelector } from 'react-redux';
import {
  deleteDayTag,
  deleteGenreTag,
  deleteGradeTag,
  deleteStatusTag,
} from '@/store/CurSearchTagSlice';
import SearchTag from '@/components/pages/search/SearchTag';
import Lottie from 'react-lottie-player';
import EmptyLottie from '../../public/lottie/51382-astronaut-light-theme.json';
import WebtoonItem from '@/components/common/WebtoonItem';
import { getWebtoons } from '../api/webtoon';
import SortModal from '@/components/pages/search/SortModal';
import { getCookie } from '@/util/cookie';

interface Data {
  key: number;
  value: string;
}

export default function SearchPage() {
  const token = getCookie('accessToken');
  const dispatch = useDispatch();
  const curSearchTag = useSelector((state: RootState) => state.searchTag); // Redux에 있는 값

  const [selectedDaysElement, setSelectedDaysElement] = useState<any | null>(null); // Tag 정보를 이용하여 Element로 변환한 값
  const [selectedGenresElement, setSelectedGenresElement] = useState<any | null>(null); // Tag 정보를 이용하여 Element로 변환한 값
  const [selectedGradesElement, setSelectedGradesElement] = useState<any | null>(null); // Tag 정보를 이용하여 Element로 변환한 값
  const [selectedStatusElement, setSelectedStatusElement] = useState<any | null>(null); // Tag 정보를 이용하여 Element로 변환한 값

  const [webtoonList, setWebtoonList] = useState<any[]>([]); // 웹툰 정보 리스트
  const [webtoonListElement, setWebtoonListElement] = useState<any | null>(null); // 웹툰 정보를 이용하여 Element로 변환한 값
  const [webtoonCount, setWebtoonCount] = useState<number>(0); // 전체 웹툰 리스트의 길이

  const [searchText, setSearchText] = useState<string>('');

  const [sortType, setSortType] = useState<number>(1); // 정렬기준 1: 조회순 2: 평점 높은 순 3: 댓글 많은 순
  const [sortTypeString, setSortTypeString] = useState<string>('조회순');
  const [sortOpen, setSortOpen] = useState<boolean>(false);
  const [pageNum, setPageNum] = useState<number>(1);

  /*
   * @Method
   * 필터가 클릭됐을 경우 실행되는 메소드
   * */
  const onfilterClick = () => {
    // console.log('필터 클릭됨');
  };

  /*
   * @Method
   * 배치 순서 버튼이 클릭됐을 경우 실행되는 메소드
   * */

  const closeModal = () => {
    setSortOpen(sortOpen => !sortOpen);
  };

  const openModal = () => {
    setSortOpen(true);
  };

  /*
   * @Method
   * Tag 삭제 버튼 클릭시 태그 삭제
   * */
  const deleteDay = (data: Data) => {
    dispatch(deleteDayTag(data));
    reloadTag();
  };

  /*
   * @Method
   * Tag 삭제 버튼 클릭시 태그 삭제
   * */
  const deleteGenre = (data: Data) => {
    dispatch(deleteGenreTag(data));
    reloadTag();
  };

  /*
   * @Method
   * Tag 삭제 버튼 클릭시 태그 삭제
   * */
  const deleteGrade = (data: Data) => {
    dispatch(deleteGradeTag(data));
    reloadTag();
  };

  /*
   * @Method
   * Tag 삭제 버튼 클릭시 태그 삭제
   * */
  const deleteStatus = (data: Data) => {
    dispatch(deleteStatusTag(data));
    reloadTag();
  };

  // 정렬 상태관리
  const handleSortChange = (newState: number) => {
    setSortType(newState);
  };

  const reloadTag = () => {
    // console.dir(curSearchTag);
    setSelectedDaysElement(
      curSearchTag.days.map(v => <SearchTag tagData={v} deleteTag={deleteDay} />),
    );
    setSelectedGenresElement(
      curSearchTag.genres.map(v => <SearchTag tagData={v} deleteTag={deleteGenre} />),
    );
    setSelectedGradesElement(
      curSearchTag.grades.map(v => <SearchTag tagData={v} deleteTag={deleteGrade} />),
    );
    setSelectedStatusElement(
      curSearchTag.status.map(v => <SearchTag tagData={v} deleteTag={deleteStatus} />),
    );
  };

  const onSearchBarChange = (e: ChangeEvent<HTMLInputElement>) => setSearchText(e.target.value);
  const handleClearSearchText = () => {
    setSearchText('');
  };

  useEffect(() => {
    if (sortType === 1) {
      setSortTypeString('조회순');
    } else if (sortType === 2) {
      setSortTypeString('별점 높은 순');
    } else if (sortType === 3) {
      setSortTypeString('댓글 많은 순');
    }
  }, [sortType]);

  useEffect(() => {
    const debounce = setTimeout(() => {
      setSearchText(searchText);
    }, 300);

    return () => clearTimeout(debounce);
  }, [searchText]);

  useEffect(() => {
    reloadTag();
    // 필터값을 이용하여 웹툰을 검색한다.
    getWebtoons(
      {
        keyword: searchText,
        page: pageNum, // 페이지 숫자
        size: 21, // 한 페이지에 몇 개를 받을 건지
        sortType: sortType,
        statusId: curSearchTag.status.map(v => v.key),
        genreId: curSearchTag.genres.map(v => v.key),
        gradeId: curSearchTag.grades.map(v => v.key),
        dayId: curSearchTag.days.map(v => v.key),
      },
      token,
    ).then(res => {
      if (res != null) {
        setWebtoonList(res.contents);
        setWebtoonCount(res.count);
      } else {
      }
    });
  }, [sortType, curSearchTag, searchText]);

  useEffect(() => {
    setWebtoonListElement(
      webtoonList.map(data => {
        return (
          <WebtoonItem
            id={data.id}
            imageUrl={data.imagePath}
            status={data.status}
            webtoonName={data.name}
            key={data.id}
          />
        );
      }),
    );
  }, [webtoonList]);

  const scrollRef = useRef<any>(null);
  const scrollNext = () => {
    const nextPage = pageNum + 1;
    // console.log(nextPage);
    getWebtoons(
      {
        keyword: searchText,
        page: nextPage, // 페이지 숫자
        size: 21, // 한 페이지에 몇 개를 받을 건지
        sortType: sortType,
        statusId: curSearchTag.status.map(v => v.key),
        genreId: curSearchTag.genres.map(v => v.key),
        gradeId: curSearchTag.grades.map(v => v.key),
        dayId: curSearchTag.days.map(v => v.key),
      },
      token,
    ).then(res => {
      if (res != null) {
        setPageNum(nextPage);
        setWebtoonList([...webtoonList, ...res.contents]);
      }
    });
  };
  const scrollFn = () => {
    // 스크롤 맨 밑에서
    console.log(scrollRef.current.scrollTop);
    if (
      scrollRef.current.scrollTop ===
      scrollRef.current.scrollHeight - scrollRef.current.clientHeight
    ) {
      scrollNext();
    }
  };

  return (
    <div
      className="min-w-screen w-screen overflow-auto bg-BackgroundLight"
      ref={scrollRef}
      onScroll={scrollFn}
    >
      <Headerbar showBackBtn={true} pageName="탐색" />
      <div className="m-2 rounded-2xl bg-BackgroundLightComponent p-4 pb-2">
        <SearchBar
          onSearchBarChange={onSearchBarChange}
          onClearSearchText={handleClearSearchText}
        />
        <div className="flex flex-row items-center justify-between">
          <div className="pl-2 pr-2 text-xl font-bold">
            <span>전체</span>
            <span className="ml-1 text-PrimaryLight">
              {webtoonCount > 999 ? '999+' : webtoonCount}
            </span>
            <span className="ml-1">개</span>
          </div>
          <div className="mr-2 flex flex-row">
            <button
              className="mr-2 flex rounded-2xl border-2 border-BackgroundLightComponentBorder pl-2"
              onClick={openModal}
            >
              <span>{sortTypeString}</span>
              <AngleDown width={20} height={20} />
            </button>
            <Link href="/search/filter" className="flex items-center">
              <img
                src="/images/filter-img.svg"
                alt="filter"
                className="ml-2 h-5 w-5"
                onClick={onfilterClick}
              ></img>
            </Link>
          </div>
        </div>

        {/* 정렬버튼을 누르면 뜨는 정렬기준 선택창 */}
        <SortModal sortOpen={sortOpen} closeModal={closeModal} onSortChange={handleSortChange} />

        <div className="flex- row m-2 flex flex-wrap gap-2">
          {selectedDaysElement}
          {selectedGenresElement}
          {selectedGradesElement}
          {selectedStatusElement}
        </div>
      </div>
      {webtoonList.length == 0 ? (
        <div className="flex h-2/3 w-full flex-col items-center justify-center">
          <Lottie loop animationData={EmptyLottie} play className="h-2/3 w-2/3" />
        </div>
      ) : (
        <div className="float-left m-2 mt-2 rounded-2xl bg-BackgroundLightComponent p-4 pb-12 text-center">
          {webtoonListElement}
        </div>
      )}
      <Navbar />
    </div>
  );
}
