import { useState, useEffect } from 'react';
import styles from './Top10.module.css';

export default function RollingBanner() {
  const [currentIdx, setCurrentIdx] = useState<number>(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIdx(currentIdx => (currentIdx + 1) % items.length);
    }, 3000);
    return () => clearInterval(interval);
  }, []);

  let items = [
    { id: 1, rank: 1, webtoonname: '1초' },
    { id: 2, rank: 2, webtoonname: '호랑이행님' },
    {
      id: 3,
      rank: 3,
      webtoonname: '재벌집 막내아들',
    },
    { id: 4, rank: 4, webtoonname: '신의 탑' },
    { id: 5, rank: 5, webtoonname: '이름이 긴 웹툰이 있을 수도 있으니까12345678' },
    { id: 6, rank: 6, webtoonname: '기안84' },
  ];

  const getPrevIndex = (index: number) => (index + items.length - 1) % items.length;
  const getNextIndex = (index: number) => (index + 2) % items.length;

  // // 글자수 확인 함수
  // const shortenWords = (str: string) => {
  //   let result = '';
  //   if (str.length > 20) {
  //     result = str.substr(0, 15) + '...';
  //   } else {
  //     result = str;
  //   }
  //   return result;
  // };

  return (
    <div className={styles.rollingbanner}>
      <div className={styles.leftitem}>
        <img src="/images/Main_Chatgrow.png" alt="top10" className={styles.chatgrowicon}></img>
        <div className={styles.title}>TOP 10</div>
      </div>
      <div className={styles.wrap}>
        <ul>
          {items.map((item, index) => {
            let classObj = {};
            if (index === getPrevIndex(currentIdx)) {
              classObj = { className: styles.prev };
            } else if (index === currentIdx) {
              classObj = { className: styles.current };
            } else if (index === getNextIndex(currentIdx)) {
              classObj = { className: styles.next };
            }
            return (
              <li key={index} {...classObj}>
                <span className={styles.rankfont}>{index + 1}</span>
                &nbsp; &nbsp;
                <span className={styles.rankitemfont}>{item.webtoonname}</span>
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
}
