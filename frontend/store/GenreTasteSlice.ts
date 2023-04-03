import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface GenreTasteState {
  genreTasteList: number[];
}

const initialState: GenreTasteState = {
  genreTasteList: [],
};

const GenreTasteSlice = createSlice({
  name: 'genreTasteList',
  initialState,
  reducers: {
    changeGenreTaste: (state: GenreTasteState, action: PayloadAction<number[]>) => {
      state.genreTasteList = action.payload;
      console.log(state.genreTasteList);
    },
  },
});

export const { changeGenreTaste } = GenreTasteSlice.actions;
export default GenreTasteSlice;
