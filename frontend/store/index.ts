import { configureStore, combineReducers, AnyAction, CombinedState } from '@reduxjs/toolkit';
import { createWrapper, HYDRATE } from 'next-redux-wrapper';
import { Reducer } from '@reduxjs/toolkit';
import { persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

import CurSearchTagSlice, { CurSearchTagState } from './CurSearchTagSlice';
import GenreTasteSlice, { GenreTasteState } from './GenreTasteSlice';
import LoginSlice, { IsLogin } from './LoginSlice';

export interface RootState {
  searchTag: CurSearchTagState;
  genreTasteList: GenreTasteState;
  isLogin: IsLogin;
}

const RootReducer = (state: RootState, action: AnyAction): CombinedState<RootState> => {
  if (action.type === HYDRATE) return { ...state, ...action.payload };
  const combinedReducer = combineReducers({
    searchTag: CurSearchTagSlice.reducer,
    genreTasteList: GenreTasteSlice.reducer,
    isLogin: LoginSlice.reducer,
  });
  return combinedReducer(state, action);
};

const persistConfig = {
  key: 'root',
  storage,
  whiteList: ['isLogin'],
};
const persistedReducer = persistReducer(
  persistConfig,
  combineReducers({
    isLogin: LoginSlice.reducer,
  }),
);
const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware => getDefaultMiddleware({ serializableCheck: false }),
});

export default store;

const makeStore = () =>
  configureStore({
    reducer: RootReducer as Reducer<CombinedState<RootState>, AnyAction>,
    devTools: process.env.NODE_ENV === 'development',
  });

export const wrapper = createWrapper(makeStore);
