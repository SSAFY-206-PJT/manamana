import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import axios from 'axios';
import { getUserInfo } from '../api/detail';

interface Props {
  token: string | null;
}

function Login({ token }: Props) {
  const router = useRouter();
  if (token) {
    // api 통신 사용자 정보 받아오기 등등 ~
    localStorage.setItem('token', token);
    axios.defaults.headers.common['Authorization'] = 'Bearer' + localStorage.getItem('token');

    getUserInfo().then(data => console.log(data));
    return <div>로그인 성공, 사용자 정보 받아오는중</div>;
  } else {
    const test1 = () => {
      fetch('https://j8b206.p.ssafy.io/api/oauth2/authorization/kakao', { credentials: 'include' });
    };
    return (
      <div className="flex h-screen w-full items-center justify-center">
        <a href="https://j8b206.p.ssafy.io/api/oauth2/authorization/kakao">
          <button className="text-4xl">카카오 로그인</button>
        </a>
        <button onClick={test1}>testtest</button>
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
