import * as Style from "./style/tsf";
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import SignUpContext from "./SignUpContext";
import axios from 'axios';

function RegistrationForm() {
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const [isIdDuplicated, setIsIdDuplicated] = useState(false);
  const [isEmailAvailable, setIsEmailAvailable] = useState(false);
  const [value , setValue] = useState('');

  const onChangeIdHandler = (e) => {
    const value = e.target.value;
    console.log(value);
    setValue(value);

    // 이메일 중복 및 유효성 검사 체크
    checkEmailDuplication(value);
  }

  const validateEmail = (email) => {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@(?:naver\.com|gmail\.com)$/;
    return emailRegex.test(email);
  };

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
      setIsEmailAvailable(!response.data.isDuplicated);
      setMessage(response.data.message);
    })
    .catch(function(error) {
      console.log(error);
      if(error.data.message == null || error.data.code == null) {

        setError("잘못된 요청입니다. ")
      }
      setError(error.data.message);
      setIsEmailAvailable(false);
    });
  };

  const DisplayMessage = ({ error, message }) => {
    if (error) {
      return <Style.ErrorMessage>{error}</Style.ErrorMessage>;
    } else if (message) {
      return <Style.Message>{message}</Style.Message>;
    }
    return null;
  };

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
            onChange={onChangeIdHandler}
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
        <Style.Button>번호확인</Style.Button>
        <Style.VerificationTitle>인증번호 입력</Style.VerificationTitle>
        <Style.Input placeholder="이메일에 전송된 인증번호 입력해주세요. (숫자 6자)" />
      </Style.FormSection>
      <Style.VerifyButton>본인인증</Style.VerifyButton>
      <Style.ProgressIndicator />
    </Style.MainContainer>
  );
}

export default RegistrationForm;
