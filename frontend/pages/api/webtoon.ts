import axios, { AxiosRequestConfig } from 'axios';
import { getCookie } from '@/util/cookie';
/*
 *
 * @author zouamare
 *
 * @copyright 2023
 */

// basic axios setting
axios.defaults.baseURL = 'https://j8b206.p.ssafy.io/api';
// axios.defaults.withCredentials = true;

interface Parameters {
  keyword: string;
  page: number; // 페이지 숫자
  size: number; // 한 페이지에 몇 개를 받을 건지
  sortType: number;
  statusId: number[];
  genreId: number[];
  gradeId: number[];
  dayId: number[];
}

///////////////////* GET *///////////////////
/**
 * getWebtoons : 웹툰 검색
 * @returns
 */
const getWebtoons = async (p: Parameters) => {
  let url = `/webtoons?keyword=${p.keyword}&page=${p.page}&size=${p.size}&sortType=${p.sortType}`;
  let value = null;
  const token = getCookie('accessToken');
  await axios
    .post(url, {
      statusId: p.statusId,
      genreId: p.genreId,
      gradeId: p.gradeId,
      dayId: p.dayId,
    },{
      headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
      // data: {
      //   statusId: p.statusId,
      //   genreId: p.genreId,
      //   gradeId: p.gradeId,
      //   dayId: p.dayId,
      // },
    })
    .then(({ data }) => {
      console.log(data)
      value = data.result;
      console.log('value', value);
    })
    .catch(err => {
      console.log('[ERROR] 마나 골라 웹툰 정보 가져올 때 오류 발생');
    });
  return value;
};

export { getWebtoons };
