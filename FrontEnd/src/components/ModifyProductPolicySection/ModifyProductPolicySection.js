import React, { useState, useEffect } from "react";
import { useHistory } from "react-router";
import Button from "../Button/Button";
import styles from "./ModifyProductPolicySection.module.css";
import Swal from "sweetalert2";
import axios from "axios";
import Url from "../../Url"

function ModifyProductPolicySection(props) {
  const history = useHistory();
  const [submit, setSubmit] = useState(null);
  const [productPolicy, setProductPolicy] = useState({
    deliveryStandards: "",
    healthAndSecurity: "",
    cancellationPolicy: "",
  });

  useEffect(() => {
    axios
      .get(Url+"/politics")
      .then((response) => {
        if (response.data !== "null")
          setProductPolicy({
            deliveryStandards: response.data.delivery,
            healthAndSecurity: response.data.healthAndSecurity,
            cancellationPolicy: response.data.cancellation,
          });
      })
      .catch((error) => {});
  }, []);

  const handleInputChange = (e) => {
    const target = e.target;
    const value = target.value;
    const name = target.name;

    setProductPolicy({
      ...productPolicy,
      [name]: value,
    });
  };

  const onSubmit = (e) => {
    e.preventDefault();
    setSubmit(true);

    const data = {
      delivery: `${productPolicy.deliveryStandards}`,
      healthAndSecurity: `${productPolicy.healthAndSecurity}`,
      cancellation: `${productPolicy.cancellationPolicy}`,
    };

    if (
      productPolicy.deliveryStandards !== "" &&
      productPolicy.healthAndSecurity !== "" &&
      productPolicy.cancellationPolicy !== ""
    ) {
      const config = {
        headers: { Authorization: props.user.token },
      };

      axios
        .put("http://localhost:8080/politics", data, config)
        .then(function (response) {
          if (response.status === 200) {
            Swal.fire({
              icon: "success",
              title: "Se actualizaron las políticas del producto.",
              showDenyButton: true,
              confirmButtonText: "Ir al home",
            }).then((result) => {
              if (result.isConfirmed) {
                history.push("/");
              }
            });
          } else {
            const Toast = Swal.mixin({
              toast: true,
              position: "top-end",
              showConfirmButton: false,
              background: "#ffc2c2",
              timer: 5000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener("mouseenter", Swal.stopTimer);
                toast.addEventListener("mouseleave", Swal.resumeTimer);
              },
            });
            Toast.fire({
              icon: "error",
              title:
                "<h4 style='color:#B00020'>Lamentablemente el producto no ha podido crearse. </br>Por favor intente más tarde.</h4>",
            });
          }
        })
        .catch(function (error) {
          const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            background: "#ffc2c2",
            timer: 5000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener("mouseenter", Swal.stopTimer);
              toast.addEventListener("mouseleave", Swal.resumeTimer);
            },
          });
          Toast.fire({
            icon: "error",
            title:
              "<h4 style='color:#B00020'>Lamentablemente el producto no ha podido crearse. </br>Por favor intente más tarde.</h4>",
          });
        });
    }
  };

  return (
    <div>
      <form className={styles.container}>
        {(productPolicy.deliveryStandards === "" ||
          productPolicy.healthAndSecurity === "" ||
          productPolicy.cancellationPolicy === "") &&
          submit && (
            <p className={styles.error}>
              *Todos los campos son obligatorios. Por favor complete los campos
              vacios.
            </p>
          )}
        <div className={styles.container_textarea}>
          <div>
            <h4 className={styles.subtitle}>Normas de entrega</h4>
            <label className={styles.label}>Descripción</label>
            <textarea
              className={styles.textarea_product_policy}
              onChange={handleInputChange}
              name="deliveryStandards"
              placeholder="Escribir"
              value={productPolicy.deliveryStandards}
            />
          </div>
          <div>
            <h4 className={styles.subtitle}>Salud y seguridad</h4>

            <label className={styles.label}>Descripción</label>
            <textarea
              className={styles.textarea_product_policy}
              onChange={handleInputChange}
              name="healthAndSecurity"
              placeholder="Escribir"
              value={productPolicy.healthAndSecurity}
            />
          </div>
          <div>
            <h4 className={styles.subtitle}>Política de cancelación</h4>
            <label className={styles.label}>Descripción</label>
            <textarea
              className={styles.textarea_product_policy}
              onChange={handleInputChange}
              name="cancellationPolicy"
              placeholder="Escribir"
              value={productPolicy.cancellationPolicy}
            />
          </div>
        </div>

        <div className={styles.btn_container}>
          <div className={styles.btn}>
            <Button
              type="submit"
              title="Modificar"
              btnStyle="btn--full"
              btnSize="btn--responsive"
              onClick={onSubmit}
            />
          </div>
        </div>
      </form>
    </div>
  );
}

export default ModifyProductPolicySection;
