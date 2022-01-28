import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShareAlt } from "@fortawesome/free-solid-svg-icons";
import styles from "./ShareButton.module.css";
import {
  FacebookShareButton,
  FacebookMessengerShareButton,
  TwitterShareButton,
  TelegramShareButton,
  WhatsappShareButton,
  EmailShareButton,
  EmailIcon,
  FacebookIcon,
  FacebookMessengerIcon,
  TelegramIcon,
  TwitterIcon,
  WhatsappIcon,
} from "react-share";

function ShareButton(props) {
  const [clickShareBtn, setClickShareBtn] = useState(false);
  const shareUrl = props.url;
  const title = "Digital Car";

  return (
    <div>
      <FontAwesomeIcon
        icon={faShareAlt}
        className={styles.icon_share}
        onClick={() => setClickShareBtn(!clickShareBtn)}
      />
      {clickShareBtn && (
        <div className={styles.icons_share_content}>
          <EmailShareButton url={shareUrl} quote={title}>
            <EmailIcon size={32} round={true} />
          </EmailShareButton>
          <FacebookShareButton url={shareUrl} quote={title}>
            <FacebookIcon size={32} round={true} />
          </FacebookShareButton>
          <FacebookMessengerShareButton url={shareUrl} quote={title}>
            <FacebookMessengerIcon size={32} round={true} />
          </FacebookMessengerShareButton>
          <TelegramShareButton url={shareUrl} quote={title}>
            <TelegramIcon size={32} round={true} />
          </TelegramShareButton>
          <TwitterShareButton url={shareUrl} quote={title}>
            <TwitterIcon size={32} round={true} />
          </TwitterShareButton>
          <WhatsappShareButton url={shareUrl} quote={title}>
            <WhatsappIcon size={32} round={true} />
          </WhatsappShareButton>
        </div>
      )}
    </div>
  );
}

export default ShareButton;
