import { useRouter } from 'next/router';


// import { AppProps } from 'next/app'
// { showBachBtn, pageName, showNotiBtn}: AppProps 
export default function Headerbar() {
  const router = useRouter()

  let showBachBtn = true
  let showNotiBtn = true

  let pageName = '알림'

  // 뒤로가기 버튼
  let backBtn = showBachBtn ? (
    <div className="">
      <img src="/images/HeaderBar_Back.png" alt='goBack' onClick={() => router.back()}></img>
    </div>
  ) : <></>
  
  // 알림 버튼
  let notiBtn = showNotiBtn ? (
    <div className="">
      <img src="/images/HeaderBar_Noti.png" alt='notification' onClick={() => router.push('/notification')}></img>
    </div>
  ) : <> </>

  return (
    <div className="flex justify-between items-center p-5 h-14 bg-BackgroundLightComponent">
      { backBtn }
      <div className="">
        <p className="font-bold text-xl">{ pageName }</p>
      </div>
      { notiBtn }
    </div>
  )
}
