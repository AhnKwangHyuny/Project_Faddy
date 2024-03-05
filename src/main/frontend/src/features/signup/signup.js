import { axiosInstance } from 'api/axiosInstance';

export async function signup(id, password, nickname, email) {

  try {
    const response = await axiosInstance.post('/api/v1/users/signup', {
      id,
      password,
      nickname,
      email,
    });

    // 회원가입 성공 처리
    // 예: 로그인 페이지로 리디렉션
    return response;
  } catch (error) {

    // Error 로직

    throw error;
  }
}
