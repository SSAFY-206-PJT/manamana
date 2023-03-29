function LoginTest() {
  const login = () => {
    window.Kakao.Auth.authorize({
      redirectUri: process.env.NEXT_PUBLIC_KAKAO_REDIRECT_URI,
    });
  };
  return (
    <div>
      <div onClick={login}>로그인</div>
    </div>
  );
}

export default LoginTest;
