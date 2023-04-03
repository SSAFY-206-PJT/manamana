import '@/styles/globals.css';
import type { AppProps } from 'next/app';
import { wrapper } from '../store/index';
import { Provider } from 'react-redux';
import { persistStore } from 'redux-persist';
import { PersistGate } from 'redux-persist/integration/react';
import store from '../store/index';
import { useEffect } from 'react';
import { getCookie } from '@/util/cookie';

const persistor = persistStore(store);

function App({ Component, pageProps }: AppProps) {
  return (
    <Provider store={store}>
      <PersistGate persistor={persistor}>
        <Component {...pageProps} />
      </PersistGate>
    </Provider>
  );
}

export default wrapper.withRedux(App);
