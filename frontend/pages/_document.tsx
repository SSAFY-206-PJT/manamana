import { Html, Head, Main, NextScript } from 'next/document';

export default function Document() {
  return (
    <Html lang="en">
      <Head>
        <link rel="manifest" href="/manifest.json" />
        <meta name="theme-color" content="#BE3455" />
        <link href="/images/favicons/favicon-16x16.png" rel="icon" type="image/png" sizes="16x16" />
        <link href="/images/favicons/favicon-32x32.png" rel="icon" type="image/png" sizes="32x32" />
        <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
      </Head>
      <body className="h-screen">
        <Main />
        <NextScript />
      </body>
    </Html>
  );
}
