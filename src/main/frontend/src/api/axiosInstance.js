import axios from 'axios';

import { AXIOS_BASE_URL, NETWORK } from 'constants/api';

export const axiosInstance = axios.create({
  baseURL: AXIOS_BASE_URL,
  withCredentials: true,
  useAuth: true,
});

export const userRequestInstance = axios.create({
  baseURL: AXIOS_BASE_URL,
  timeout: NETWORK.TIMEOUT
});

// 유저 헤더에 쿠키 추가
export const setAccessToken = (token) => {
  userRequestInstance.defaults.headers.common.Authorization = `Bearer ${token}`;
};

