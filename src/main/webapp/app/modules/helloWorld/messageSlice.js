import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

// Thunk para fazer a requisição ao backend
export const sendMessage = createAsyncThunk(
    'message/sendMessage',
    async (userMessage, { rejectWithValue }) => {
        try {
            const response = await axios.post('http://localhost:8080/api/hello-world', { userMessage });
            return response.data; // Retorna o DTO do backend
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Erro ao enviar a mensagem');
        }
    }
);

const messageSlice = createSlice({
    name: 'message',
    initialState: {
        userMessage: '',
        responseMessage: null,
        status: 'idle',
        error: null,
    },
    reducers: {
        setUserMessage: (state, action) => {
            state.userMessage = action.payload;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(sendMessage.pending, (state) => {
                state.status = 'loading';
                state.error = null;
            })
            .addCase(sendMessage.fulfilled, (state, action) => {
                state.status = 'succeeded';
                state.responseMessage = action.payload;
            })
            .addCase(sendMessage.rejected, (state, action) => {
                state.status = 'failed';
                state.error = action.payload;
            });
    },
});

export const { setUserMessage } = messageSlice.actions;
export default messageSlice.reducer;
