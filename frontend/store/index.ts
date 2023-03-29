import { configureStore, combineReducers, AnyAction, CombinedState } from '@reduxjs/toolkit';
import { createWrapper, HYDRATE } from 'next-redux-wrapper';
import { Reducer } from '@reduxjs/toolkit';
import CurSearchTagSlice, { CurSearchTagState } from './CurSearchTagSlice';
import GenreTasteSlice, { GenreTasteState } from './GenreTasteSlice';
import LoginSlice, { Login } from './LoginSlice';

export interface RootState {
  searchTag: CurSearchTagState;
  genreTasteList: GenreTasteState;
  login: Login;
}

const RootReducer = (state: RootState, action: AnyAction): CombinedState<RootState> => {
  if (action.type === HYDRATE) return { ...state, ...action.payload };
  const combinedReducer = combineReducers({
    searchTag: CurSearchTagSlice.reducer,
    genreTasteList: GenreTasteSlice.reducer,
    login: LoginSlice.reducer,
  });
  return combinedReducer(state, action);
};

const makeStore = () =>
  configureStore({
    reducer: RootReducer as Reducer<CombinedState<RootState>, AnyAction>,
    devTools: process.env.NODE_ENV === 'development',
  });

export const wrapper = createWrapper(makeStore);
