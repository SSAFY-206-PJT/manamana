import { useState, useEffect } from 'react';
import styles from './Top10.module.css';
import axios from 'axios';

interface Top10 {
  rank: number;
  keyword: string;
}

export default function RollingBanner() {
  const [currentIdx, setCurrentIdx] = useState<number>(0);
  const [items, setItems] = useState<Top10[]>([])

  // Top10 데이터 가져오기
  useEffect(() => {
    axios.get('https://j8b206.p.ssafy.io/api/webtoons/list/keywords').then(response => {
      const data: Top10[] = response.data.result.map((item: Top10) => {
        return {
          rank: item.rank,
          keyword: item.keyword,
        }
      })
      setItems(data);
    })
  },[]);

  useEffect(() => {
    if (items.length > 0) {
      const interval = setInterval(() => {
        setCurrentIdx(currentIdx => (currentIdx + 1) % items.length);
      }, 3000);
      return () => clearInterval(interval);
    }
  }, [items]);


  const getPrevIndex = (index: number) => (index + items.length - 1) % items.length;
  const getNextIndex = (index: number) => (index + 2) % items.length;

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
                <span className={styles.rankitemfont}>{item.keyword}</span>
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
}