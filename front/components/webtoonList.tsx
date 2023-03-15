type Props = {
  imageUrl: string;
  webtoonName: string;
};

export default function WebtoonList(props: Props) {
  let imageUrl = props.imageUrl;
  let webtoonName = props.webtoonName;
  return (
    <div className="inline-block">
      <div className="mr-2 inline-block">
        <img src={imageUrl} alt="imageURL" className="w-24 h-32 mt-2"></img>
        <div>{webtoonName}</div>
      </div>
    </div>
  );
}
