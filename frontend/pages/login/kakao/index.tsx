import { useEffect } from 'react';
import { GetServerSideProps } from 'next';

interface Props {
  code: string;
}
function KakaoLogin({ code }: Props) {
  useEffect(() => {
    console.log(code);
  }, []);
  return (
    <div>
      <div>dd</div>
    </div>
  );
}

export default KakaoLogin;

export const getServerSideProps: GetServerSideProps = async context => {
  const { code } = context.query;

  return { props: { code: code } };
};
