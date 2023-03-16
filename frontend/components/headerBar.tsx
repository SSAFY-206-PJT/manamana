import { useRouter } from 'next/router';
import Link from 'next/link';

type Props = {
  showBackBtn: Boolean;
  pageName?: string;
  rightBtn?: 'EDIT' | 'NOTI' | undefined;
};

export default function Headerbar(props: Props) {
  const router = useRouter();

  let showBackBtn = props.showBackBtn;
  let pageName = props.pageName;
  let rightBtnOpt = props.rightBtn;

  // 왼쪽 버튼 (뒤로가기)
  let backBtn =
    // 왼쪽 : 뒤로가기, 오른쪽 : 편집 버튼인 경우
    showBackBtn && rightBtnOpt === 'EDIT' ? (
      <div className="w-12 h-6">
        <img src="/images/HeaderBar_Back.png" alt="goBack" onClick={() => router.back()}></img>
      </div>
    ) : // 왼쪽 : 뒤로가기, 오른쪽 : 편집 버튼이 아닌 경우
    showBackBtn ? (
      <div className="">
        <img src="/images/HeaderBar_Back.png" alt="goBack" onClick={() => router.back()}></img>
      </div>
    ) : (
      // 왼쪽에 뒤로가기가 없는 경우
      <div className="w-6 h-6"></div>
    );

  // 오른쪽 버튼 (알림, 편집, null)
  let showRightBtn = null;
  switch (rightBtnOpt) {
    case 'NOTI': // 알림
      showRightBtn = (
        <div>
          <Link href="/notification">
            <img src="/images/HeaderBar_Noti.png" alt="notification"></img>
          </Link>
        </div>
      );
      break;
    case 'EDIT': // 편집
      showRightBtn = (
        <div className="flex justify-center items-center w-12 h-6 text-sm border-2 rounded-md border-BackgroundLightComponentBolder">
          편집
        </div>
      );
      break;
    default:
      showRightBtn = <div className="w-6 h-6"></div>;
      break;
  }

  return (
    <div className="flex justify-between items-center p-5 h-14 bg-BackgroundLightComponent">
      {backBtn}
      <div className="">
        <p className="font-bold text-xl">{pageName}</p>
      </div>
      {showRightBtn}
    </div>
  );
}
