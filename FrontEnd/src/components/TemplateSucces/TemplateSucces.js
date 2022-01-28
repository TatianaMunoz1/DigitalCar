import React from "react";
import Button from "../../components/Button/Button";
import styles from "./TemplateSucces.module.css";
import succesIcon from "../../img/SuccesIcon.png";

function TemplateSucces({ title, subtitle }) {
  return (
    <div className={styles.container}>
      <div className={styles.container_message}>
        <img src={succesIcon} alt="SuccesIcon" className={styles.icon} />
        <h1 className={styles.title}>{title}</h1>
        {subtitle && <h2 className={styles.subtitle}>{subtitle}</h2>}
        <div className={styles.button}>
          <Button
            type="button"
            title="ok"
            link="/"
            btnStyle="btn--full"
            btnSize="btn--responsive"
            onClick={() => null}
          />
        </div>
      </div>
    </div>
  );
}

export default TemplateSucces;
