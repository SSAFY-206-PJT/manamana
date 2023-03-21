import Headerbar from '../../components/common/Headerbar';
export default function GenreTastePage() {
  return (
    <div className="h-screen bg-BackgroundLight">
      <Headerbar showBackBtn={true} pageName={'취향선택'} />
      <div>선호하는 장르를 선택해주세요</div>
    </div>
  );
}
