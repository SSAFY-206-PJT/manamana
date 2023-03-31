import { GetServerSideProps } from 'next';
import { useEffect } from 'react';
import { useRouter } from 'next/router';
import axios from 'axios';
import { getUserInfo } from '../api/detail';

interface Props {
  token: string | null;
}

function Login({ token }: Props) {
  if (token) {
    const router = useRouter();
    const getInfo = async () => {
      const data = await getUserInfo();
      // redux에 유저 정보 저장
      router.push('/');
    };

    useEffect(() => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      getInfo();
    }, []);
    return <div>로그인 성공, 사용자 정보 받아오는중</div>;
  } else {
    return (
      <div className="flex h-screen w-full items-center justify-center">
        <a href="https://j8b206.p.ssafy.io/api/oauth2/authorization/kakao">
          <button className="text-4xl">카카오 로그인</button>
        </a>
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
