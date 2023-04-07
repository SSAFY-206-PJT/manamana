import Image from 'next/image';

interface Data {
  key: number;
  value: string;
}

interface Props {
  tagData: Data;
  deleteTag: (data: Data) => void;
}

export default function SearchTag(props: Props) {
  const ondeleteTagClick = () => {
    props.deleteTag(props.tagData);
  };

  return (
    <div className="flex items-center justify-center gap-2 rounded-xl bg-PrimaryLight pb-1 pl-2 pr-2 pt-1 text-FontPrimaryDark">
      <div className="inline-block">{props.tagData.value}</div>
      <div className="inline-block" onClick={ondeleteTagClick}>
        <img src={'/images/Delete_white.png'} alt="delete tag" className="h-2 w-2"></img>
      </div>
    </div>
  );
}
