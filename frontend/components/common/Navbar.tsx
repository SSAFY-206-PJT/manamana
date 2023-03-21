import Link from 'next/link';

export default function Navbar() {
  return (
    <div className="fixed bottom-[-1px] left-0 right-0 flex w-screen bg-FooterBackground">
      <div className="flex grow justify-center">
        <Link href="/">
          <img className="p-3" src="/images/NavBar_Home.png" alt="GoHome"></img>
        </Link>
      </div>
      <div className="flex grow justify-center">
        <Link href="/search">
          <img className="p-3" src="/images/NavBar_Search.png" alt="GoSearch"></img>
        </Link>
      </div>
      <div className="flex grow justify-center">
        <Link href="/profile">
          <img className="p-3" src="/images/NavBar_User.png" alt="GoUser"></img>
        </Link>
      </div>
    </div>
  );
}
