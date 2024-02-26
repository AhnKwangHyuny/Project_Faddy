import { SignupProvider } from './SignUpContext';
import IdInputForm from './CreateIdPage';
import PasswordInputForm from './CreatePasswordPage';
import NicknameInputForm from './CreateNicknamePage';
import EmailVerificationForm from './EmailVerificationPage';
import { BrowserRouter as Router, Route, useNavigate, Routes } from 'react-router-dom';
import { useEffect } from 'react';

const SignUp = function () {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('email/verifications');
  }, [navigate]);

  return (
    <SignupProvider>
      <Routes>
        <Route path="id" element={<IdInputForm />}/>
        <Route path="password" element={<PasswordInputForm />} />
        <Route path="nickname" element={<NicknameInputForm />} />
        <Route path="email/verifications" element={<EmailVerificationForm />} />
      </Routes>
    </SignupProvider>
  );
}

export default SignUp;
