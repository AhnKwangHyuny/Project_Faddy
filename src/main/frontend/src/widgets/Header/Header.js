import React from 'react';
import { NavBarContainer, Logo, Icon, UserIcon } from './HeaderStyle';

const Header = () => {
  return (
    <NavBarContainer>
      <Logo>Faddy</Logo>
      <Icon loading="lazy" alt="" src="/group-3192.svg" />
      <UserIcon loading="lazy" alt="" src="/user-circle-light.svg" />
    </NavBarContainer>
  );
};

export default Header;
