import axios from "axios";

export const BASE_URL = 'http://localhost:8000';


export const myAxios = axios.create({
    baseUrl: BASE_URL,
});