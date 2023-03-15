import Navbar from '../../components/navBar';
import Headerbar from '../../components/headerBar'

export default function SearchPage() {
  return (
    <div>
      <Headerbar showBackBtn={false} pageName="탐색" showNotiBtn={true} />
      <h1 className="text-3xl font-bold underline">Search Page</h1>
      <Navbar />
    </div>
  );
}
