import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import styles from "./CardCategory.module.css";
import axios from "axios";

function CardCategory(props) {
  const history = useHistory();
  const [car, setCar] = useState({ ...props });

  const handlerOnClick = () => {
    history.push("/search-page?category="+car.title)
    // axios
    //   .get("http://localhost:8080/cars/filter?category=" + car.title)
    //   .then((response) => {
    //     car.handlerSetCars();
    //     car.handlerSetCars(response.data);
    //   })
    //   .catch((error) => {});
  };

  return (
    <div className={styles.container} onClick={handlerOnClick}>
      <div className={styles.container_image}>
        <img className={styles.image} src={car.img} alt={car.title} />
      </div>
      <div className={styles.container_details}>
        <h3 className={styles.title}>{car.title}</h3>
        <p className={styles.description}>{car.description}</p>
      </div>
    </div>
  );
}

export default CardCategory;
