import axios from 'axios';

import {handleAPIError} from 'api/interceptors';

import { AXIOS_BASE_URL, NETWORK } from 'constants/api';

export const axiosInstance = axios.create({
  baseURL: AXIOS_BASE_URL,
  timeout: NETWORK.TIMEOUT,
  withCredentials: true,
  useAuth: true,
});

axiosInstance.interceptors.request.use((config) => {
  // 토큰을 얻는 방법은 애플리케이션에 따라 다릅니다.
  // 일반적으로 토큰은 로그인 후에 얻어지며, 로컬 스토리지나 쿠키, 또는 상태 관리 라이브러리에 저장됩니다.
  const token = getTokenFromSomewhere();

  if (token) {
    // 토큰이 있으면 요청 헤더에 추가합니다.
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
}, handleAPIError);

axiosInstance.interceptors.response.use((response) => response, handleAPIError);