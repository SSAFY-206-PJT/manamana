type Props = {
  imageUrl: string;
  webtoonName: string;
};

export default function WebtoonItem(props: Props) {
  let imageUrl = props.imageUrl;
  let webtoonName = props.webtoonName;
  return (
    <div className="inline-block">
      <div className="mr-2 inline-block">
        <img src={imageUrl} alt="imageURL" className="w-20 h-28 mt-2"></img>
        <div>{webtoonName}</div>
      </div>
    </div>
  );
}
