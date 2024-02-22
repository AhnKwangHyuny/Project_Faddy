import React, { useState , useEffect , useContext , useRef } from 'react';
import * as Style from ".././Common/SignUpStyle";
import { Link } from 'react-router-dom';
import SignUpContext from "./SignUpContext";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function PasswordInputForm() {


    const {password , setPassword } = useContext(SignUpContext);
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');
    const [isPasswordAvailable, setIsPasswordAvailable] = useState(false); // 아이디 사용 가능한지 아닌지

    const passwordRef = useRef();
    const navigate = useNavigate();



    const onChangePasswordHandler = (event) => {
        let password = event.target.value;

        if(password == "") {
            return;
        }

        // password 유효성 검사
        let isValid = isValidPassword(password);
        if(isValid){
            setMessage("사용가능한 비밀번호 입니다.");
        } else {
            setError("비밀번호는 숫자,영문자, 특수문자롤 1개씩 포함한 8자리 이상이어야 합니다.");
        }

        setIsPasswordAvailable(isValid);

    }

    const isValidPassword = (password) => {
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

        return passwordRegex.test(password);
    }

    const onFormSubmit = (event) => {
            event.preventDefault();
            const password = passwordRef.current.value;
            setPassword(password);

            console.log(password);

            navigate('/signUp/nickname');

          };


    return (
        <form onSubmit={onFormSubmit}>
            <Style.Instruction>비밀번호를 입력해주세요.</Style.Instruction>

            <Style.InputField
                ref = {passwordRef}
                onChange={onChangePasswordHandler}
                type="password"
                id='password'
                name='password'
                value={password}
                theme='underLine'
                placeholder="숫자,영문자,특수문자를 1개 이상 사용한 8자리 이상의 비밀번호"
            />

            <Style.StepInfo>2 of 3</Style.StepInfo>

            <Style.ProgressSection>
                <Style.ProgressBar/>
            </Style.ProgressSection>

            <Style.NextButton disabled={isPasswordAvailable} type = "submit"> 다음 </Style.NextButton>


            <Style.FooterIndicator />
        </form>
    );
}

export default PasswordInputForm;