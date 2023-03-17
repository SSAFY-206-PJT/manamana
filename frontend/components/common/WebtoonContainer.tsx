import Link from 'next/link';

type Props = {
  categoryTitle: string;
  rightBtn?: Boolean;
};

export default function webtoonContainer(props: Props) {
  let categoryTitle = props.categoryTitle;
  let showRightBtn = props.rightBtn;

  return (
    <div className="flex justify-between rounded-lg bg-BackgroundLightComponent">
      <div className="text-lg font-bold">{categoryTitle}</div>
      {showRightBtn ? (
        <div className="flex items-center">
          <Link href="/my-webtoon">
            <img src="/images/goOtherPage.png" alt="goOtherPage"></img>
          </Link>
        </div>
      ) : (
        <></>
      )}
    </div>
  );
}
