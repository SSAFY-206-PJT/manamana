import axios from 'axios';
/*
 *
 * @author zouamare
 *
 * @copyright 2023
 */

// basic axios setting
axios.defaults.baseURL = 'https://j8b206.p.ssafy.io/api';
// axios.defaults.withCredentials = true;

///////////////////* GET *///////////////////
/**
 * managolaInit : 마나골라에 필요한 웹툰 정보를 가져옴
 * @returns
 */
const managolaInit = async (token: any) => {
  let url = `/webtoons/world-cup`;
  let value = null;
  await axios
    .get(url, {
      headers: {
        Authorization: 'Bearer ' + token,
      },
    })
    .then(({ data }) => {
      value = data.result;
    })
    .catch(err => {
      // console.log("[ERROR] 마나 골라 웹툰 정보 가져올 때 오류 발생");
    });
  return value;
};

///////////////////* POST *///////////////////
/**
 * managolaEnd : 마나 골라 결과를 전송하여 추천된 웹툰의 정보를 가져옴
 * @returns
 */
const managolaEnd = async (idArr: number[], token: any) => {
  let url = `/webtoons/world-cup`;
  let value: any = null;
  await axios
    .post(
      url,
      {
        id: idArr,
      },
      {
        headers: {
          Authorization: 'Bearer ' + token,
        },
      },
    )
    .then(({ data }) => {
      value = data.result;
    })
    .catch(err => {
      // console.log("[ERROR] 마나 골라 웹툰 정보 전송 시 오류 발생");
    });
  return value;
};

export { managolaInit, managolaEnd };
