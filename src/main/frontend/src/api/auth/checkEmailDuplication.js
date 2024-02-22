import { axiosInstance } from 'api/axiosInstance';

import { END_POINTS } from 'constants/api';

export const checkEmailDuplication = async(email) => {
  const response = await axiosInstance.post('/api/users/checkEmailDuplication' , {email});
  return response.data;

};