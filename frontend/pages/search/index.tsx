import Navbar from '../../components/common/Navbar';
import Headerbar from '../../components/common/Headerbar';

export default function SearchPage() {
  return (
    <div>
      <Headerbar showBackBtn={true} pageName="내 웹툰" rightBtn="EDIT" />
      <h1 className="text-3xl font-bold underline">Search Page</h1>
      <Navbar />
    </div>
  );
}
