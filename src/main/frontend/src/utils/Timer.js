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

function Timer({ initialSeconds, onTimerEnd }) {
  const [seconds, setSeconds] = useState(initialSeconds);

 useEffect(() => {
   let timerId;
   if (seconds > 0) {
     timerId = setTimeout(() => {
       setSeconds(seconds - 1);
     }, 1000);
   } else if (seconds === 0 && onTimerEnd) {
     onTimerEnd();
   }

   return () => {
     clearTimeout(timerId);
   };
 }, [seconds, onTimerEnd]);


  const minutes = Math.floor(seconds / 60);
  const remainingSeconds = seconds % 60;

  return (
    <TimerText>
      {`${minutes}:${remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds}`}
    </TimerText>
  );
}

export default Timer;