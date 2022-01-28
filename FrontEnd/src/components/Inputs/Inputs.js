import React, { useState } from "react";
import {
  faCheckCircle,
  faTimesCircle,
} from "@fortawesome/free-solid-svg-icons";
import styles from "./Inputs.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Inputs = ({
  value,
  type,
  label,
  placeholder,
  name,
  errorLegend,
  regularExpression,
  onChange,
  validState,
  setValidState,
  hideCheck,
}) => {
  const [validation, setValidation] = useState(null);

  const validationFunction = () => {
    if (regularExpression) {
      if (regularExpression.test(value)) {
        setValidation(true);
        validState &&
          setValidState({
            ...validState,
            [name]: true,
          });
      } else {
        setValidation(false);
        validState &&
          setValidState({
            ...validState,
            [name]: false,
          });
      }
    }
    hideCheck && setValidation(null);
  };

  // useEffect(() => {
  //   setValidState({
  //     ...validState,
  //     [name]: validation,
  //   });
  // }, [validation]);

  return (
    <div className={styles.container} >
      {label && (
        <label htmlFor={name} className={styles.label}>
          {label}
        </label>
      )}
      <div
        className={styles.input_box}
      >
        <input

          type={type}
          data-testid="testemail"
          placeholder={placeholder}
          className={
            (validation === null && styles.input) ||
            (validation === true && `${styles.input} ${styles.valid_input}`) ||
            (validation === false && `${styles.input} ${styles.invalid_input}`)
          }
          id={name}
          onChange={onChange}
          value={value}
          name={name}
          onKeyUp={validationFunction}
          onBlur={validationFunction}
        />
        <FontAwesomeIcon
          icon={validation === true ? faCheckCircle : faTimesCircle}
          color={validation === true ? "#1ed12d" : "#FF0000"}
          className={
            validation === null
              ? styles.icon_validation_hide
              : styles.icon_validation_show
          }
        />
      </div>
      {validation === false && (
        <p className={styles.error_legend}>{errorLegend}</p>
      )}
    </div>
  );
};

export default Inputs;
