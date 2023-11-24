import { BASE_URL_POST, myPostAxios } from "./helper";

export const loadAllCategories = () => {
    return myPostAxios
        .get(BASE_URL_POST+'/api/category/')
        .then(response => {return response.data})
};