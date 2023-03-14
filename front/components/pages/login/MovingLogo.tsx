import Image from 'next/image'

export default function MovingLogo() {

    //  https://blogpack.tistory.com/1120
    // //인터벌 메서드로 애니메이션 생성
    // let rollerWidth = document.querySelector('.roller ul').offsetWidth;//회전 배너 너비값
    // let betweenDistance = 1;//이동 크기 - 정수여야 함
    // originalID = window.setInterval(betweenRollCallback, parseInt(1000 / 100), betweenDistance, document.querySelector('#roller1'));
    // cloneID = window.setInterval(betweenRollCallback, parseInt(1000 / 100), betweenDistance, document.querySelector('#roller2'));

    // //인터벌 애니메이션 함수(공용)
    // function betweenRollCallback(d, roller) {
    //     let left = parseInt(roller.style.left);
    //     roller.style.left = (left - d) + 'px';//이동
    //     //조건부 위치 리셋
    //     if (rollerWidth + (left - d) <= 0) {
    //         roller.style.left = rollerWidth + 'px';
    //     }
    // }

    return (<div>
        <div className='container overflow-visible flex flex-row -translate-x-2 transition-transform duration-100'>
            <Image
                src="/images/Lezhin_Comics_Logo.png"
                alt={'lezhin comics'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
            <Image
                src="/images/Naver_Webtoon_Logo.png"
                alt={'naver webtoon'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
            <Image
                src="/images/Kakao_Page_Logo.png"
                alt={'kakao page'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
            <Image
                src="/images/Kakao_Webtoon_Logo.png"
                alt={'kakao webtoon'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
        </div>
        {/* <div className='container overflow-visible flex flex-row translate-x-2'>
            <Image
                src="/images/Lezhin_Comics_Logo.png"
                alt={'lezhin comics'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
            <Image
                src="/images/Naver_Webtoon_Logo.png"
                alt={'naver webtoon'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
            <Image
                src="/images/Kakao_Page_Logo.png"
                alt={'kakao page'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
            <Image
                src="/images/Kakao_Webtoon_Logo.png"
                alt={'kakao webtoon'}
                width={'100'}
                height={'100'}
                className='inline-block rounded-3xl'
            />
        </div> */}
    </div>);
}
