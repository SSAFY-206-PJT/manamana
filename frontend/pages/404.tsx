import Link from 'next/link';
import Headerbar from '../components/common/Headerbar';
import Navbar from '../components/common/Navbar';
const NotFoundPage = () => {
  return (
    <div className="h-screen">
      <Headerbar showBackBtn={true} />
      <div className="absolute top-0 flex h-screen w-screen flex-col items-center justify-center">
        <div className="mb-6 border-2 p-4 text-center">
          <p className="text-2xl">404 Page Not Found</p>
          <p className="text-2xl">페이지를 찾을 수 없습니다.</p>
        </div>
        <div>
          <Link
            href="/"
            className="flex items-center justify-center rounded-full text-lg font-bold"
          >
            <img src="/images/404icon.png" alt="icon"></img>
          </Link>
          <Link
            href="/"
            className="flex animate-blink items-center justify-center rounded-full text-lg font-bold"
          >
            <p>홈으로 돌아가기</p>
          </Link>
        </div>
      </div>
      <Navbar />
    </div>
  );
};
export default NotFoundPage;

// function NotFoundPage() {

//   return (
//     <div className={styles.container}>
//       <img src={groom} alt='' className={styles.groomimg}></img>
//       <div className={styles.margin}>
//         <div className={styles.header}>해당 페이지를 찾을 수 없습니다.</div>
//         <div className={styles.header}>(404 Not Found)</div>
//       </div>
//       <div clasName={styles.margin}>
//         <div className={styles.body}>페이지가 존재하지 않거나, 사용할 수 없는 페이지입니다.</div>
//         <div className={styles.body}>입력하신 주소가 정확한지 확인해 주시기 바랍니다.</div>
//       </div>
//       <div>

//       <Link to="/main">
//         <div className={styles.gohome}>홈으로 돌아가기 →</div>
//       </Link>

//       </div>
//     </div>
//   )
// }
