import { useRouter } from 'next/router';

export default function CommentPage() {
  const router = useRouter();
  const { webtoon_id } = router.query;
  const { WEBTOON_GRADATION_COLOR } = router.query;

  return (
    <div>
      <h1 className="text-3xl font-bold underline">Detail Comment Page</h1>
      <h1>id는 {webtoon_id}</h1>
      <h1>색깔은 {WEBTOON_GRADATION_COLOR}</h1>
    </div>
  );
}
