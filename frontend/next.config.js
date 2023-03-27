/** @type {import('next').NextConfig} */

const nextConfig = {

  reactStrictMode: true,
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'i.namu.wiki',
        port: '',
        pathname: '/i/**',
      },
      {
        protocol: 'https',
        hostname: 'image-comic.pstatic.net',
        port: '',
        pathname: '/webtoon/**',
      },
    ],
  },
  webpack: config => {
    // 아래를 추가합니다.
    config.module.rules.push({
      test: /\.svg$/i,
      issuer: /\.[jt]sx?$/,
      use: ["@svgr/webpack"]
    });

    config.module.rules.push({
      test: /\.(ts|tsx)$/i,
      use: {
        loader: "babel-loader",
        options: {
          presets: ["next/babel"],
        },
      },
    })
    return config;
  }
};

module.exports = nextConfig;
