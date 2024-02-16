import * as React from 'react';
import * as StyledComponents from ".././Common/SignUpInputForm";
import styled from 'styled-components';


function NicknameInputForm({onSubmit}) {
  return (
    <form onSubmit={onSubmit}>
        <StyledComponents.Instruction>닉네임을 입력해주세요.</StyledComponents.Instruction>
        <StyledComponents.InputField placeholder="닉네임"/>
        <StyledComponents.StepInfo>3 of 3</StyledComponents.StepInfo>
        <StyledComponents.ProgressSection>
            <StyledComponents.ProgressBar />
        </StyledComponents.ProgressSection>
        <StyledComponents.NextButton>다음</StyledComponents.NextButton>
        <StyledComponents.FooterIndicator />
    </form>
  );
}

export default NicknameInputForm;