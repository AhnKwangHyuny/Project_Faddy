
export const ValidateUserId = (id) => {

    // 아이디는 5~15자의 영소문자, 숫자만 입력 가능합니다.
    const regex = /^[a-z0-9]{5,15}$/;
    return regex.test(id);
  };

export const validateNickname = (name) => {
    // 닉네임은 특수문자를 제외한 3글자 이상 가능합니다.
    const regex = /^[\p{L}\p{N}]{3,12}$/gu;
    return regex.test(name);
}