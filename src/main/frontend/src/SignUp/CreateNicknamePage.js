import React, { useState , useEffect , useContext , useRef } from 'react';
import * as Style from ".././Common/SignUpStyle";
import { Link } from 'react-router-dom';
import SignUpContext from "./SignUpContext";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function NicknameInputForm() {


    const {nickname , setNickname} = useContext(SignUpContext);
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');
    const [isIdDuplicated, setIsIdDuplicated] = useState(false);
    const [isNicknameAvailable, setIsNicknameAvailable] = useState(false);

    const nicknameRef = useRef();
    const navigate = useNavigate();

    const onChangeNicknameHandler = (e) => {
        const value = e.target.value;

        if(value.trim() === "") { // 닉네임 공백이면 서버 요청 x
            return;
        }

        // nickname 중복 검사
        isNicknameDuplicateCheck(value);
    }

    const isNicknameDuplicateCheck = async(nickname) => {
        await axios.post("http://localhost:9000/users/check-duplication/nickname" , {
            nickname : nickname
        })
        .then((response) => {
            console.log(response);

            if(response?.data?.isDuplicated == null || response.data.message == null) {
                setError("잘못된 요청입니다.");
                return;
            }

            setIsNicknameAvailable(!response.data.isDuplicated);
            setMessage(response.data.message);

        })
        .catch(function(error) {
            console.log(error);
            setError("요청 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
        })
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
        const nickname = nicknameRef.current.value;
        setNickname(nickname);

        console.log(nickname);


    };


    return (
        <form onSubmit={onFormSubmit}>
            <Style.Instruction>닉네임을 입력해주세요.</Style.Instruction>

            <Style.InputField
                ref = {nicknameRef}
                onChange={onChangeNicknameHandler}
                type="text"
                id='nickname'
                name='nickname'
                value={nickname}
                placeholder="닉네임 입력"
                theme='underLine'
                maxLength={15}
            />

            <DisplayMessage error={error} message={message} />

            <Style.StepInfo>3 of 3</Style.StepInfo>
            <Style.ProgressSection>
                <Style.ProgressBar />
            </Style.ProgressSection>

            <Style.NextButton type = "submit" disabled={isNicknameAvailable} >다음</Style.NextButton>

            <Style.FooterIndicator />
        </form>
    );
}

export default NicknameInputForm;
