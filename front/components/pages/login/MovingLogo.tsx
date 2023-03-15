import Image from 'next/image'
import { css } from "@emotion/react";
import React, { useRef } from "react";

export default function MovingLogo(props: {
    direction: string;
    startIdx: number;
}) {

    /*
    *   @variable
    *   로고 이미지 변수로 지정
    */
    const lezhinLogo = <Image
        src="/images/Lezhin_Comics_Logo.png"
        alt={'lezhin comics'}
        width={'150'}
        height={'150'}
        className='inline-block rounded-3xl'
    />;

    const naverLogo = <Image
        src="/images/Naver_Webtoon_Logo.svg"
        alt={'naver webtoon'}
        width={'150'}
        height={'150'}
        className='inline-block rounded-3xl'
    />;

    const kakaoPageLogo = <Image
        src="/images/Kakao_Page_Logo.png"
        alt={'kakao page'}
        width={'150'}
        height={'150'}
        className='inline-block rounded-3xl'
    />;

    const kakaoWebtoonLogo = <Image
        src="/images/Kakao_Webtoon_Logo.png"
        alt={'kakao webtoon'}
        width={'150'}
        height={'150'}
        className='inline-block rounded-3xl'
    />;

    /*
    *   @variable
    *   로고 이미지 변수 배열에 저장
    */
    const LogoType = [lezhinLogo, naverLogo, kakaoPageLogo, kakaoWebtoonLogo];

    /*
    *   @variable
    *   로고 이미지를 저장할 배열
    */
    let LogoList = [];

    /*
    *   @loop
    *   로고 이미지를 저장하기 위한 반복문
    */
    for (let i = 0; i < 40; i++) {
        LogoList.push(LogoType[(props.startIdx + i) % LogoType.length]);
    }


    /*
    *   @Method
    *   왼쪽으로 움직이는 코드
    */
    let moveLeft = () => {
        return (css`
        animation: 50s linear 1s infinite normal none running moveLeft;

        @keyframes moveLeft {
            from {
                transform: translateX(0);
            }
            to {
                transform: translateX(-100%);
            }
        }
    `);
    }

    let position = 0;
    const containerRef = useRef<HTMLDivElement>(null);
    const logoRef = useRef<HTMLDivElement>(null);

    /*
    *   @Method
    *   오른쪽으로 움직이는 코드
    */
    let moveRight = () => {
        if (logoRef.current != null && containerRef.current != null) {
            position += 5;
            logoRef.current.style.left = `${position}px`;

            if (position >= containerRef.current.offsetWidth) {
                position = 0;

            }

            setTimeout(moveRight, 10);
        }

        console.dir(logoRef.current);

        return (
            css``
            //         `
            //     animation: 50s linear 1s infinite normal none running moveRight;

            //     @keyframes moveRight {
            //         to {
            //             transform: translateX(100%);
            //         }
            //     }
            // `
        );
    }




    return (<div
        className='w-full overflow-hidden h-fit'
        ref={containerRef}
        css={
            css`
            `
        }
    >
        <div
            ref={logoRef}
            css={
                props.direction == "left" ? moveLeft() : moveRight()
            }
            className='float-left flex'>
            {LogoList}
        </div>
        {/* <div
            ref={logoRef}
            css={
                props.direction == "left" ? moveLeft() : moveRight()
            }
            className='float-left flex'>
            {LogoList}
        </div> */}
    </div>);
}
