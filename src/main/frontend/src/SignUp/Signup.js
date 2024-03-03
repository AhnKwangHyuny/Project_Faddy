import { SignupProvider } from './SignUpContext';
import IdInputForm from './CreateUserIdPage';
import PasswordInputForm from './CreatePasswordPage';
import NicknameInputForm from './CreateNicknamePage';
import EmailVerificationForm from './EmailVerificationPage';
import { BrowserRouter as Router, Route, useNavigate, Routes , useLocation } from 'react-router-dom';
import { useEffect } from 'react';

const SignUp = function () {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (location.pathname === '/signup') {
        navigate('email/verifications');
      }
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
