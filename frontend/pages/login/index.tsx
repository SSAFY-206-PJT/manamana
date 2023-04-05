import { GetServerSideProps } from 'next';
import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { setCookie } from '@/util/cookie';
import { css } from '@emotion/react';
import { userInfo } from '../api/detail';
import { setLogin, setUserInfo } from '@/store/LoginSlice';
import { useDispatch } from 'react-redux';

interface Props {
  token: string | null;
}

function Login({ token }: Props) {
  if (token) {
    const [loading, setLoading] = useState<Boolean>(true);
    const router = useRouter();
    const dispatch = useDispatch();
    const userInfoAPI = async () => {
      const res = await userInfo(token);
      // console.log(res.result);
      if (res.result) {
        dispatch(setUserInfo(res.result.result));
        dispatch(setLogin(true));
        setLoading(false);
      }
    };

    useEffect(() => {
      if (!loading) {
        router.push('/');
      }
    }, [loading]);

    useEffect(() => {
      setCookie('accessToken', token, { path: '/', secure: true });
      localStorage.setItem('accessToken', token);
      userInfoAPI();
    }, []);
    return (
      <div
        onClick={() => {
          router.push('/');
        }}
      >
        로그인 성공, 사용자 정보 받아오는중
      </div>
    );
  } else {
    const lezhinLogo = (
      <img
        src={'/images/Lezhin_Comics_Logo.png'}
        alt="lezhin commics"
        className="inline-block w-[25%] rounded-2xl"
      ></img>
    );

    const naverLogo = (
      <img
        src={'/images/Naver_Webtoon_Logo.svg'}
        alt="naver webtoon"
        className="inline-block w-[25%] rounded-2xl"
      ></img>
    );

    const kakaoPageLogo = (
      <img
        src={'/images/Kakao_Page_Logo.png'}
        alt="kakao page"
        className="inline-block w-[25%] rounded-2xl"
      ></img>
    );

    const kakaoWebtoonLogo = (
      <img
        src={'/images/Kakao_Webtoon_Logo.png'}
        alt="kakao webtoon"
        className="inline-block w-[25%] rounded-2xl"
      ></img>
    );

    return (
      <div className="flex h-screen w-screen flex-col">
        <div className="z-[-1]">
          <div className="inline-block">
            {lezhinLogo}
            {kakaoPageLogo}
            {naverLogo}
            {kakaoWebtoonLogo}
          </div>
          <div className="inline-block">
            {kakaoWebtoonLogo}
            {lezhinLogo}
            {kakaoPageLogo}
            {naverLogo}
          </div>
          <div className="inline-block">
            {naverLogo}
            {kakaoWebtoonLogo}
            {lezhinLogo}
            {kakaoPageLogo}
          </div>
        </div>
        <div className="from-40% via-70% to-100% absolute z-[-1] h-screen w-screen bg-gradient-to-t from-black via-black to-gray-300 opacity-80 "></div>
        <div className="top-[10%] flex flex-col items-center">
          <img src="/images/MANAMANA.svg" alt="manamana" className="h-[150px] w-[150px] pb-8"></img>

          <div className=" text-lg text-FontSecondaryDark">
            <p>오직 당신을 위한</p>
            <p>웹툰 추천 플랫폼</p>
          </div>

          <div
            css={css`
              font-weight: bold;
            `}
            className="text-4xl text-FontPrimaryDark"
          >
            마나마나
          </div>

          <a href="https://j8b206.p.ssafy.io/api/oauth2/authorization/kakao">
            <button className="m-8 h-[45px] w-[300px]">
              <img src="images/kakao_login_medium_wide.png" alt="카카오 로그인"></img>
            </button>
          </a>

          <div className="text-center text-FontSecondaryDark">
            <p>Copyright 2023.</p>
            <p>Team-Beef-Jerky All rights reserved.</p>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;

export const getServerSideProps: GetServerSideProps = async context => {
  const { token } = context.query;
  if (token) {
    return { props: { token } };
  } else {
    return { props: { token: null } };
  }
};
