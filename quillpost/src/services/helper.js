import axios from "axios";

//for Auth part
export const BASE_URL_AUTH = 'http://localhost:8000';

export const myAuthAxios = axios.create({
    baseUrl: BASE_URL_AUTH,
});


//for Post part
export const BASE_URL_POST = 'http://localhost:8080';

export const myPostAxios = axios.create({
    baseURL: BASE_URL_POST,
});