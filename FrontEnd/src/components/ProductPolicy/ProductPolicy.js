import React, { useState, useEffect } from "react";
import styles from "../ProductPolicy/ProductPolicy.module.css";
import axios from "axios";
import Url from "../../Url";

function ProductPolicy() {
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

  const deliveryStandards = productPolicy.deliveryStandards.split("/");
  const deliveryStandardsList = deliveryStandards.map((text, index) => {
    return <li key={`deliveryStandards_${index}`}>{text}</li>;
  });

  const healthAndSecurity = productPolicy.healthAndSecurity.split("/");
  const healthAndSecurityList = healthAndSecurity.map((text, index) => {
    return <li key={`healthAndSecurity_${index}`}>{text}</li>;
  });

  const cancellationPolicy = productPolicy.cancellationPolicy.split("/");
  const cancellationPolicyList = cancellationPolicy.map((text, index) => {
    return <li key={`cancellationPolicy_${index}`}>{text}</li>;
  });

  return (
    <div className={styles.product_policy}>
      <p className={styles.subtitle}>¿Qué tenés que saber?</p>
      <div className={styles.container}>
        <div>
          <p className={styles.subtitle_second_type}>Normas de entrega</p>
          <ul className={styles.list}>
            <li>Tolerancia de espera de 59 minutos</li>
            {deliveryStandardsList}
          </ul>
        </div>
        <div>
          <p className={styles.subtitle_second_type}>Salud y seguridad</p>
          <ul className={styles.list}>{healthAndSecurityList}</ul>
        </div>
        <div>
          <p className={styles.subtitle_second_type}>Política de cancelación</p>
          <ul className={styles.list}>{cancellationPolicyList}</ul>
        </div>
      </div>
    </div>
  );
}

export default ProductPolicy;
