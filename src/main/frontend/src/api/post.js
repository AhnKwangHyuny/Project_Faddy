import { END_POINTS ,   AXIOS_BASE_URL } from 'constants/api';

import {userRequestInstance} from 'api/axiosInstance';

export const postUserLogin = async({username, password}) => {

    console.log(AXIOS_BASE_URL);

    const data = {
            username: username,
            password: password
        };


    return await userRequestInstance.post(END_POINTS.LOGIN , data);
}

