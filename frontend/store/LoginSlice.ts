import { createSlice } from '@reduxjs/toolkit';

export interface IsLogin {
  isLogin: boolean;
}

const initialState: IsLogin = {
  isLogin: false,
};

const LoginSlice = createSlice({
  name: 'isLogin',
  initialState,
  reducers: {
    setLogin(state, action) {
      state.isLogin = action.payload;
    },
  },
});

export const { setLogin } = LoginSlice.actions;

export default LoginSlice;
