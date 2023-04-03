/** @type {import('next').NextConfig} */
const withPlugins = require('next-compose-plugins');
const withPWA = require('next-pwa')({
  dest: 'public',
});

const nextConfig = {
  reactStrictMode: true,
  async rewrites() {
    return [
      {
        source: '/mana/:path*',
        destination: 'https://j8b206.p.ssafy.io/api/:path*',
      },
    ];
  },
  images: {
    remotePatterns: [
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
      use: ['@svgr/webpack'],
    });

    config.module.rules.push({
      test: /\.(ts|tsx)$/i,
      use: {
        loader: 'babel-loader',
        options: {
          presets: ['next/babel'],
        },
      },
    });
    return config;
  },
};
module.exports = withPWA(nextConfig);
