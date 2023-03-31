import axios from 'axios';

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

/** 로그인 갱신
 * 헤더에 기존 accessToken, 쿠키에는 refreshToken이 담겨있어야 함
 * @returns 새로운 accessToken
 */
export const renewToken = async (token: any) => {
  const options = {
    method: 'GET',
    url: `/auth/reissue`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    // token 을 응답
    return answer;
  } catch (error) {
    console.log(error);
    return null;
  }
};

///////////////////* 웹툰 상세 기능 *///////////////////
/**웹툰 상세정보 조회
 *
 * @param webtoon_id
 * @returns Webtoon
 */
export const getWebtoonDetail = async (webtoon_id: any, token: any) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}`,
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    console.log(axios.defaults.headers.common);
    console.log(error);
    return null;
  }
};

/**웹툰 플랫폼
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonProviders = async (webtoon_id: any, token: any) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/providers`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    console.log(error);
    return null;
  }
};

/**웹툰 댓글 전체 조회
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonComments = async (webtoon_id: any, page: number, token: any) => {
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
    console.log(error);
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
  token: any,
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
    console.log(error);
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
  token: any,
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
    console.log(error);
    return null;
  }
};

/**웹툰 댓글 삭제
 *
 * @param webtoon_id
 * @param id 댓글 id
 * @returns
 */
export const deleteWebtoonComment = async (webtoon_id: any, id: number, token: any) => {
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
    console.log(error);
    return null;
  }
};

/**웹툰 댓글 신고
 *
 * @param webtoon_id
 * @param comment_id
 * @returns
 */
export const reportWebtoonComment = async (webtoon_id: any, comment_id: number, token: any) => {
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
    console.log(error);
    return null;
  }
};

/**관심웹툰 등록
 *
 * @param webtoon_id
 * @returns 응답 or null
 */
export const likeWebtoon = async (webtoon_id: any, token: any) => {
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
    console.log(error);
    return null;
  }
};

/**개인이 평가한 평점 조회
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonMyScore = async (webtoon_id: any, token: any) => {
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
    console.log(error);
    return null;
  }
};

/**웹툰 평점 입력, 수정
 *
 * @param webtoon_id
 * @returns
 */
export const postWebtoonMyScore = async (webtoon_id: any, score: number, token: any) => {
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
    console.log(error);
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
export const unlikeWebtoon = async (user_id: any, webtoon_ids: number[], token: any) => {
  const options = {
    method: 'DELETE',
    url: `/users/${webtoon_ids}/webtoons`,
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
    data: { id: [1] },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    console.log(error);
    console.log(error);
    return null;
  }
};

/** 내 댓글 조회
 *
 * @param user_id
 * @returns
 */
export const myWebtoonComment = async (user_id: any, token: any) => {
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
    console.log(error);
    return null;
  }
};

/** 유저 정보 조회
 *
 * @returns
 */
export const getUserInfo = async (token: any) => {
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
    console.log(error);
    return null;
  }
};

///////////////////* 웹툰 추천 관련 기능 *///////////////////
export const getElseWebtoon = async (webtoon_id: any, token: any) => {
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
    console.log(error);
    return null;
  }
};
