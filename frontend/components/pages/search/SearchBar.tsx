import Image from "next/image";

export default function SearchBar(){
    return (
        <div className="border-2 border-BackgroundLightComponentBorder rounded-2xl flex flex-row pl-4 pr-4 pt-1 pb-1 mb-4">
            <div className="flex justify-center items-center">
                <Image
                src={'/images/search-img.svg'}
                alt='search bar logo'
                width={20}
                height={20}
                />
            </div>
            <input className="ml-4 w-full outline-none" placeholder="웹툰 검색" />
        </div>
    );
}