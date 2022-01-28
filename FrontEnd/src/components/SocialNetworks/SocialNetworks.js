import React from "react";
import styles from "./SocialNetworks.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFacebook,
  faInstagram,
  faLinkedin,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";

function SocialNetworks({ snStyle }) {
  return (
    <div
      className={[styles.social_networks_container, styles[snStyle]].join(" ")}
    >
      <a
        href="https://www.facebook.com"
        target="blank"
        className={[styles.sn, styles[snStyle]].join(" ")}
      >
        <FontAwesomeIcon icon={faFacebook} />
      </a>
      <a
        href="https://www.linkedin.com"
        target="blank"
        className={[styles.sn, styles[snStyle]].join(" ")}
      >
        <FontAwesomeIcon icon={faLinkedin} />
      </a>
      <a
        href="https://www.twitter.com"
        target="blank"
        className={[styles.sn, styles[snStyle]].join(" ")}
      >
        <FontAwesomeIcon icon={faTwitter} />
      </a>
      <a
        href="https://www.instagram.com"
        target="blank"
        className={[styles.sn, styles[snStyle]].join(" ")}
      >
        <FontAwesomeIcon icon={faInstagram} />
      </a>
    </div>
  );
}

export default SocialNetworks;
