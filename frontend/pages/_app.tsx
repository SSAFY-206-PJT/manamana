import '@/styles/globals.css';
import type { AppProps } from 'next/app';
import { wrapper } from "../store/index";

function App({ Component, pageProps }: AppProps) {
  return (
    <Component {...pageProps} />
  );
}

export default wrapper.withRedux(App);