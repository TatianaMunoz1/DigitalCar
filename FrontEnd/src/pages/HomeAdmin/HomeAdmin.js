import React, { useState } from "react";
import styles from "./HomeAdmin.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronDown, faChevronUp } from "@fortawesome/free-solid-svg-icons";
import Button from "../../components/Button/Button";
import ModifyProductPolicySection from "../../components/ModifyProductPolicySection/ModifyProductPolicySection";
import Administration from "../../components/Administration/Administration";
import Subheader from "../../components/Subheader/Subheader";

export default function HomeAdmin(props) {
  const [productManagementSelected, setProductManagementSelected] =
    useState(true);
  const [productManagementMobileSelected, setProductManagementMobileSelected] =
    useState(false);
  const [productPolicyMobileSelected, setProductPolicyMobileSelected] =
    useState(false);
  const [productPolicySelected, setProductPolicySelected] = useState(false);

  return (
    <div className={styles.container}>
      <div className={styles.container_header}>
        <Subheader
          title="Administración"
          subtitle={
            productPolicySelected || productPolicyMobileSelected
              ? "POLÍTICAS DEL PRODUCTO"
              : "CARGAR NUEVO VEHÍCULO"
          }
          link={"/"}
        />
      </div>

      <div className={styles.container_forms_mobile}>
        <div>
          <button
            type="button"
            className={
              productManagementMobileSelected
                ? styles.button_1_mobile_selected
                : styles.button_1_mobile
            }
            onClick={() => {
              setProductManagementMobileSelected(
                !productManagementMobileSelected
              );
              setProductPolicyMobileSelected(false);
            }}
          >
            <span className={styles.btn_content}>
              CARGAR NUEVO VEHÍCULO
              <FontAwesomeIcon
                icon={
                  productManagementMobileSelected ? faChevronUp : faChevronDown
                }
              />
            </span>
          </button>
          {productManagementMobileSelected && <Administration user={props.user}/>}
        </div>
        <div>
          <button
            type="button"
            className={
              productPolicyMobileSelected
                ? styles.button_2_mobile_selected
                : styles.button_2_mobile
            }
            onClick={() => {
              setProductPolicyMobileSelected(!productPolicyMobileSelected);
              setProductManagementMobileSelected(false);
            }}
          >
            <span className={styles.btn_content}>
              POLÍTICAS DEL PRODUCTO
              <FontAwesomeIcon
                icon={productPolicyMobileSelected ? faChevronUp : faChevronDown}
              />
            </span>
          </button>
          {productPolicyMobileSelected && <ModifyProductPolicySection user={props.user}/>}
        </div>
      </div>

      <div className={styles.container_forms_tablet_desktop}>
        <button
          type="button"
          className={
            productManagementSelected
              ? styles.button_1_selected
              : styles.button_1
          }
          onClick={() => {
            setProductManagementSelected(true);
            setProductPolicySelected(false);
          }}
        >
          CARGAR NUEVO VEHÍCULO
        </button>
        <button
          type="button"
          className={
            productPolicySelected ? styles.button_2_selected : styles.button_2
          }
          onClick={() => {
            setProductPolicySelected(true);
            setProductManagementSelected(false);
          }}
        >
          POLÍTICAS DEL PRODUCTO
        </button>
        {productManagementSelected && <Administration user={props.user}/>}
        {productPolicySelected && <ModifyProductPolicySection user={props.user}/>}
      </div>
    </div>
  );
}
