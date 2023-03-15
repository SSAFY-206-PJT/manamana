import { useRouter } from 'next/router';
import Link from 'next/link';

/**
 *
 * @param {Boolean} showBackBtn 뒤로가기 버튼 유무
 * @param {string} pageName 페이지 이름
 * @param {Boolean} showNotiBtn 알림 버튼 유무
 * @returns
 */
type Props = {
  showBackBtn: Boolean;
  pageName: string;
  showNotiBtn: Boolean;
};

export default function Headerbar(props: Props) {
  const router = useRouter();

  let showBackBtn = props.showBackBtn;
  let pageName = props.pageName;
  let showNotiBtn = props.showNotiBtn;

  // 뒤로가기 버튼
  let backBtn = showBackBtn ? (
    <div className="">
      <img src="/images/HeaderBar_Back.png" alt="goBack" onClick={() => router.back()}></img>
    </div>
  ) : (
    <div className="w-6 h-6"></div>
  );

  // 알림 버튼
  let notiBtn = showNotiBtn ? (
    <div>
      <Link href="/notification">
        <img src="/images/HeaderBar_Noti.png" alt="notification"></img>
      </Link>
    </div>
  ) : (
    <div className="w-6 h-6"></div>
  );

  return (
    <div className="flex justify-between items-center p-5 h-14 bg-BackgroundLightComponent">
      {backBtn}
      <div className="">
        <p className="font-bold text-xl">{pageName}</p>
      </div>
      {notiBtn}
    </div>
  );
}
