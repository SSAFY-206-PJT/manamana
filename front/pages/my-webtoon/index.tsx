import Headerbar from "@/components/headerBar";

export default function MyWebtoonPage() {
  return (
    <div>
      <Headerbar showBackBtn={true} pageName='내 웹툰' rightBtn='EDIT'></Headerbar>
      <h1 className="text-3xl font-bold underline">My-Webtoon Page</h1>;
    </div>
  )
}
