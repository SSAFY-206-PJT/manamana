import { createSlice, PayloadAction } from "@reduxjs/toolkit";

export interface CurSearchTagState {
    tags: string[];
}

const initialState: CurSearchTagState = {
    tags: [],
};

const CurSearchTagSlice = createSlice({
    name: 'CurSearchTag',
    initialState,
    reducers: {
        changeCurSearchTag: (
            state: CurSearchTagState,
            action: PayloadAction<string[]>
        ) => {
            state.tags = action.payload;
        },
        getCurSearchTag: (state: CurSearchTagState) => {
            state.tags;
        },
        deleteCurSearchOneTag: (
            state: CurSearchTagState,
            action: PayloadAction<string>
        ) => {
            let newState = [...state.tags];
            for(let i = 0; i < newState.length; i++){
                if(newState[i] === action.payload){
                    newState.splice(i, 1);
                    break;
                }
            }
            state.tags = newState;
        }
    },
});

export const { changeCurSearchTag, deleteCurSearchOneTag } = CurSearchTagSlice.actions;
export default CurSearchTagSlice;