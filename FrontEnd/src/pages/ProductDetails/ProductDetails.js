import React from "react";
import styles from "./ProductDetails.module.css";
import SectionProductDetail from "../../components/SectionProductDetail/SectionProductDetail";

function ProductDetails() {
  return (
    <div className={styles.conteiner}>
      <SectionProductDetail />
    </div>
  );
}

export default ProductDetails;
