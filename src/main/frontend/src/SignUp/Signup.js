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


  const handleIdSubmit = (e) => {
    e.preventDefault();
    // 아이디 유효성 검사를 수행합니다.
  };

  const handlePasswordSubmit = (e) => {
    e.preventDefault();
    // 패스워드 유효성 검사를 수행합니다.
  };

  const handleNicknameSubmit = (e) => {
    e.preventDefault();
    // 닉네임 유효성 검사를 수행합니다.
    // 모든 정보를 서버로 전송합니다.
    console.log(id, password, nickname);
  };

  return (
    <Routes>
      <Route path="id" element={<IdInputForm onChange = {onChangeIdHandler} />} />
      <Route path="password" element={<PasswordInputForm onSubmit={handlePasswordSubmit} />} />
      <Route path="nickname" element={<NicknameInputForm onSubmit={handleNicknameSubmit} />} />
    </Routes>
  );
}

export default Signup;
