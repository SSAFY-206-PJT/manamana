import { setCookie } from '@/util/cookie';
import axios from 'axios';

// axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;
axios.defaults.baseURL = 'https://j8b206.p.ssafy.io/api/';

/**
 * success 올바른 응답여부
 * error 에러코드 API, LOGIN
 * result 결과
 */
export interface Response {
  success: boolean;
  error?: string;
  result: any;
  newToken?: string;
}

/** 로그인 갱신
 * 헤더에 기존 accessToken, 쿠키에는 refreshToken이 담겨있어야 함
 * @returns 새로운 accessToken
 */
export const renewToken = async (token: string): Promise<Response> => {
  const options = {
    method: 'GET',
    url: `/auth/reissue`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return { success: true, result: answer };
  } catch (error) {
    // console.log(error);
    return { success: false, error: 'API', result: { message: 'API통신오류' } };
  }
};

/**유저 정보 가져오기
 *
 * @param token
 * @returns
 */
export const userInfo = async (token: string): Promise<Response> => {
  const user_id = 4;
  const options = {
    method: 'GET',
    url: `/users/${user_id}`,
    headers: {
      Authorization: 'Bearer ' + token,
    },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    // token 을 응답
    return { success: true, result: answer };
  } catch (error) {
    // console.log(error);
    return { success: false, error: 'API', result: { message: 'API통신오류' } };
  }
};

///////////////////* 웹툰 상세 기능 *///////////////////
export interface IdName {
  id: number;
  name: string;
}
export interface WebtoonDetail {
  id: number;
  name: string;
  imagePath: string;
  plot: string;
  grade: string;
  status: string;
  webtoonUrl: string;
  webtoonId: number;
  startDate: string;
  totalEpisode: number;
  colorHsl: string;
  authors: Array<IdName>;
  genres: Array<IdName>;
  days: {
    id: number;
    codeId: number;
  }[];
  additions: {
    view: number;
    scoreCount: number;
    scoreAverage: string;
  };
}

/**웹툰 상세 정보 /webtoons/{webtoon-id}
 *
 * @param webtoon_id
 * @param token
 * @param isAgain
 * @returns
 */
export const getWebtoonDetail = async (
  webtoon_id: any,
  token: string,
  isAgain = false,
): Promise<Response> => {
  const options = {
    method: 'GET',
    url: `https://j8b206.p.ssafy.io/api/webtoons/${webtoon_id}`,
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  };
  try {
    const res = await axios.request(options);

    // 응답은 왔는데 200 ~ 299 가 아닌경우
    // if (res.data.code && res.data.code >= 300 && !isAgain) {
    //   const newres = await renewToken(token);
    //   if (newres.success) {
    //     const newAnswer: any = await getWebtoonDetail(webtoon_id, newres.result.token, true);
    //     return { success: true, result: newAnswer.data.result, newToken: newres.result.token };
    //   } else {
    //     return { success: false, error: 'LOGIN', result: { message: '토큰 갱신 실패' } };
    //   }
    // }

    if (res.data && res.data.isSuccess) {
      const answer = res.data.result;
      return { success: true, result: answer };
    } else {
      return { success: false, error: res.data, result: '' };
    }
  } catch (error) {
    // console.log(error);
    return { success: false, error: 'API', result: { message: 'API통신오류' } };
  }
};

/**웹툰 플랫폼
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonProviders = async (webtoon_id: any, token: string, isAgain = false) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/providers`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;

    if (answer.code !== 200 && !isAgain) {
      const res = await renewToken(token);
      if (res.result.token) {
        setCookie('accessToken', res.result.token);
        const newAnswer: any = await getWebtoonProviders(webtoon_id, res.result.token, true);
        return newAnswer;
      } else {
        return answer;
      }
    } else if (answer.code !== 200 && isAgain) {
      return answer;
    } else {
      return answer;
    }
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**웹툰 댓글 전체 조회
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonComments = async (webtoon_id: any, page: number, token: string) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/comments`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    params: { page },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**웹툰 댓글 작성
 *
 * @param webtoon_id
 * @param content 내용
 * @param isSpoiler 스포일러 여부
 * @returns
 */
export const postWebtoonComment = async (
  webtoon_id: any,
  content: string,
  isSpoiler: boolean,
  token: string,
) => {
  const options = {
    method: 'POST',
    url: `/webtoons/${webtoon_id}/comments`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    data: { content, isSpoiler },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**웹툰 댓글 수정
 *
 * @param webtoon_id
 * @param comment_id
 * @param content
 * @param isSpoiler
 * @returns
 */
export const modifyWebtoonComment = async (
  webtoon_id: any,
  comment_id: number,
  content: string,
  isSpoiler: boolean,
  token: string,
) => {
  const options = {
    method: 'PATCH',
    url: `/webtoons/${webtoon_id}/comments/${comment_id}`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    data: { content, isSpoiler },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**웹툰 댓글 삭제
 *
 * @param webtoon_id
 * @param id 댓글 id
 * @returns
 */
export const deleteWebtoonComment = async (webtoon_id: any, id: number, token: string) => {
  const options = {
    method: 'DELETE',
    url: `/webtoons/${webtoon_id}/comments`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    data: { id: [id] },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**웹툰 댓글 신고
 *
 * @param webtoon_id
 * @param comment_id
 * @returns
 */
export const reportWebtoonComment = async (webtoon_id: any, comment_id: number, token: string) => {
  const options = {
    method: 'PATCH',
    url: `/webtoons/${webtoon_id}/comments/${comment_id}/report`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**관심웹툰 등록
 *
 * @param webtoon_id
 * @returns 응답 or null
 */
export const likeWebtoon = async (webtoon_id: any, token: string) => {
  const options = {
    method: 'PATCH',
    url: `/webtoons/${webtoon_id}/like`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**개인이 평가한 평점 조회
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonMyScore = async (webtoon_id: any, token: string) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/scores`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**웹툰 평점 입력, 수정
 *
 * @param webtoon_id
 * @returns
 */
export const postWebtoonMyScore = async (webtoon_id: any, score: number, token: string) => {
  const options = {
    method: 'POST',
    url: `/webtoons/${webtoon_id}/scores`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    data: { score },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**웹툰 평점 입력, 수정
 *
 * @param webtoon_id
 * @returns
 */
export const goSeePlus = async (webtoon_id: any, token: string) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/redirect/scores`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

///////////////////* 회원 관련 기능 *///////////////////
/**웹툰 관심 등록 해제
 *
 * @param user_id 유저 아이디
 * @param webtoon_ids 웹툰 아이디 목록 []
 * @returns 응답 or null
 */
export const unlikeWebtoon = async (user_id: any, webtoon_ids: number[], token: string) => {
  const options = {
    method: 'DELETE',
    url: `/users/${user_id}/webtoons`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    data: { id: webtoon_ids },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    // console.log(error);
    return null;
  }
};

/** 내 댓글 조회
 *
 * @param user_id
 * @returns
 */
export const myWebtoonComment = async (user_id: any, token: string) => {
  const options = {
    method: 'GET',
    url: `/users/${user_id}/comments`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/** 유저 정보 조회
 *
 * @returns
 */
export const getUserInfo = async (token: string) => {
  const options = {
    method: 'GET',
    url: `/users/1`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/** 유저가 관심등록한 웹툰들
 *
 * @param token
 * @returns
 */
export const getUserLike = async (token: string) => {
  const options = {
    method: 'GET',
    url: `/users/1/webtoons`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

///////////////////* 웹툰 추천 관련 기능 *///////////////////
export const getElseWebtoon = async (webtoon_id: any, token: string) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/recommands`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};

/**추천 알고리즘을 통한 웹툰 조회.
 *
 * @param token
 * @param param 장르(genreId), 나이(age-group), 성별(gender)
 * @returns
 */
export const algoWebtoons = async (token: string, param: string) => {
  const options = {
    method: 'GET',
    url: `/webtoons/recommands/${param}`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    // console.log(res);
    return answer;
  } catch (error) {
    // console.log(error);
    return null;
  }
};
