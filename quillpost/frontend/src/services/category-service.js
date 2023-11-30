import { BASE_URL_POST, myPostAxios } from "./helper";

export const loadAllCategories = () => {
    return myPostAxios
        .get('/category/')
        .then(response => {return response.data})
};