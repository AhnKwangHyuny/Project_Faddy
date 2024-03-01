import React, { useState, useEffect } from 'react';
import styled from 'styled-components';

const TimerText = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2em; /* 글자 크기 */
  color: #ff0000; /* 글자 색상 */
  margin-top: 20px; /* 상단 여백 */
`;

function Timer({ initialSeconds }) {
  const [seconds, setSeconds] = useState(initialSeconds);

  useEffect(() => {
    if (seconds > 0) {
      const timerId = setTimeout(() => {
        setSeconds(seconds - 1);
      }, 1000);

      return () => clearTimeout(timerId);
    } else {
      // 시간이 다 되었을 때 경고창 표시
      alert('시간이 완료되었습니다. 다시 인증 부탁드립니다.');
    }
  }, [seconds]);

  const minutes = Math.floor(seconds / 60);
  const remainingSeconds = seconds % 60;

  return (
    <TimerText>
      {seconds > 0 ? `${minutes}:${remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds}` : '시간이 완료되었습니다.'}
    </TimerText>
  );
}

export default Timer;
