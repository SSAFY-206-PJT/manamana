import Link from 'next/link'

type Props = {
  categoryTitle: string;
};

export default function webtoonContainer(props: Props) {
  let categoryTitle = props.categoryTitle
  let rightBtn = <></>
  switch (categoryTitle) {
    case '내가 보는 웹툰':
      rightBtn = (
        <img src='/images/goOtherPage.png' alt='goMyWebtoon'></img>
      )
  }
  return (
    <div className="flex justify-between bg-BackgroundLightComponent rounded-lg">
      <div className="text-lg font-bold">{categoryTitle}</div>
      <div className="flex items-center">
        <Link href='/my-webtoon'>
          {rightBtn}
        </Link>
      </div>
    </div>
  )
}