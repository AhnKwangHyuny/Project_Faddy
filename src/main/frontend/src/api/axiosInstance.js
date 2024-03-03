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

  const token = localStorage.getItem("authToken");

  if(token) {
    config.headers.Authorization = token;
  }

  return config;
}, handleAPIError);

axiosInstance.interceptors.response.use((response) => response, handleAPIError);

