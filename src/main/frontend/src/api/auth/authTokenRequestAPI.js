import {axiosInstance} from '../axiosInstance.js';
import { END_POINTS } from '../../constants/api';

export const verifyAuthCodeAndRequestAuthToken = async(data) => {

    if (!data || data.code == null || data.email == null) {
        alert("인증번호 및 이메일을 정확히 입력바랍니다.");
        return;
    }

    const response = await axiosInstance.post(END_POINTS.VERIFY_AUTH_CODE , data);

    return response;
}