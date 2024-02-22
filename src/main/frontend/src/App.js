import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import OnTheBoard from "./OnTheBoard/OnTheBoard";
import Login from "./Login/LoginForm"; // 로그인 페이지 컴포넌트를 import합니다.
import Signup from "./SignUp/SignUp";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<OnTheBoard />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup/*" element={<Signup />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
