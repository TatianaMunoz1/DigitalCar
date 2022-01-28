import React from "react";
import styles from "./Subheader.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronLeft } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

function Subheader({ title, subtitle, link }) {
  return (
    <div className={styles.header_box}>
      <div className={styles.title_box}>
        {subtitle && <h3 className={styles.subtitle}>{subtitle}</h3>}
        <h1 className={styles.title}>{title}</h1>
      </div>
      <Link to={link}>
        <FontAwesomeIcon icon={faChevronLeft} className={styles.chevron_left} />
      </Link>
    </div>
  );
}

export default Subheader;
