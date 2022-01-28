import React, { useState, useEffect } from "react";
import { Link, useHistory } from "react-router-dom";
import Inputs from "../../components/Inputs/Inputs";
import Button from "../../components/Button/Button";
import styles from "./Register.module.css";
import Swal from "sweetalert2";
import "animate.css";
import axios from "axios";
import Url from "../../Url";

const Register = (props) => {
  const history = useHistory();
  const [formularioValido, setFormularioValido] = useState(null);
  const [errorMsg, setErrorMsg] = useState("");
  const [showErrorMsg, setShowErrorMsg] = useState(false);
  const [userData, setUserData] = useState({
    name: "",
    lastName: "",
    email: "",
    password: "",
    password2: "",
  });
  const [userValidation, setUserValidation] = useState({
    name: false,
    lastName: false,
    email: false,
    password: false,
    password2: null,
  });

  const expressions = {
    name: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
    password: /^.{6,12}$/,
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
  };

  useEffect(() => {
    props.renderHeader();
    return () => props.renderHeader();
  }, []);

  const onSubmit = (e) => {
    e.preventDefault();

    if (
      userValidation.name === true &&
      userValidation.lastName === true &&
      userValidation.password === true &&
      userValidation.password2 === true &&
      userValidation.email === true
    ) {
      const data = {
        name: userData.name,
        lastName: userData.lastName,
        email: userData.email,
        password: userData.password,
      };

      axios
        .post(Url+"/register", data)
        .then((response) => {
          setShowErrorMsg(false);

          if (response.status === 201) {
            props.changeUser(response.data);
            setFormularioValido(true);
            setUserData({
              name: "",
              lastName: "",
              email: "",
              password: "",
              password2: "",
            });

            Swal.fire({
              icon: "success",
              title: "Gracias por registrarse!",
              showClass: {
                popup: "animate__animated animate__fadeInDown",
              },
              hideClass: {
                popup: "animate__animated animate__fadeOutUp",
              },
            });

            history.push("/");
          }
        })
        .catch((error) => {
          setShowErrorMsg(true);

          if (error.response.status === 403)
            setErrorMsg(
              "Por favor, vuelva a intentarlo. Sus credenciales son inválidas"
            );
          else if (error.response.status === 400)
            setErrorMsg(
              "El mail ingresado corresponde a un mail ya registrado"
            );
          else
            setErrorMsg(
              "Lamentablemente no ha podido registrarse. Por favor intente más tarde"
            );
        });
    } else {
      setFormularioValido(false);
      setShowErrorMsg(true);
      setErrorMsg(
        "Por favor, vuelva a intentarlo. Sus credenciales son inválidas"
      );
    }
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

    if (userData.password !== "") {
      if (userData.password !== userData.password2) {
        setUserValidation({ ...userValidation, password2: false });
      } else {
        setUserValidation({ ...userValidation, password2: true });
      }
    }
  };

  return (
    <main className={styles.main}>
      <form className={styles.form} action="" onSubmit={onSubmit}>
        <h1 className={styles.title}>Crear cuenta</h1>
        <Inputs className="input1"
          onChange={handleChange}
          value={userData.name}
          type="text"
          label="Nombre"
          name="name"
          errorLegend="El nombre solo puede contener letras y espacios."
          regularExpression={expressions.name}
          validState={userValidation}
          setValidState={setUserValidation}
        />
        <Inputs
          onChange={handleChange}
          value={userData.lastName}
          type="text"
          label="Apellido"
          name="lastName"
          errorLegend="El apellido solo puede contener letras y espacios."
          regularExpression={expressions.name}
          validState={userValidation}
          setValidState={setUserValidation}
        />
        <Inputs className="input2"
          onChange={handleChange}
          value={userData.email}
          type="email"
          label="Email"
          name="email"
          errorLegend="Ingrese un correo valido."
          regularExpression={expressions.email}
          validState={userValidation}
          setValidState={setUserValidation}
        />
        <Inputs
          onChange={handleChange}
          value={userData.password}
          type="password"
          label="Contraseña"
          name="password"
          errorLegend="La contraseña debe tener de 6 a 12 dígitos."
          regularExpression={expressions.password}
          validState={userValidation}
          setValidState={setUserValidation}
        />
        <Inputs
          onChange={handleChange}
          value={userData.password2}
          type="password"
          label="Repetir Contraseña"
          name="password2"
          errorLegend="Ambas contraseñas deben ser iguales."
          regularExpression={expressions.password}
          validState={userValidation}
          setValidState={setUserValidation}
          hideCheck={true}
        />
        {userValidation.password2 === false && (
          <p className={styles.error_message_password}>
            Ambas contraseñas deben ser iguales.
          </p>
        )}

        {showErrorMsg && <p className={styles.error_message}>{errorMsg}</p>}

        <div className={styles.button_container}>
          <Button
            type="submit"
            title="Registro"
            btnStyle="btn--full"
            btnSize="btn--responsive"
            onClick={onSubmit}
          />
          <p className={styles.login_box}>
            ¿Ya tienes una cuenta? &nbsp;
            <Link to="/login">
              <span className={styles.login_label}>Inicia sesión</span>
            </Link>
          </p>
        </div>
      </form>
    </main>
  );
};

export default Register;
