import React from "react";
import SocialNetworks from "../SocialNetworks/SocialNetworks";
import styles from "./Footer.module.css";

function Footer() {
  return (
    <footer className={styles.container} data-testid="footerTestId">
      <p className={styles.line}>©2021 Digital Booking</p>
      <div className={styles.social_networks}>
        <SocialNetworks snStyle="sn--style--footer" />
      </div>
    </footer>
  );
}

export default Footer;
