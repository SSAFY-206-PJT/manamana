import PublishStateBlock from "@/components/pages/search/filter/PublishStateBlock";
import PublishDayBlock from "@/components/pages/search/filter/PublishDayBlock";
import GenreStateBlock from "@/components/pages/search/filter/GenreStateBlock";
import AgeGradeBlock from "@/components/pages/search/filter/AgeGradeBlock";
import {useState, useEffect} from "react";
import ConfirmBtn from "@/components/confirmBtn";
import { useRouter } from "next/router"
import { RootState } from "../../../store/index";
import { useDispatch, useSelector } from "react-redux";
import { changeDays, changeGenres, changeGrades, changeStatus } from "@/store/CurSearchTagSlice";
import axios from "axios";

interface Props{
  days: {id:number, day:string}[],
  genres: {id:number, name:string}[],
  grades: {id:number, grade:string}[],
  status: {id:number, status:string}[]
}

interface Data{
  key: number,
  value: string
}


export default function FilterPage(props : Props) {

  /*
  * @Variable
  * */
  const router = useRouter(); // 화면 전환을 위한 라우터
  const dispatch = useDispatch(); // redux 사용을 위한 dispatch
  const curSearchTag = useSelector((state: RootState) => state.searchTag);  // 현재 redux에서 저장한 searchTag 값 가져오기
  const [selectedDays, setSelectedDays] = useState<Data[]>([]);
  const [selectedGenres, setSelectedGenres] = useState<Data[]>([]);
  const [selectedGrades, setSelectedGrades] = useState<Data[]>([]);
  const [selectedStatus, setSelectedStatus] = useState<Data[]>([]);

  const selectDayBlock = (data: Data) => {
    selectedDays.push(data);
    console.log(data);
  }

  const selectGenreBlock = (data: Data) => {
    selectedGenres.push(data);
    console.log(data);
  }

  const selectGradeBlock = (data: Data) => {
    selectedGrades.push(data);
    console.log(data);
  }

  const selectStatusBlock = (data: Data) => {
    selectedStatus.push(data);
    console.log(data);
  }

  const unSelectDayBlock = (data: Data) => {
    for(let i = 0; i < selectedDays.length; i++){
      if(selectedDays[i].key === data.key){
        selectedDays.splice(i, 1);
        break;
      }
    }
  }

  const unSelectGenreBlock = (data: Data) => {
    for(let i = 0; i < selectedGenres.length; i++){
      if(selectedGenres[i].key === data.key){
        selectedGenres.splice(i, 1);
        break;
      }
    }
  }

  const unSelectGradeBlock = (data: Data) => {
    for(let i = 0; i < selectedGrades.length; i++){
      if(selectedGrades[i].key === data.key){
        selectedGrades.splice(i, 1);
        break;
      }
    }
  }

  const unSelectStatusBlock = (data: Data) => {
    for(let i = 0; i < selectedStatus.length; i++){
      if(selectedStatus[i].key === data.key){
        selectedStatus.splice(i, 1);
        break;
      }
    }
  }

  const onConfirmClick = () => {
    // 지금까지 선택된 selectedBlocks 데이터를 보낸다.
    dispatch(changeDays(selectedDays));
    dispatch(changeGenres(selectedGenres));
    dispatch(changeGrades(selectedGrades));
    dispatch(changeStatus(selectedStatus));
    // search로 이동한다.
    router.replace({
      pathname: '/search'
    },
    "/search"
    );
  }

  const onCancelClick = () => {
    // search로 이동한다.
    router.back();
  }

  useEffect(() => {
    setSelectedDays([...curSearchTag.days]);
    setSelectedGenres([...curSearchTag.genres]);
    setSelectedGrades([...curSearchTag.grades]);
    setSelectedStatus([...curSearchTag.status]);
  }, []);

  return (
    <div className="flex flex-col gap-4 h-full w-full bg-BackgroundLight">
      <div className="flex justify-center items-center h-16 m-2">
        <span className="text-2xl text-PrimaryLight font-bold">필터</span>
      </div>
      <div className="flex flex-col gap-4 m-4">
        <div className="flex flex-col gap-1">
          <div className="text-lg">완결 여부</div>
          <div className="flex gap-2">
            {props.status.map((v) => {
            let status: boolean = false;
            for(let i = 0; i < curSearchTag.status.length; i++){
              if(curSearchTag.status[i].key === v.id){
                status = true;
                break;
              }
            }
            return <PublishStateBlock key={v.id} value={{key: v.id, value: v.status}} selectBlock={selectStatusBlock} unSelectBlock={unSelectStatusBlock} status={status} />
            })}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">연재 요일</div>
          <div className="text-center">
            {props.days.map((v) => {
              let status: boolean = false;
              for(let i = 0; i < curSearchTag.days.length; i++){
                if(curSearchTag.days[i].key === v.id){
                  status = true;
                  break;
                }
              }
            return <PublishDayBlock key={v.id} value={{key: v.id, value: v.day}} selectBlock={selectDayBlock} unSelectBlock={unSelectDayBlock} status={status} />
          })}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">장르</div>
          <div className="text-center">
            {props.genres.map((v) => {
              let status: boolean = false;
              for(let i = 0; i < curSearchTag.genres.length; i++){
                if(curSearchTag.genres[i].key === v.id){
                  status = true;
                  break;
                }
              }
              return <GenreStateBlock key={v.id} value={{key: v.id, value: v.name}} selectBlock={selectGenreBlock} unSelectBlock={unSelectGenreBlock} status={status} />
            })}
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <div className="text-lg">연령등급</div>
          <div className="flex w-full justify-center gap-8">
            {props.grades.map((v) => {
              let status: boolean = false;
              for(let i = 0; i < curSearchTag.grades.length; i++){
                if(curSearchTag.grades[i].key === v.id){
                  status = true;
                  break;
                }
              }
              return <AgeGradeBlock key={v.id} value={{key: v.id, value: v.grade}} selectBlock={selectGradeBlock} unSelectBlock={unSelectGradeBlock} status={status} />
            })}
          </div>
        </div>
      </div>
      <div className="m-8">
        <ConfirmBtn cancel={onCancelClick} confirm={onConfirmClick} />
      </div>
    </div>
  );
}

export async function getServerSideProps() {
  const daysRes = await axios.get("https://j8b206.p.ssafy.io/api/webtoons/list/days");
  const genresRes = await axios.get("https://j8b206.p.ssafy.io/api/webtoons/list/genres");
  const gradesRes = await axios.get("https://j8b206.p.ssafy.io/api/webtoons/list/grades");
  const statusRes = await axios.get("https://j8b206.p.ssafy.io/api/webtoons/list/status");

  return {
    props: {
      days: daysRes.data.result,
      genres: genresRes.data.result,
      grades: gradesRes.data.result,
      status: statusRes.data.result
    }
  }
}