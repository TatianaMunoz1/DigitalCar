import React from "react";
import styles from "./Button.module.css";
import { Link } from "react-router-dom";

function Button({ title, link, type, onClick, btnSize, btnStyle }) {
  return (
    <Link to={link ? link : "#"}>
      <button
        className={[styles.btn, styles[btnStyle], styles[btnSize]].join(" ")}
        type={type}
        onClick={onClick}
      >
        {title}
      </button>
    </Link>
  );
}

export default Button;
