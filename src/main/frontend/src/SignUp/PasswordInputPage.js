import * as React from 'react';
import * as StyledComponents from ".././Common/SignUpInputForm";
import styled from 'styled-components';
import { Link } from 'react-router-dom';


function PasswordInputForm({onSubmit}) {
  return (
    <form onSubmit={onSubmit}>
        <StyledComponents.Instruction>비밀번호를 입력해주세요.</StyledComponents.Instruction>
        <StyledComponents.InputField placeholder="숫자,영문자,특수문자를 1개 이상 사용한 8자리 이상의 비밀번호"/>
        <StyledComponents.StepInfo>2 of 3</StyledComponents.StepInfo>
        <StyledComponents.ProgressSection>
            <StyledComponents.ProgressBar />
        </StyledComponents.ProgressSection>
        <StyledComponents.NextButton type="submit" >
            < Link to="/signUp/nickname"> 다음 </Link>
        </StyledComponents.NextButton>
        <StyledComponents.FooterIndicator />
    </form>
  );
}

export default PasswordInputForm;