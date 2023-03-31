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
      <div className="h-6 w-12">
        <img src="/images/HeaderBar_Back.png" alt="goBack" onClick={() => router.back()}></img>
      </div>
    ) : // 왼쪽 : 뒤로가기, 오른쪽 : 편집 버튼이 아닌 경우
    showBackBtn ? (
      <div className="">
        <img src="/images/HeaderBar_Back.png" alt="goBack" onClick={() => router.back()}></img>
      </div>
    ) : (
      // 왼쪽에 뒤로가기가 없는 경우
      <div className="h-6 w-6"></div>
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
        <div className="flex h-6 w-12 items-center justify-center rounded-md border-2 border-BackgroundLightComponentBolder text-sm">
          편집
        </div>
      );
      break;
    default:
      showRightBtn = <div className="h-6 w-6"></div>;
      break;
  }

  return (
    <div className="z-30 flex h-14 items-center justify-between bg-BackgroundLightComponent p-5">
      {backBtn}
      <div className="">
        <p className="text-xl font-bold">{pageName}</p>
      </div>
      {showRightBtn}
    </div>
  );
}
