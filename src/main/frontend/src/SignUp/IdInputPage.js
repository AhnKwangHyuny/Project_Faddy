import * as React from 'react';
import * as StyledComponents from ".././Common/SignUpInputForm";
import styled from 'styled-components';
import { Link } from 'react-router-dom';



function IdInputForm({onChangeIdHandler}) {
  return (
    <StyledComponents.Instruction>아이디를 입력해주세요.</StyledComponents.Instruction>
    <StyledComponents.InputField
        onChange={onChangeIdHandler}
        type="text"
        id='id'
        name='id'
        value={id}
        placeholder='아이디 입력'
        theme='underLine'
        maxLength={15}
        />
    <StyledComponents.StepInfo>1 of 3</StyledComponents.StepInfo>
    <StyledComponents.ProgressSection>
        <StyledComponents.ProgressBar />
    </StyledComponents.ProgressSection>
    <StyledComponents.NextButton type="submit" >
        < Link to="/signUp/password"> 다음 </Link>
    </StyledComponents.NextButton>
    <StyledComponents.FooterIndicator />
  );
}

export default IdInputForm;