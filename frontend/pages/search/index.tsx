import Navbar from '../../components/navBar';
import Headerbar from '../../components/headerBar';

export default function SearchPage() {
  return (
    <div>
      <Headerbar showBackBtn={true} pageName="내 웹툰" rightBtn="EDIT" />
      <h1 className="text-3xl font-bold underline">Search Page</h1>
      <Navbar />
    </div>
  );
}
