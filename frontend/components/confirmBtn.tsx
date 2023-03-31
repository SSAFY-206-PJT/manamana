function ConfirmBtn({ cancel, confirm }: any) {
  return (
    <div className="flex h-full w-full justify-center">
      <button
        className="m-2 flex w-1/2 items-center justify-center rounded border border-PrimaryLight bg-white py-1"
        onClick={cancel}
      >
        <p className="text-center text-PrimaryLight">취소</p>
      </button>
      <button
        className="m-2 flex w-1/2 items-center justify-center rounded border border-PrimaryLight bg-PrimaryLight py-1"
        onClick={confirm}
      >
        <p className="text-center text-white">확인</p>
      </button>
    </div>
  );
}

export default ConfirmBtn;
