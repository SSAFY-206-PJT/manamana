import Navbar from '../components/navBar';
import Headerbar from'../components/headerBar'

export default function Home() {
  return (
    <>
    <Headerbar />
      <h1 className="text-3xl text-PrimaryLight font-bold underline">Hello world!</h1>
      <Navbar />
    </>
  );
}
