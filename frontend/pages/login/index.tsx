import MovingLogo from '@/components/pages/login/MovingLogo';
import { css } from "@emotion/react";
import MNMNLogo from '../../public/images/MANAMANA.svg';
import Image from 'next/image';

export default function LoginPage() {

  const loginClick = () => {
    alert("login clicked");
  };

  return (
    <div className='flex flex-col bg-gray-800 w-screen h-screen'>
      <div>
        <MovingLogo direction='left' startIdx={2}></MovingLogo>
        <MovingLogo direction='right' startIdx={1}></MovingLogo>
        <MovingLogo direction='left' startIdx={0}></MovingLogo>
        <MovingLogo direction='right' startIdx={3}></MovingLogo>
      </div>
      <div css={
        css`
        z-index: 1;
        background: linear-gradient(180deg, rgba(0, 0, 0, 0.58) 28.23%, #000000 52.04%);
        width: 100%;
        height: 100vh;
        position: absolute;
        `
      }
        className='flex flex-col justify-center items-center'
      >
        <MNMNLogo width='150' height='150' />

        <div className='text-FontSecondaryDark mb-5 m-12 text-lg'>
          <p>오직 당신을 위한</p>
          <p>웹툰 추천 플랫폼</p>
        </div>

        <div 
        css={
          css`
            font-weight: bold;
          `
        }
        className='text-FontPrimaryDark m-8 mt-6 text-4xl'>
          마나마나
        </div>

        <button 
        className='w-80 h-12 m-8'
        css={
          css`
          background-image: url("/images/kakao_login_medium_wide.png");
          background-size: cover;
          cursor: pointer; 
          `
        }
        onClick={
          loginClick
        }
        ></button>

        <div className='text-FontSecondaryDark text-center m-8'>
          <p>Copyright 2023.</p>
          <p>Team-Beef-Jerky All rights reserved.</p>
        </div>
      </div>
    </div>
  );
}
