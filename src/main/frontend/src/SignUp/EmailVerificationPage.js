// 필요한 모듈과 컴포넌트를 import합니다.
import * as Style from "./style/tsf";
import React, { useState } from 'react';
import SignUpContext from "./SignUpContext";
import axios from 'axios';
import {getEmailAuthCode} from 'api/email/getEmailAuthCode';
import {verifyAuthCodeAndRequestAuthToken} from 'api/auth/authTokenRequestAPI';

import Timer from 'util/Timer';


function RegistrationForm() {
  // 상태변수 정의
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  const [authCodeError , setAuthCodeError] = useState('');
  const [authCodeMessage , setAuthCodeMessage] = useState('');

  const [isIdDuplicated, setIsIdDuplicated] = useState(false);
  const [isEmailAvailable, setIsEmailAvailable] = useState(false);
  const [isAvailable, setIsAvailable] = useState(false);
  const [isButtonAvailable , setIsButtonAvailable] = useState(true);
  const [isProcessing , setIsProcessing] = useState(false);

  const [value , setValue] = useState('');
  const [email , setEmail] = useState('');
  const [authCode , setAuthCode] = useState('');

  const [showTimer , setShowTimer] = useState(false);

  /*
  * 이메일 인증코드 요청 핸들러
  */
  const onEmailCodeRequest = (e) => {

    if(isProcessing) {
      alert("이미 해당 이메일로 인증번호 발급이 완료되었습니다.");
      return;
    }

    const email = value;

    if (!validateEmail(value)) {
      alert('올바른 이메일 주소를 입력해주세요.');

      const emailInput = document.getElementById('email');
      emailInput.value = '';
      emailInput.focus();

      return;
    }

    const response = getEmailAuthCode(email);

    response.then((response) => {

      console.log(response);

      setIsProcessing(true);
      setShowTimer(true);

      alert("이메일 인증 코드 발송 완료~ ");
    })
  }

  /*
  * 이메일 인증코드 확인 핸들러
  */

  const onAuthCodeVerificationHandler = () => {
    const code = authCode;

    console.log(authCode , typeof authCode , authCode.length);

    // 만약 authCode가 6자리가 안되거나 문자열이 아닌 경우 버튼 비활성화 및 이벤트 종료
    if (authCode.length !== 6 || typeof authCode !== 'string') {
        alert('인증번호는 6자리 문자열이어야 합니다.');
        return;
    }

     const data = {
        code : authCode,
        email : value
     }

    const response = verifyAuthCodeAndRequestAuthToken(data);

    response.then((response) => {
      setAuthCodeMessage("인증이 완료되었습니다.");
      setIsAvailable(true);
    })
}


/*
* 인증 시간 초과 시 서버에 저장한 email : authcode 삭제
*/

//const onTimerEnd = () => {
//  if(validateEmail(email)) {
//
//  }
//
//};



  const onAuthCodeChange = (e) => {
      setAuthCode(e.target.value);
    };

  // 이메일 입력 핸들러를 정의합니다.
  const onChangeEmailHandler = (e) => {
    const value = e.target.value;

    // 이메일 입력이 없을 경우, 에러와 메시지를 초기화합니다.
    if(value === '') {
      setError('');
      setMessage('');
    } else {
      // 이메일 중복 및 유효성 검사를 수행합니다.
      checkEmailDuplication(value);
    }

    console.log(value);
    setValue(value);
  }

  // 이메일 유효성 검사 함수를 정의합니다.
  const validateEmail = (email) => {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@(?:naver\.com|gmail\.com)$/;
    return emailRegex.test(email);
  };

  // 이메일 중복 검사 함수를 정의합니다.
  const checkEmailDuplication = async(email) => {
    await axios.post("http://localhost:9000/api/users/email/duplicates", {
      email: email
    })
    .then((response) => {
      console.log(response.data);

      if(response?.data?.isDuplicated == null || response.data.message == null) {
        setError("잘못된 요청입니다.");
        return;
      }
      setError('');
      setMessage(response.data.message);

      if(!response.data.isDuplicated) {
        setIsEmailAvailable(true);
      }

    })
    .catch(function(error) {
      const response = error.response.data;

      if(response.code === 5003) {
        setError(response.message);
        setMessage('');
      }

      setIsEmailAvailable(false);
    });
  };

  // useState 초기화
  const resetState = () => {
    setError('');
    setMessage('');
    setAuthCodeError('');
    setAuthCodeMessage('');
    setIsIdDuplicated(false);
    setIsEmailAvailable(false);
    setIsAvailable(false);
    setIsButtonAvailable(true);
    setIsProcessing(false);
    setValue('');
    setEmail('');
    setAuthCode('');
    setShowTimer(false);
  };


  // 에러와 메시지를 표시하는 컴포넌트를 정의합니다.
  const DisplayMessage = ({ error, message }) => {
    console.log(error , message);

    if (error) {
      return <Style.ErrorMessage>{error}</Style.ErrorMessage>;
    } else if (message) {
      return <Style.Message>{message}</Style.Message>;
    }
    return null;
  };

  // RegistrationForm 컴포넌트의 렌더링 내용을 정의합니다.
  return (
    <Style.MainContainer>
      <Style.Section>
        <Style.SectionHeader>
          <Style.Icon loading="lazy" src="https://cdn.builder.io/api/v1/image/assets/TEMP/cb443ed5e5fad0a5a243fa3140cdf82a96fd899f2e94e9517abfeabceaf9429a?apiKey=a65641faa3d54339891445c030384eb2&" alt="Sign Up" />
          <Style.Title>회원가입</Style.Title>
        </Style.SectionHeader>
        <Style.Description>
          가입 절차를 위해 아래 절차 동의를 눌러주시고 본인인증을 진행해주세요.
        </Style.Description>
      </Style.Section>
      <Style.FormSection>
        <Style.Instruction>
          <Style.InstructionTitle>이메일 인증</Style.InstructionTitle>

          <Style.Input
            onChange={onChangeEmailHandler}
            type="email"
            id='email'
            name='email'
            value={value}
            placeholder='이메일 입력(naver , google)'
            theme='underLine'
            maxLength={30}
          />
        </Style.Instruction>

        <DisplayMessage error={error} message={message} />

        <Style.Button
        type = "submit"
        disabled = {!isEmailAvailable}
        onClick = {onEmailCodeRequest}>번호 전송</Style.Button>

        <Style.VerificationTitle>인증번호 입력</Style.VerificationTitle>

        <Style.Input
          type="text"
          id='authCode'
          name='authCode'
          value={authCode}
          onChange={onAuthCodeChange}
          placeholder='해당 이메일로 발송된 인증번호 6자리를 눌러주세요.'
          theme='underLine'
          maxLength={6}
        />


        <DisplayMessage error={authCodeError} message={authCodeMessage} />

        {showTimer && <Timer initialSeconds={180} onTimerEnd={() => {

          resetState();
          alert("인증 시간이 완료되었습니다. 재 요청 부탁드립니다");



        }} />}

        <Style.Button
        type = "submit"
        disabled={authCode.length !== 6}
        onClick = {onAuthCodeVerificationHandler} >번호 확인</Style.Button>

        </Style.FormSection>


      <Style.VerifyButton disabled={!isAvailable}>회원가입 이동</Style.VerifyButton>
      <Style.ProgressIndicator />
    </Style.MainContainer>
  );
}

export default RegistrationForm;
