import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';

interface Props {
  token: string | null;
}

function Loginpage({ token }: Props) {
  const router = useRouter();
  if (token) {
    // api 통신 사용자 정보 받아오기 등등 ~
    localStorage.setItem('token', token);
    router.push('/');
    return <div>로그인 성공, 사용자 정보 받아오는중</div>;
  } else {
    alert(`로그인 실패, 토큰값: ${token}`);
    router.push('/login');
    return <div>로그인 실패, 로딩중</div>;
  }
}

export default Loginpage;

export const getServerSideProps: GetServerSideProps = async context => {
  const { token } = context.query;
  if (token) {
    return { props: { token } };
  } else {
    return { props: { token: null } };
  }
};
