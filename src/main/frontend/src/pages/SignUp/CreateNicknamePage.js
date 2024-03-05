import React, { useState , useEffect , useContext , useRef } from 'react';
import * as Style from "Common/SignUpStyle";
import { Link } from 'react-router-dom';
import SignupContext from "./SignUpContext";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

import {validateNickname} from 'utils/Validate';

import {getNicknameValidationResponse} from 'api/user/UserVerificationAPI';

function NicknameInputForm() {

    const {id , password , email} = useContext(SignupContext);
    const navigate = useNavigate();

    // 닉네임 컴포넌트 마운트 시 id , password ,email이 정의되어 있지 않다면 회원가입 초기 화면으로 리다이렉트
    useEffect(() => {
        if (!id || !password || !email) {

            alert("잘못된 접근입니다.");

            navigate('/signup/email/verifications');
        }
    }, [id, password, email, navigate]);

    const {nickname , setNickname} = useContext(SignupContext);
    const [value , setValue] = useState('');

    const [error, setError] = useState('');
    const [message, setMessage] = useState('');

    const [isAvailable , setIsAvailable] = useState(false);

    const onChangeNicknameHandler = (e) => {
        const name = e.target.value;
        setValue(name);

        if(name.trim() === '') {
            setError('');
            setMessage('');

            return;
        }

        if(!validateNickname(name)) {
            setError("3~12자리 사이 닉네임을 입력해주세요.");

            return false;
        }

        // nickname 중복 검사
        const result = getNicknameValidationResponse(value);

        result.then((response) => {
            console.log(response);

            if(response?.data?.responseCode !== "200") {
                setError("유효하지 않은 닉네임입니다.");
                setIsAvailable(false);

            }

            setError('');
            setNickname(value);
            setMessage(response.data.responseMessage);

            setIsAvailable(true);

        })
        .catch((err) => {
            console.log(err);

            setIsAvailable(false);
        });


    }



    const DisplayMessage = ({ error , message }) => {
        if (error) {
          return <Style.ErrorMessage>{error}</Style.ErrorMessage>;
        } else if (message) {
          return <Style.Message>{message}</Style.Message>;
        }
          return null;
        };

    const onFormSubmit = (event) => {
        event.preventDefault();

        //useContext(SignupContext) 에 id , password , email이 모두 들어있는지 확인

        // 없으면 누락된 회원정보가 있습니다. 말한 후 쿠키 삭제 후 이메일 인증 페이로 리다이렉트
        // 전부 존재한다면 서버에 유저 회원가입 요청

    };


    return (
        <form onSubmit={onFormSubmit}>
            <Style.Instruction>닉네임을 입력해주세요.</Style.Instruction>

            <Style.InputField
                onChange={onChangeNicknameHandler}
                type="text"
                id='nickname'
                name='nickname'
                value={value}
                placeholder="닉네임 입력"
                theme='underLine'
                maxLength={15}
            />

            <DisplayMessage error={error} message={message} />

            <Style.StepInfo>3 of 3</Style.StepInfo>
            <Style.ProgressSection>
                <Style.ProgressBar />
            </Style.ProgressSection>

            <Style.NextButton type = "submit" disabled={!isAvailable} >다음</Style.NextButton>

            <Style.FooterIndicator />
        </form>
    );
}

export default NicknameInputForm;
