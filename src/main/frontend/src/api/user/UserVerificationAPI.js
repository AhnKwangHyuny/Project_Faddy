import {axiosInstance} from '../axiosInstance.js';
import { END_POINTS } from '../../constants/api';
import {validateUserId} from 'util/validateUserId';

export const checkUserId = async (userId) => {

    if(userId === null || userId === undefined || validateUserId(userId)) {
        console.warn("유저 아이디가 유효하지 않습니다.");
        return;
    }

    const data = {
        'userId': userId
    };

    const response = await axiosInstance.post(END_POINTS.CHECK_USER_ID , data);

    return response;
}
