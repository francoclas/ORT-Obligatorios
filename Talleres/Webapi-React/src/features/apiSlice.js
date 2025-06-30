import { createSlice } from "@reduxjs/toolkit";

//Slice para facilitar el uso de las api segun se necesite en los demas componentes.
export const apiUrl = createSlice(
   {
    name:"api",
    initialState:{baseURL:"https://movetrack.develotion.com/",imgURL :"https://movetrack.develotion.com/imgs/"},
    reducers: {
        actualizarBaseUrl: (state,action) => {
            state.baseURL = action.payload;
        },
        actualizarImgURL: (state,action) => {
            state.imgURL = action.payload;
        }
    }
   } 
)