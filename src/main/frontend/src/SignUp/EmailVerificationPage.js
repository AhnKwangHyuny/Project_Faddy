import styled from "styled-components";

import React, { useState , useEffect , useContext , useRef } from 'react';
import { Link } from 'react-router-dom';
import SignUpContext from "./SignUpContext";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function RegistrationForm() {

  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const [isIdDuplicated, setIsIdDuplicated] = useState(false);
  const [isNicknameAvailable, setIsNicknameAvailable] = useState(false);

  /*
  * email 유효성 검사
  */
  const validateEmail = (email) => {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@(?:naver\.com|gmail\.com)$/;

    return emailRegex.test(email);
  };

  /*
  * 이메일 중복확인 API
  */
  const isEmailDuplicated = (email) => {


  };

  return (
    <MainContainer>
      <Section>
        <SectionHeader>
          <Icon loading="lazy" src="https://cdn.builder.io/api/v1/image/assets/TEMP/cb443ed5e5fad0a5a243fa3140cdf82a96fd899f2e94e9517abfeabceaf9429a?apiKey=a65641faa3d54339891445c030384eb2&" alt="Sign Up" />
          <Title>회원가입</Title>
        </SectionHeader>
        <Description>
          가입 절차를 위해 아래 절차 동의를 눌러주시고 본인인증을 진행해주세요.
        </Description>
      </Section>
      <FormSection>
        <Instruction>
          <InstructionTitle>이메일 인증</InstructionTitle>
          <Input type="email" placeholder="네이버 또는 구글 이메일 번호를 입력해주세요." />
        </Instruction>
        <Button>번호확인</Button>
        <VerificationTitle>인증번호 입력</VerificationTitle>
        <Input placeholder="이메일에 전송된 인증번호 입력해주세요. (숫자 6자)" />
      </FormSection>
      <VerifyButton>본인인증</VerifyButton>
      <ProgressIndicator />
    </MainContainer>
  );
}

export default RegistrationForm;

const MainContainer = styled.main`
  background-color: #fff;
  display: flex;
  max-width: 480px;
  width: 100%;
  padding-bottom: 29px;
  flex-direction: column;
  margin: 0 auto;
`;

const HeaderImage = styled.img`
  width: 100%;
  object-fit: cover;
`;

const Section = styled.section`
  display: flex;
  margin-top: 44px;
  width: 100%;
  flex-direction: column;
  padding: 0 32px;
`;

const SectionHeader = styled.div`
  display: flex;
  justify-content: space-between;
  gap: 20px;
  font-size: 15px;
  color: #14304a;
  font-weight: 600;
  white-space: nowrap;
  text-align: center;
  letter-spacing: 0.07px;
`;

const Icon = styled.img`
  width: 13px;
`;

const Title = styled.h1`
  flex-grow: 1;
  font-family: Inter, sans-serif;
`;

const Description = styled.p`
  color: rgba(80, 90, 99, 0.8);
  margin-top: 64px;
  font: 500 18px Inter, sans-serif;
`;

const FormSection = styled.section`
  background-color: rgba(255, 255, 255, 1);
  display: flex;
  margin-top: 55px;
  width: 100%;
  flex-direction: column;
  font-weight: 600;
  padding: 24px 23px 50px;
`;

const Instruction = styled.div`
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 20px;
`;

const InstructionTitle = styled.h2`
  font-size: 18px;
  color: #14304a;
  text-align: center;
`;

const Input = styled.input`
  color: #c4c4c4;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid transparent;

  ::placeholder {
    color: #C4C4C4;
  }
`;

const Button = styled.button`
  border-radius: 8px;
  background-color: rgba(217, 217, 217, 0.7);
  color: #000;
  text-align: center;
  padding: 6px 19px;
  font-size: 14px;
  font-family: Inter, sans-serif;
  margin-top: 34px;
  cursor: pointer;
`;


const VerificationTitle = styled.h3`
  color: #14304a;
  text-align: center;
  margin-top: 25px;
  font-size: 18px;
`;


const VerifyButton = styled.button`
  align-items: center;
  border-radius: 32px;
  background-color: #000;
  color: #fff;
  text-align: center;
  padding: 19px 60px;
  font-weight: 700;
  font-size: 15px;
  font-family: Inter, sans-serif;
  margin-top: 87px;
  max-width: 327px;
  width: 100%;
  cursor: pointer;
`;

const ProgressIndicator = styled.div`
  border-radius: 2.5px;
  border: 1px solid #d9d9d9;
  background-color: rgba(217, 217, 217, 0.85);
  width: 134px;
  height: 5px;
  margin-top: 163px;
  align-self: center;
`;