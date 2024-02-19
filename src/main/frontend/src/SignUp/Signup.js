import React, { useState , useEffect } from 'react';
import { Navigate } from 'react-router-dom';

import IdInputForm from './IdInputPage';
import PasswordInputForm from './PasswordInputPage';
import NicknameInputForm from './NicknameInputPage';

import { BrowserRouter as Router, Route, useNavigate, Routes } from 'react-router-dom';

function Signup() {
  const navigate = useNavigate();

    useEffect(() => {
      navigate('id');
    }, []);

  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [nickname , setNickname] = useState('');
  const [confirm, setConfirm] = useState('');

  const [idError, setIdError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nicknameError , setNicknameError] = useState('');
  const [confirmError, setConfirmError] = useState('');

  const [isIdDuplicated, setIsIdDuplicated] = useState(false); // 아이디 중복 검사를 했는지 안했는지
  const [isIdAvailable, setIsIdAvailable] = useState(false); // 아이디 사용 가능한지 아닌지

  const [isNicknameDuplicated, setIsNicknameDuplicated] = useState(false); // 닉네임 중복 검사를 했는지 안했는지
  const [isNicknameAvailable, setIsNicknameAvailable] = useState(false); // 닉네임 사용 가능한지 아닌지

/*
: 사용자가 아이디와 비밀번호 및 닉네임을 입력할 때 마다 해당 값의 변화를 감지하고 업데이트 함
*/

// 아이디
const onChangeIdHandler = (e) => {
  const idValue = e.target.value;
  setId(idValue);

  // id 유효성 검사
  idCheckHandler(idValue);
}

// 닉네임
const onChangeIdHandler = (e) => {
  const nicknameValue = e.target.value;
  setId(nicknameValue);

  // nickname 유효성 검사
  nicknameCheckHandler(nicknameValue);
}

const onChangePasswordHandler = (e) => {
  const { name, value } = e.target;
  if (name === 'password') {
    setPassword(value);
    passwordCheckHandler(value, confirm);
  } else {
    setConfirm(value);
    passwordCheckHandler(password, value);
  }
}

/*
 아이디 유효성 검사
 1. 5~ 15자리의 영소문자 , 숫자 배합
 2. 적어도 1개 이상의 영어 대소문자와 숫자가 포함된 조합이어야함
 3. 특수문자 안됨
*/
const idCheckHandler = async (id) => {
    const idRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{5,15}$/;

    if (id === '') {
      setIdError('아이디를 입력해주세요.');
      setIsIdAvailable(false);
      return false;

    } else if (!idRegex.test(id)) {
      setIdError('아이디는 5~15자의 영소문자, 숫자만 입력 가능합니다.');
      setIsIdAvailable(false);
      return false;
    }

    try {
      idDuplicationCheckAPI(id);

//      if (responseData) {
//        setIdError('사용 가능한 아이디입니다.');
//        setIsIdCheck(true);
//        setIsIdAvailable(true);
//        return true;
//      } else {
//        setIdError('이미 사용중인 아이디입니다.');
//        setIsIdAvailable(false);
//        return false;
//      }
    } catch (error) {
      alert('서버 오류입니다. 관리자에게 문의하세요.');
      console.error(error);
      return false;
    }
}

const idDuplicationCheckAPI = async(userId) => {
    let return_value;

    await axios.post("http://localhost:9000/users/check-duplication" , {
        userId : userId,
    })
    .then((response) => {
        console.log(response);
    })
    .catch(function(error) {
        console.log(error);

    })

}


  return (
    <Routes>
      <Route path="id" element={<IdInputForm onChange = {onChangeIdHandler} />} />
      <Route path="password" element={<PasswordInputForm onSubmit={handlePasswordSubmit} />} />
      <Route path="nickname" element={<NicknameInputForm onSubmit={handleNicknameSubmit} />} />
    </Routes>
  );
}

export default Signup;
