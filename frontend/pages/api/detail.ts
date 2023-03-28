import axios from 'axios';
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

///////////////////* 웹툰 상세 기능 *///////////////////
/**웹툰 상세정보 조회
 *
 * @param webtoon_id
 * @returns Webtoon
 */
export const getWebtoonDetail = async (webtoon_id: any) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}`,
    headers: { 'Content-Type': 'application/json' },
  };
  try {
    const url = `/webtoons/${webtoon_id}`;
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};

/**웹툰 플랫폼
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonProviders = async (webtoon_id: any) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/providers`,
    headers: { 'Content-Type': 'application/json' },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};

/**웹툰 댓글 전체 조회
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonComments = async (webtoon_id: any) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/comments`,
    headers: { 'Content-Type': 'application/json' },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
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
export const postWebtoonComment = async (webtoon_id: any, content: string, isSpoiler: boolean) => {
  const options = {
    method: 'POST',
    url: `/webtoons/${webtoon_id}/comments`,
    headers: { 'Content-Type': 'application/json' },
    data: { content, isSpoiler },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
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
) => {
  const options = {
    method: 'PATCH',
    url: `/webtoons/${webtoon_id}/comments/${comment_id}`,
    headers: { 'Content-Type': 'application/json' },
    data: { content, isSpoiler },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};

/**웹툰 댓글 삭제
 *
 * @param webtoon_id
 * @param id 댓글 id
 * @returns
 */
export const deleteWebtoonComment = async (webtoon_id: any, id: number) => {
  const options = {
    method: 'DELETE',
    url: `/webtoons/${webtoon_id}/comments`,
    headers: { 'Content-Type': 'application/json' },
    data: { id: [id] },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};

/**웹툰 댓글 신고
 *
 * @param webtoon_id
 * @param comment_id
 * @returns
 */
export const reportWebtoonComment = async (webtoon_id: any, comment_id: number) => {
  const options = {
    method: 'PATCH',
    url: `/webtoons/${webtoon_id}/comments/${comment_id}/report`,
    headers: { 'Content-Type': 'application/json' },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};

/**관심웹툰 등록
 *
 * @param webtoon_id
 * @returns 응답 or null
 */
export const likeWebtoon = async (webtoon_id: any) => {
  const options = {
    method: 'PATCH',
    url: `/webtoons/${webtoon_id}/like`,
    headers: { 'Content-Type': 'application/json' },
  };

  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};

/**개인이 평가한 평점 조회
 *
 * @param webtoon_id
 * @returns
 */
export const getWebtoonMyScore = async (webtoon_id: any) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/scores`,
    headers: { 'Content-Type': 'application/json' },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};

/**웹툰 평점 입력, 수정
 *
 * @param webtoon_id
 * @returns
 */
export const postWebtoonMyScore = async (webtoon_id: any, score: number) => {
  const options = {
    method: 'POST',
    url: `/webtoons/${webtoon_id}/scores`,
    headers: { 'Content-Type': 'application/json' },
    data: { score },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
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
export const unlikeWebtoon = async (user_id: any, webtoon_ids: number[]) => {
  const options = {
    method: 'DELETE',
    url: `/users/${webtoon_ids}/webtoons`,
    headers: { 'Content-Type': 'application/json' },
    data: { id: [1] },
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

/** 내 댓글 조회
 *
 * @param user_id
 * @returns
 */
export const myWebtoonComment = async (user_id: any) => {
  const options = {
    method: 'GET',
    url: `/users/${user_id}/comments`,
    headers: { 'Content-Type': 'application/json' },
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
export const getElseWebtoon = async (webtoon_id: any) => {
  const options = {
    method: 'GET',
    url: `/webtoons/${webtoon_id}/recommands`,
    headers: { 'Content-Type': 'application/json' },
  };
  try {
    const res = await axios.request(options);
    const answer = res.data;
    return answer;
  } catch (error) {
    return null;
  }
};
