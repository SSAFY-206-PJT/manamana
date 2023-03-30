import { createSlice } from '@reduxjs/toolkit';

export interface IsLogin {
  isLogin: boolean;
  userId: number | null;
  email: string | null;
  nickname: string | null;
  imagePath: string | null;
  gender: string | null;
  age: number | null;
  likeCount: number;
  scoreCount: number;
}

const initialState: IsLogin = {
  isLogin: false,
  userId: null,
  email: null,
  nickname: null,
  imagePath: null,
  gender: null,
  age: null,
  likeCount: 0,
  scoreCount: 0,
};

const LoginSlice = createSlice({
  name: 'isLogin',
  initialState,
  reducers: {
    setLogin(state, action) {
      state.isLogin = action.payload;
    },
    setUserInfo(state, action) {
      state.userId = action.payload.userId;
      state.email = action.payload.email;
      state.nickname = action.payload.nickname;
      state.imagePath = action.payload.imagePath;
      state.gender = action.payload.gender;
      state.age = action.payload.age;
      state.likeCount = action.payload.likeCount;
      state.scoreCount = action.payload.scoreCount;
    },
  },
});

export const { setLogin, setUserInfo } = LoginSlice.actions;

export default LoginSlice;
