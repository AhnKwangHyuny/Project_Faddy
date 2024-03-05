import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

function LoginForm() {
  return (
    <Container>
      <Content>
        <Logo>Faddy</Logo>
        <Title>로그인</Title>
        <Row>
          <Image src="https://cdn.builder.io/api/v1/image/assets/TEMP/03fcec9516568a3453c5faa1b3c809f19f272f9e808d97a582f111c385346473?apiKey=a65641faa3d54339891445c030384eb2&" />
          <Label>Email</Label>
            <Input type="email" placeHolder="naveen@gmail.com" />
        </Row>
        <Separator />
        <Row>
          <Image src="https://cdn.builder.io/api/v1/image/assets/TEMP/2d0794d2546ba119bb5a020c3fb1dd626fce12688ef8e425b8786179a83bc69c?apiKey=a65641faa3d54339891445c030384eb2&" />
          <Label>Password</Label>
            <Input type="password" placeHolder="naV#123456" />
        </Row>
        <Separator />
        <ForgotLink>아이디 / 비밀번호 찾기</ForgotLink>
        <ProviderContainer>
          <ProviderLogo src="https://cdn.builder.io/api/v1/image/assets/TEMP/10fd1fa74f83c31c1287c86f02a1f88e476f6d5e3e201af9f94da0625e06de3b?apiKey=a65641faa3d54339891445c030384eb2&" />
          <ProviderText>login with kakao</ProviderText>
        </ProviderContainer>
        <ProviderContainer>
          <ProviderLogo src="https://cdn.builder.io/api/v1/image/assets/TEMP/62805269a90818204f7d595ee5a73f3f6f16ff5d5b6b2e26f47e2253d112812d?apiKey=a65641faa3d54339891445c030384eb2&" />
          <ProviderText>login with google</ProviderText>
        </ProviderContainer>
        <Signup>
          아직 회원이 아니신가요?{" "}
          <SignupLink><Link to="/signup">회원가입</Link></SignupLink>{" "}
        </Signup>
      </Content>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  max-width: 480px;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  font-size: 20px;
  color: #fff;
  font-weight: 600;
  white-space: nowrap;
  margin: 0 auto;
`;

const Content = styled.div`
  background-color: #fff;
  display: flex;
  width: 100%;
  flex-direction: column;
  padding: 50px 63px;
`;

const Logo = styled.div`
  background-color: #171717;
  border-radius: 50%;
  align-self: center;
  margin-top: 14px;
  justify-content: center;
  align-items: center;
  aspect-ratio: 1;
  padding: 37px 43px 28px;
  font: 38px Poppins, sans-serif;
`;

const Title = styled.div`
  color: #171717;
  align-self: center;
  margin-top: 28px;
  font: 50px Poppins, sans-serif;
`;

const Row = styled.div`
  display: flex;
  margin-top: 61px;
  justify-content: space-between;
  gap: 20px;
  font-size: 14px;
  color: #595959;
  text-align: center;
`;

const Image = styled.img`
  aspect-ratio: 1;
  object-fit: auto;
  object-position: center;
  width: 28px;
`;

const Email = styled.div`
  font-family: Inter, sans-serif;
  flex-grow: 1;
  flex-basis: auto;
  margin: auto 0;
`;

const Separator = styled.div`
  background-color: #979797;
  margin-top: 5px;
  height: 1px;
`;

const Password = styled.div`
  font-family: Inter, sans-serif;
  margin: auto 0;
`;

const ForgotLink = styled.div`
  color: #bdbaba;
  align-self: end;
  margin-top: 4px;
  font: 15px Poppins, sans-serif;
`;

const ProviderContainer = styled.div`
  border-radius: 8px;
  background-color: #171717;
  align-self: center;
  display: flex;
  margin-top: 81px;
  width: 300px;
  max-width: 100%;
  gap: 15px;
  padding: 19px 36px;
`;

const ProviderLogo = styled.img`
  aspect-ratio: 1;
  object-fit: auto;
  object-position: center;
  width: 32px;
`;

const ProviderText = styled.div`
  font-family: Poppins, sans-serif;
  align-self: start;
  margin-top: 9px;
  flex-grow: 1;
`;

const Signup = styled.div`
  color: #2679c5;
  font-family: Poppins, sans-serif;
  align-self: center;
  margin: 66px 0 15px;
`;

const SignupLink = styled.span`
  color: rgba(38, 121, 197, 1);
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
`;

const Label = styled.label`
  padding: 12px 12px 12px 0;
  display: inline-block;
`;


export default LoginForm;