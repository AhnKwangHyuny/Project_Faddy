import React, { createContext, useState } from 'react';

// Create a Context
const SignupContext = createContext();

// Create a Provider which will hold our global state
export const SignupProvider = ({ children }) => {
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [nickname, setNickname] = useState('');
  const [email , setEmail] = useState('');

  return (
    <SignupContext.Provider value={{id, setId, password, setPassword, nickname, setNickname , email , setEmail}}>
      {children}
    </SignupContext.Provider>

  );
};

export default SignupContext;
