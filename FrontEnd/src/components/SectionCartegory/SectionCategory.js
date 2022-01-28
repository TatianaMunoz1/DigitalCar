import React, { useEffect, useState } from "react";
import CardCategory from "../CardCategory/CardCategory";
import styles from "./SectionCategory.module.css";
import axios from "axios";
import Url from "../../Url";

function SectionCategory(props) {
  const [categories, setCategories] = useState([]);
  const cards = categories.map((category) => (
    <div key={`${category.id}_${category.title}`} className={styles.cards_box}>
      <CardCategory
        id={category.id}
        title={category.title}
        img={category.imgUrl}
        description={category.description}
        handlerSetCars={props.handlerSetCars}
      />
    </div>
  ));

  useEffect(() => {
    axios
      .get(Url+"/categories")
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {});
  }, []);

  return (
    <section className={styles.section}>
      <div className={styles.container}>
        <h2 className={styles.title}>Buscá por tipo de vehículo</h2>
        <p className={styles.text}>
          Contamos con una gran variedad de vehículos para todas las
          necesidades.
        </p>
        <div className={styles.cards}>{cards}</div>
      </div>
    </section>
  );
}

export default SectionCategory;
