// Layout.js
import React from 'react';
import Header from 'widgets/Header/Header';
import MainMenuBar from 'widgets/MainMenuBar/MainMenuBar';

const Layout = ({ children }) => (
  <>
    <Header />
    {children}
    <MainMenuBar />
  </>
);

export default Layout;
