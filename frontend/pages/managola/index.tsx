import Headerbar from "@/components/common/Headerbar";
import ManagolaCanvas from "@/components/pages/managola/managolaCanvas";

export default function ManagolaPage() {
  return (
    <div className="w-full h-screen overflow-hidden">
      <Headerbar showBackBtn={true} pageName={"마나골라"} rightBtn={undefined} key={"마나골라"} />
      <div className="h-full bg-slate-600">
        <ManagolaCanvas />
      </div>
    </div>
  );
}
