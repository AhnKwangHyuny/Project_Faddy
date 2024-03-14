import { END_POINTS } from 'constants/api';

import {userRequestInstance} from 'api/axiosInstance';

export const postUserLogin = async({username, password}) => {

    const formData = new FormData();
    formData.append('username' , username);
    formData.append('password' , password);

    return await userRequestInstance.post(END_POINTS.LOGIN , formData);
}