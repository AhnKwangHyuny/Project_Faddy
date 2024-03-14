//import type { AxiosError, InternalAxiosRequestConfig } from 'axios';
//
//import { HTTPError } from 'api/HTTPError';
//import { axiosInstance } from 'api/axiosInstance';
//
//import { ACCESS_TOKEN_KEY , ERROR_CODE, HTTP_STATUS_CODE } from 'constants/api';
//
//
//export const handleAPIError = (error) => {
//
//  if (!error.response) throw error;
//
//  const { data, status } = error.response;
//
//  console.log(data ,status);
//
//  return Promise.reject(error);
//
//};
//
//export const checkAndSetToken = (config) => {
//  // 토큰 인증이 필요없는 request인 경우
//  if (!config.useAuth || !config.headers || config.headers.Authorization) return config;
//
//  const accessToken = localStorage.getItem('ACCESS_TOKEN_KEY');
//
//  if (!accessToken) {
//    window.location.href = PATH.ROOT;
//    throw new Error('토큰이 유효하지 않습니다');
//  }
//
//  // eslint-disable-next-line no-param-reassign
//  config.headers.Authorization = `Bearer ${accessToken}`;
//
//  return config;
//}
