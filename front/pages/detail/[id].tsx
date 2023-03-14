import { useRouter } from 'next/router';

export default function DetailPage() {
  const router = useRouter();
  const { id } = router.query;
  return (
    <div>
      <h1 className="text-3xl font-bold underline">Detail Page</h1>
      <h1>id는 {id}</h1>
    </div>
  );
}
