/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import * as React from 'react';
import { Link } from 'react-router-dom';

import { boxStyle, divStyle, imgStyle , div2Style , div3Style,div4Style , div5Style , div6Style, div7Style} from './OnTheBoardStyles';


// 이미지 컴포넌트
const Image = ({ srcSet }) => (
  <img
    loading="lazy"
    srcSet={srcSet}
    css={imgStyle}
  />
);

// 버튼 컴포넌트
const StyledButton = ({ children, style }) => (
  <div css={style}>{children}</div>
);

// 메인 컴포넌트
function OnTheBoard(props) {
  return (
    <>
    <div css={boxStyle}>
      <div css={divStyle}>
        <Image srcSet="..." />
        <div css={div2Style}>Faddy</div>
        <div css={div3Style}>
          <div css={div4Style}>
            자신의 패션을 자유롭게
            <br />
            공유 하세요.
          </div>

          <StyledButton style={div5Style}><Link to = "/styleShare">메인으로</Link></StyledButton>
          <StyledButton style={div6Style}><Link to = "/login">로그인</Link></StyledButton>



          <div css={div7Style}>
            By continuing, you agree to the{" "}
            <span style={{fontWeight: 600}}>Terms of Service</span> and
            confirm
            <br />
            that you have read our{" "}
            <span style={{fontWeight: 600}}>Privacy Policy</span>.
          </div>
        </div>
      </div>
    </div>
  </>
  );
}

export default OnTheBoard;
