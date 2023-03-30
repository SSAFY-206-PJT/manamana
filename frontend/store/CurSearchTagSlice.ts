import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface Data {
    key: number,
    value: string
}


export interface CurSearchTagState {
    days: Data[];
    genres: Data[];
    grades: Data[];
    status: Data[];
}

const initialState: CurSearchTagState = {
    days: [],
    genres: [],
    grades: [],
    status: [],
};

const CurSearchTagSlice = createSlice({
    name: 'CurSearchTag',
    initialState,
    reducers: {
        changeDays: (
            state: CurSearchTagState,
            action: PayloadAction<Data[]>
        ) => {
            state.days = action.payload;
        },
        changeGenres: (
            state: CurSearchTagState,
            action: PayloadAction<Data[]>
        ) => {
            state.genres = action.payload;
        },
        changeGrades: (
            state: CurSearchTagState,
            action: PayloadAction<Data[]>
        ) => {
            state.grades = action.payload;
        },
        changeStatus: (
            state: CurSearchTagState,
            action: PayloadAction<Data[]>
        ) => {
            state.status = action.payload;
        },
        deleteDayTag: (
            state: CurSearchTagState,
            action: PayloadAction<Data>
        ) => {
            let newState = [...state.days];
            for (let i = 0; i < newState.length; i++) {
                if (newState[i].key === action.payload.key) {
                    newState.splice(i, 1);
                    break;
                }
            }
            state.days = newState;
        },
        deleteGenreTag: (
            state: CurSearchTagState,
            action: PayloadAction<Data>
        ) => {
            let newState = [...state.genres];
            for (let i = 0; i < newState.length; i++) {
                if (newState[i].key === action.payload.key) {
                    newState.splice(i, 1);
                    break;
                }
            }
            state.genres = newState;
        },
        deleteGradeTag: (
            state: CurSearchTagState,
            action: PayloadAction<Data>
        ) => {
            let newState = [...state.grades];
            for (let i = 0; i < newState.length; i++) {
                if (newState[i].key === action.payload.key) {
                    newState.splice(i, 1);
                    break;
                }
            }
            state.grades = newState;
        },
        deleteStatusTag: (
            state: CurSearchTagState,
            action: PayloadAction<Data>
        ) => {
            let newState = [...state.status];
            for (let i = 0; i < newState.length; i++) {
                if (newState[i].key === action.payload.key) {
                    newState.splice(i, 1);
                    break;
                }
            }
            state.status = newState;
        }
    },
});

export const { changeDays, changeGenres, changeGrades, changeStatus, deleteDayTag, deleteGenreTag, deleteGradeTag, deleteStatusTag} = CurSearchTagSlice.actions;
export default CurSearchTagSlice;