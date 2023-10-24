import { myAxios } from "./helper";
import { BASE_URL } from "./helper";

export const SignUp = (user) => {
    return myAxios
        .post(BASE_URL+'/api/register', user)
        .then((response) => response.data)
};