import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import Inputs from "../../components/Inputs/Inputs";
import Button from "../../components/Button/Button";
import Swal from "sweetalert2";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faExclamationCircle } from "@fortawesome/free-solid-svg-icons";
import "animate.css";
import styles from "./Login.module.css";
import Url from "../../Url";

const Login = (props) => {
  // const history = useHistory();
  const expressions = {
    password: /^.{6,12}$/, // 6 a 12 digitos.
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
  };
  const [errorMsg, setErrorMsg] = useState("");
  const [showErrorMsg, setShowErrorMsg] = useState(false);
  const [showMessageLogin, setShowMessageLogin] = useState(false);
  const [userData, setUserData] = useState({
    email: "",
    password: "",
  });
  const [userValidation, setUserValidation] = useState({
    email: false,
    password: false,
  });

  const loginMessage = (name, lastName) => {
    const Toast = Swal.mixin({
      toast: true,
      position: "top-end",
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      background: "rgba(239, 243, 246, 1)",
      didOpen: (toast) => {
        toast.addEventListener("mouseenter", Swal.stopTimer);
        toast.addEventListener("mouseleave", Swal.resumeTimer);
      },
    });
    Toast.fire({
      icon: "success",
      title: `<h4>Bienvenido ${name} ${lastName}</h4>`,
    });
  };

  useEffect(() => {
    props.renderHeader();
    return () => props.renderHeader();
  }, []);

  useEffect(() => {
    setShowMessageLogin(props.showMessageLogin);
  }, [showMessageLogin]);

  const handlerSubmit = (e) => {
    e.preventDefault();
    const data = {
      email: userData.email,
      password: userData.password,
    };
    axios
      .post(Url+"/login", data)
      .then((response) => {
        if (response.status === 200) {
          props.changeUser(response.data);
          setShowErrorMsg(false);

          loginMessage(response.data.name, response.data.lastName);
          // history.push("/");
        }
      })
      .catch((error) => {
        setShowErrorMsg(true);
        if (error.response.status === 403)
          setErrorMsg(
            "Por favor, vuelva a intentarlo. Sus credenciales son inválidas"
          );
        else
          setErrorMsg(
            "Lamentablemente no ha podido iniciar sesión. Por favor intente más tarde"
          );
      });
  };

  const handleChange = (e) => {
    const target = e.target;
    const value = target.value;
    const valid = target.validState;
    const name = target.name;

    setUserData({
      ...userData,
      [name]: value,
    });
    setUserValidation({
      ...userValidation,
      [name]: valid,
    });
  };

  return (
    <main className={styles.main}>
      {showMessageLogin && (
        <div className={styles.login_request}>
          <FontAwesomeIcon icon={faExclamationCircle} />
          &nbsp; Para realizar una reserva necesitas estar logueado
        </div>
      )}
      <form className={styles.form} action="" onSubmit={handlerSubmit}>
        <h1 className={styles.title}>Iniciar sesión</h1>
        <Inputs
          className={`email`}
          onChange={handleChange}
          value={userData.email}
          type="email"
          label="Email"
          placeholder="correo@correo.com"
          name="email"
          errorLegend="El correo solo puede contener letras, numeros, puntos, guiones y guion bajo."
          regularExpression={expressions.email}
          validState={userValidation}
          setValidState={setUserValidation}
        />
        <Inputs
          className={`password`}
          onChange={handleChange}
          value={userData.password}
          type="password"
          label="Contraseña"
          name="password"
          errorLegend="La contraseña tiene que ser de 6 a 12 dígitos."
          regularExpression={expressions.password}
          validState={userValidation}
          setValidState={setUserValidation}
        />

        {showErrorMsg && <p className={styles.error_message}>{errorMsg}</p>}
        <div className={styles.button_container}>
          <Button
            type="submit"
            title="Enviar"
            btnStyle="btn--full"
            btnSize="btn--responsive"
            onClick={handlerSubmit}
          />
          <p className={styles.register_box}>
            ¿Aún no tienes una cuenta? &nbsp;
            <Link to="/register">
              <span className={styles.register_label}>Regístrate</span>
            </Link>
          </p>
        </div>
      </form>
    </main>
  );
};

export default Login;
