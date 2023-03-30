import { getCookie, setCookie } from '@/util/cookie';
import { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { setLogin } from '@/store/LoginSlice';
import { RootState } from '@/store';

function cookietest() {
  const makecookie = () => {
    setCookie('myToken', 'thisistokenmanamana', {
      path: '/',
      secure: true,
      sameSite: 'none',
    });
  };

  const [dd, setdd] = useState('');

  const mycookie = () => {
    const answer = getCookie('myToken');
    if (answer) {
      setdd(answer);
    }
  };

  const dispatch = useDispatch();
  const gologin = () => {
    const token = 'asdfasdf';
    dispatch(setLogin(true));
  };
  const answer = useSelector((state: RootState) => state.isLogin);
  const [isLogin, setIsLogin] = useState('로그인 아직 안했음');
  const getlogin = () => {
    if (answer.isLogin) {
      setIsLogin('로그인 성공');
    }
  };

  return (
    <div>
      <div>dd</div>
      <button onClick={makecookie}>setCookie</button>
      <hr className="my-3" />
      <button onClick={mycookie}>getCookie</button>
      <hr className="my-3" />
      <p>{dd}</p>
      <hr className="my-3" />
      <button onClick={gologin}>로그인하기</button>
      <hr className="my-3" />
      <button onClick={getlogin}>로그인여부 받아오기</button>
      <hr className="my-3" />
      <p>{isLogin}</p>
    </div>
  );
}

export default cookietest;
