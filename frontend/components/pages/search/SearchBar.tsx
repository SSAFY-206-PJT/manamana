import Image from "next/image";
import {ChangeEvent, useState} from "react";

interface Props {
    onSearchBarChange: (e:ChangeEvent<HTMLInputElement>) => void;
}

export default function SearchBar(props: Props) {  
    const [searchContent, setSearchContent] = useState<string>("");

    /*
      * @Method
      * 입력이 변경됐을 경우 실행되는 메소드
      * */
    const onChangeSearchContent = (event: ChangeEvent<HTMLInputElement>) => {
        setSearchContent(event.target.value);
        props.onSearchBarChange(event);
    };

    const onClickDeleteSearchContent = (event: any) => {
        setSearchContent('');
    }

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
            <input className="ml-4 w-full outline-none" placeholder="웹툰 검색" onChange={onChangeSearchContent} value={searchContent} />
            {
                searchContent.length > 0 ?
                    <div 
                    className="flex top-1 justify-center items-center"
                    onClick={onClickDeleteSearchContent}
                    >
                        <Image
                            alt='delete content'
                            src={'/images/Delete_Gray_Circle.png'}
                            width={20}
                            height={20}
                        />
                    </div>
                    :
                    <div></div>
            }
        </div>
    );
}