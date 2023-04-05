import axios from 'axios';
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

interface ApiResult {
  contents: any[];
  count: number;
}

const getWebtoons = async (p: Parameters, token: any): Promise<ApiResult> => {
  let url = `/webtoons?keyword=${p.keyword}&page=${p.page}&size=${p.size}&sortType=${p.sortType}`;
  let value: any[] = [];
  let contentCount: number = 0;
  await axios
    .post(
      url,
      {
        statusId: p.statusId,
        genreId: p.genreId,
        gradeId: p.gradeId,
        dayId: p.dayId,
      },
      {
        headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
      },
    )
    .then(({ data }) => {
      value = data.result.contents;
      contentCount = data.result.count;
    })
    .catch(err => {
      // console.log('[ERROR] 마나 골라 웹툰 정보 가져올 때 오류 발생');
    });
  return { contents: value, count: contentCount };
};

export { getWebtoons };
