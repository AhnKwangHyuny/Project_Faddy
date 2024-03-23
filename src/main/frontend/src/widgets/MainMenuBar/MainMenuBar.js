import { css } from "@emotion/css";

const MainMenuBar = () => {
  return (
    <div
      className={css`
        position: fixed; /* 위치를 고정시킵니다 */
        bottom: 0; /* 화면의 가장 아래에 위치하도록 설정합니다 */
        left: 0; /* 화면의 왼쪽 끝에 위치하도록 설정합니다 */
        width: 100%; /* 컨테이너의 너비를 화면 너비와 동일하게 설정합니다 */
        display: flex;
        flex-direction: row;
        align-items: center; /* 항목들을 세로 방향으로 중앙에 배치합니다 */
        justify-content: flex-start; /* 항목들을 가로 방향에서 시작점에 배치합니다 */
        letter-spacing: normal;
        text-align: center;
        font-size: 10.3px;
        color: #fff;
        font-family: Roboto;
        background: linear-gradient(180deg, #1f1b1b, rgba(21, 20, 20, 0.8)); /* 선택적: 배경 설정 */
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.25); /* 선택적: 그림자 효과 추가 */
        z-index: 1000; /* 다른 요소들 위에 오도록 z-index 설정 */
      `}
    >
      <div
        className={css`
          flex: 1;
          background: linear-gradient(180deg, #1f1b1b, rgba(21, 20, 20, 0.8));
          box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.25);
          border: 0px solid #000;
          box-sizing: border-box;
          display: flex;
          flex-direction: row;
          align-items: flex-start;
          justify-content: flex-start;
          padding: 0px 16px;
          gap: 12px;
          max-width: 100%;
        `}
      >
        <img
          className={css`
            height: 45px;
            width: 390px;
            position: relative;
            display: none;
            min-height: 45px;
            max-width: 100%;
          `}
          alt=""
          src="/rectangle-1735.svg"
        />
        <div
          className={css`
            width: 61px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: flex-start;
            padding: 5px 7px 2px;
            box-sizing: border-box;
            position: relative;
            gap: 2px;
            z-index: 1;
          `}
        >
          <div
            className={css`
              height: 24px;
              display: flex;
              flex-direction: row;
              align-items: flex-start;
              justify-content: flex-start;
              padding: 0px 12px 0px 11px;
              box-sizing: border-box;
            `}
          >
            <img
              className={css`
                height: 24px;
                width: 24px;
                position: relative;
              `}
              alt=""
              src="/insta.svg"
            />
          </div>
          <div
            className={css`
              width: 100%;
              height: 100%;
              position: absolute;
              margin: 0 !important;
              top: 0px;
              right: 0px;
              bottom: 0px;
              left: 0px;
              background-color: rgba(217, 217, 217, 0);
              z-index: 1;
            `}
          />
          <b
            className={css`
              align-self: stretch;
              position: relative;
              z-index: 2;
            `}
          >
            스냅
          </b>
        </div>
        <div
          className={css`
            width: 64px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: flex-start;
            padding: 0px 3px 0px 0px;
            box-sizing: border-box;
          `}
        >
          <div
            className={css`
              align-self: stretch;
              display: flex;
              flex-direction: column;
              align-items: flex-start;
              justify-content: flex-start;
              padding: 5px 7px 2px;
              position: relative;
              gap: 2px;
              z-index: 1;
            `}
          >
            <div
              className={css`
                height: 24px;
                display: flex;
                flex-direction: row;
                align-items: flex-start;
                justify-content: flex-start;
                padding: 0px 12px 0px 11px;
                box-sizing: border-box;
              `}
            >
              <img
                className={css`
                  height: 24px;
                  width: 24px;
                  position: relative;
                `}
                alt=""
                src="/chat-alt.svg"
              />
            </div>
            <div
              className={css`
                width: 100%;
                height: 100%;
                position: absolute;
                margin: 0 !important;
                top: 0px;
                right: 0px;
                bottom: 0px;
                left: 0px;
                background-color: rgba(217, 217, 217, 0);
                z-index: 1;
              `}
            />
            <b
              className={css`
                align-self: stretch;
                position: relative;
                z-index: 2;
              `}
            >
              피드
            </b>
          </div>
        </div>
        <div
          className={css`
            height: 45px;
            width: 67px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: flex-start;
            padding: 0px 6px 0px 0px;
            box-sizing: border-box;
          `}
        >
          <img
            className={css`
              align-self: stretch;
              height: 45px;
              position: relative;
              max-width: 100%;
              overflow: hidden;
              flex-shrink: 0;
              object-fit: cover;
              z-index: 3;
            `}
            loading="lazy"
            alt=""
            src="/group-3215@2x.png"
          />
        </div>
        <div
          className={css`
            width: 122px;
            display: flex;
            flex-direction: row;
            align-items: flex-start;
            justify-content: flex-start;
          `}
        >
          <div
            className={css`
              flex: 1;
              display: flex;
              flex-direction: column;
              align-items: flex-start;
              justify-content: flex-start;
              padding: 5px 7px 2px;
              position: relative;
              gap: 2px;
              z-index: 4;
            `}
          >
            <div
              className={css`
                height: 24px;
                display: flex;
                flex-direction: row;
                align-items: flex-start;
                justify-content: flex-start;
                padding: 0px 12px 0px 11px;
                box-sizing: border-box;
              `}
            >
              <img
                className={css`
                  height: 24px;
                  width: 24px;
                  position: relative;
                  z-index: 3;
                `}
                alt=""
                src="/favorite.svg"
              />
            </div>
            <div
              className={css`
                width: 100%;
                height: 100%;
                position: absolute;
                margin: 0 !important;
                top: 0px;
                right: 0px;
                bottom: 0px;
                left: 0px;
                background-color: rgba(217, 217, 217, 0);
              `}
            />
            <b
              className={css`
                align-self: stretch;
                position: relative;
                z-index: 1;
              `}
            >
              팔로잉
            </b>
          </div>
          <div
            className={css`
              flex: 1;
              display: flex;
              flex-direction: column;
              align-items: flex-start;
              justify-content: flex-start;
              padding: 4px 7px 2px;
              position: relative;
              z-index: 1;
            `}
          >
            <div
              className={css`
                width: 100%;
                height: 100%;
                position: absolute;
                margin: 0 !important;
                top: 0px;
                right: 0px;
                bottom: 0px;
                left: 0px;
                background-color: rgba(217, 217, 217, 0);
              `}
            />
            <div
              className={css`
                display: flex;
                flex-direction: row;
                align-items: flex-start;
                justify-content: flex-start;
                padding: 0px 10px;
              `}
            >
              <div
                className={css`
                  height: 27px;
                  width: 27px;
                  position: relative;
                  z-index: 2;
                `}
              >
                <div
                  className={css`
                    position: absolute;
                    height: 57.41%;
                    width: 74.07%;
                    top: 21.48%;
                    right: 12.96%;
                    bottom: 21.11%;
                    left: 12.96%;
                    border-radius: 2px;
                    border: 2px solid #fff;
                    box-sizing: border-box;
                  `}
                />
                <img
                  className={css`
                    position: absolute;
                    height: 16.67%;
                    width: 66.67%;
                    top: 37.41%;
                    right: 16.67%;
                    bottom: 45.93%;
                    left: 16.67%;
                    border-radius: 2px;
                    max-width: 100%;
                    overflow: hidden;
                    max-height: 100%;
                    z-index: 1;
                  `}
                  alt=""
                  src="/following-and-messages.svg"
                />
              </div>
            </div>
            <b
              className={css`
                align-self: stretch;
                position: relative;
                z-index: 1;
              `}
            >
              메세지
            </b>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MainMenuBar;