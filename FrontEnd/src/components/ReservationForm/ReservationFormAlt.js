import React, { useState, useEffect } from "react";
import styles from "./ReservationForm.module.css";
import Inputs from "../Inputs/Inputs";

function ReservationForm(props) {
  const [validationCityInput, setValidationCityInput] = useState({
    city: false,
  });
  const [userCityInput, setUserCityInput] = useState({ city: "" });

  const expressions = { city: /^([a-zA-Z0-9., ]){1,40}$/ };

  const handleChange = (e) => {
    const target = e.target;
    const name = target.name;
    const value = target.value;
    const valid = target.validState;
    setUserCityInput({ ...userCityInput, [name]: value });
    setValidationCityInput({ ...validationCityInput, [name]: valid });
  };

  useEffect(() => {
    props.handleValidationCity(validationCityInput.city, userCityInput.city);
  }, [validationCityInput, userCityInput]);

  return (
    <div className={styles.container}>
      <form className={styles.form}>
        <div className={styles.form_columns}>
          <div className={styles.form_first_section}>
            <div>
              <label className={styles.label}>Nombre</label>
              <input
                className={styles.input_name}
                type="text"
                id="name"
                value={props.user.name}
                disabled
              />
            </div>
            <div>
              <label className={styles.label}>Apellido</label>
              <input
                className={styles.input_lastname}
                type="text"
                id="lastname"
                value={props.user.lastName}
                disabled
              />
            </div>
          </div>
          <div className={styles.form_second_section}>
            <div>
              <label className={styles.label}>Correo electrónico</label>
              <input
                className={styles.input_mail}
                type="text"
                id="mail"
                value={props.user.email}
                disabled
              />
            </div>
            <div className={styles.input_lastname_container}>
              <Inputs
                onChange={handleChange}
                value={userCityInput.city}
                type="text"
                label="Ciudad"
                name="city"
                errorLegend="Debe ingresar su ciudad de residencia."
                regularExpression={expressions.city}
                validState={validationCityInput}
                setValidState={setValidationCityInput}
              />
            </div>
          </div>
        </div>
        <div className={styles.form_third_section}>
          <textarea
            className={styles.input_textarea}
            name="textarea"
            rows="4"
            placeholder=" Escriba un mensaje."
          />
          <div className={styles.input_checkbox}>
            <label
              className={styles.container_checkbox}
              htmlFor="vacuna-covid19"
            >
              Vacunado contra el COVID-19
              <input
                type="checkbox"
                id="vacuna-covid19"
                name="vacuna-covid19"
              />
              <span className={styles.checkmark}></span>
            </label>
          </div>
        </div>
      </form>
    </div>
  );
}

export default ReservationForm;
