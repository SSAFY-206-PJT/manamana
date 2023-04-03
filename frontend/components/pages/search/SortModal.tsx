import SwipeableDrawer from '@mui/material/SwipeableDrawer';

interface Props {
  sortOpen: boolean;
  closeModal: () => void;
  // sortType: number;
  onSortChange: (newState: number) => void;
}

export default function SortModal({ sortOpen, closeModal, onSortChange }: Props) {
  const openModal = () => {};

  // 정렬 기준 변경 함수
  const handleButtonClick = (newState: number) => {
    onSortChange(newState);
    closeModal();
  };

  const modalSortType = (
    <div className="flex justify-center">
      <div className="my-6 flex w-2/3 flex-col items-center justify-center">
        <p className="text-center text-2xl text-PrimaryLight">정렬 기준</p>
        <hr className="my-2 w-full border border-PrimaryLight bg-PrimaryLight" />
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={() => {
            handleButtonClick(1);
          }}
        >
          <p className="text-center">조회순</p>
        </button>
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={() => {
            handleButtonClick(2);
          }}
        >
          <p className="text-center">별점 높은 순</p>
        </button>
        <button
          className="flex w-1/2 flex-col items-center justify-center py-3"
          onClick={() => {
            handleButtonClick(3);
          }}
        >
          <p className="text-center">댓글 많은 순</p>
        </button>
      </div>
    </div>
  );
  return (
    <div>
      <SwipeableDrawer anchor={'bottom'} open={sortOpen} onClose={closeModal} onOpen={openModal}>
        {modalSortType}
      </SwipeableDrawer>
    </div>
  );
}
