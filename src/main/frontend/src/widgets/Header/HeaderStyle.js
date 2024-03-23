import styled from '@emotion/styled';

export const NavBarContainer = styled.div`
  width: 100%;
  background-color: #fff;
  box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.25);
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
  padding: 31px 16px 10px 151px;
  box-sizing: border-box;
  letter-spacing: normal;
  gap: 20px;
  text-align: center;
  font-size: 23px;
  color: #000;
  font-family: Aclonica;
`;

export const Logo = styled.div`
  width: 88px;
  position: relative;
  line-height: 28px;
  display: inline-block;
  flex-shrink: 0;
`;

export const Icon = styled.img`
  height: 23px;
  width: 21px;
  position: relative;
`;

export const UserIcon = styled.img`
  height: 30px;
  width: 30px;
  position: relative;
`;
