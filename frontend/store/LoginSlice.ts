import { createSlice } from '@reduxjs/toolkit';

export interface Login {
  login: boolean;
}

const initialState: Login = {
  login: false,
};

const LoginSlice = createSlice({
  name: 'login',
  initialState,
  reducers: {
    setLogin(state, action) {
      state.login = action.payload;
    },
  },
});

export const { setLogin } = LoginSlice.actions;

export default LoginSlice;
