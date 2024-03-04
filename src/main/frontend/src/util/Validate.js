
export const ValidateUserId = (id) => {

    // 아이디는 5~15자의 영소문자, 숫자만 입력 가능합니다.
    const regex = /^[a-z0-9]{5,15}$/;
    return regex.test(id);
  };
