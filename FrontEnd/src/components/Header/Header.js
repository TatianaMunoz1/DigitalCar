import React, { useState } from "react";
import { useHistory, useLocation } from "react-router-dom";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faTimes } from "@fortawesome/free-solid-svg-icons";
import logo from "../../img/logo.png";
import Button from "../Button/Button";
import styles from "./Header.module.css";
import SocialNetworks from "../SocialNetworks/SocialNetworks";

function Header(props) {
  const history = useHistory();
  const location = useLocation();

  const [clickHeaderIcon, setClickHeaderIcon] = useState(false);
  const getInitials = () => {
    const name = props.user.name;
    return name[0] + props.user.lastName[0];
  };

  const handlerClickLogout = (e) => {
    e.preventDefault();
    localStorage.removeItem("user");
    props.changeUser({id: 0,
      name: "",
      lastName: "",
      email: "",
      token: "",
      role: ""});
    history.push("/");
  };

  const userProfile = (
    <>
      <div className={styles.img_user}>
        <p className={styles.img_user_box}>{getInitials()}</p>
      </div>
      <p className={styles.img_user_phrase}>
        Hola
        <br />
        <span className={styles.img_user_name}>
          {props.user.name + " " + props.user.lastName}
        </span>
      </p>
      <FontAwesomeIcon
        id="logoutButton"
        icon={faTimes}
        className={styles.btn_logout}
        onClick={handlerClickLogout}
      />
    </>
  );

  return (
    <div
      className={[styles.container, clickHeaderIcon && styles.active_menu].join(
        " "
      )}
    >
      <Link className={styles.logo_container} to="/">
        <img src={logo} className={styles.logo} alt="logo digital car" />
      </Link>
      <nav>
        <ul
          className={[styles.menu, clickHeaderIcon && styles.active].join(" ")}
        >
          <li className={styles.side_view}></li>
          <li className={styles.nav_title}>
            {props.user.name === "" ? (
              <h2>MENÚ</h2>
            ) : (
              <div className={styles.panel_user_mobile}>{userProfile}</div>
            )}
          </li>
          <li className={styles.nav_items}>
            {props.user.name === "" ? (
              <>
                {location.pathname !== "/register" && (
                  <div className={styles.nav_item}>
                    <Button
                      data-testid="location-register"
                      title="Crear cuenta"
                      link="/register"
                      btnStyle="btn--void"
                      btnSize="btn--medium"
                      onClick={() => setClickHeaderIcon(false)}
                    />
                  </div>
                )}
                {location.pathname !== "/login" && (
                  <div className={styles.nav_item}>
                    <Button
                      title="Iniciar sesión"
                      link="/login"
                      btnStyle="btn--void"
                      btnSize="btn--medium"
                      onClick={() => setClickHeaderIcon(false)}
                    />
                  </div>
                )}
              </>
            ) : (
              <>
                {props.user.role !== "admin" ? (
                  <div className={styles.nav_item_by_role}>
                    <Button
                      title="Mis reservas"
                      link="/reservation-by-user"
                      btnStyle="btn--void"
                      btnSize="btn--medium"
                      onClick={() => setClickHeaderIcon(false)}
                    />
                  </div>
                ) : (
                  <div className={styles.nav_item_by_role}>
                    <Button
                      title="Administración"
                      link="/home-admin"
                      btnStyle="btn--void"
                      btnSize="btn--medium"
                      onClick={() => setClickHeaderIcon(false)}
                    />
                  </div>
                )}
                <div className={styles.panel_user_descktop}>{userProfile}</div>
              </>
            )}
            <div className={styles.social_networks}>
              <SocialNetworks snStyle="sn--style--menu" />
            </div>
          </li>
        </ul>
        <div
          className={styles.nav_burger}
          onClick={() => setClickHeaderIcon(!clickHeaderIcon)}
        >
          <FontAwesomeIcon icon={clickHeaderIcon ? faTimes : faBars} />
        </div>
      </nav>
    </div>
  );
}

export default Header;
