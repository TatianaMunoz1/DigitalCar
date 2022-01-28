import React, { useState, useEffect } from "react";
import CardProduct from "../CardProduct/CardProduct";
import styles from "./SectionProducts.module.css";
import Button from "../Button/Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faChevronLeft,
  faChevronRight,
} from "@fortawesome/free-solid-svg-icons";

function SectionProducts(props) {
  const cars = props.cars;
  const carsXPage = props.carsXPage;
  const [favorites, setFavorites] = useState([]);
  const [page, setPage] = useState(1);
  const handleFav = (idFav) => {
    const array = [...favorites];
    const index = array.indexOf(idFav);
    if (index !== -1) {
      array.splice(index, 1);
      setFavorites([...array]);
    } else {
      array.push(idFav);
      setFavorites([...array]);
    }
  };

  const carList = () => {
    if (cars.length < 1) return <></>;

    if (
      carsXPage === null ||
      carsXPage === undefined ||
      carsXPage == 0 ||
      cars.length <= carsXPage
    )
      return cars.map((car) => (
        <CardProduct
          key={`${car.id}_${car.name}`}
          id={car.id}
          title={car.name}
          address={car.address}
          city={car.city.name}
          country={car.city.country}
          img={car.images[0].url}
          features={car.features}
          description={car.description}
          category={car.category.title.toUpperCase()}
          handleFav={handleFav}
        />
      ));

    let carsToRender = [];

    for (
      let i = 0 + (page - 1) * carsXPage;
      i < (page * carsXPage <= cars.length ? page * carsXPage : cars.length);
      i++
    ) {
      carsToRender.push(
        <CardProduct
          key={`${cars[i].id}_${cars[i].name}`}
          id={cars[i].id}
          title={cars[i].name}
          city={cars[i].city.name}
          country={cars[i].city.country}
          img={cars[i].images[0].url}
          features={cars[i].features}
          description={cars[i].description}
          category={cars[i].category.title.toUpperCase()}
          handleFav={handleFav}
        />
      );
    }
    return carsToRender;
  };

  // useEffect(() => {
  //   setPage(1);
  // }, []);

  return (
    <section className={styles.section}>
      <div className={styles.container}>
        <h2 className={styles.title}>{props.title}</h2>
        {cars && <div className={styles.cards}>{carList()}</div>}
      </div>
      {carsXPage != null && carsXPage > 0 && cars.length > carsXPage ? (
        <div className={styles.icon_chevron}>
          <FontAwesomeIcon
            icon={faChevronLeft}
            className={styles.icon_chevron_left}
            onClick={() => {
              if (page > 1) setPage(page - 1);
            }}
          />
          <p style={{ color: "white" }}>{`${1 + (page - 1) * carsXPage} - ${
            page * carsXPage <= cars.length ? page * carsXPage : cars.length
          } of ${cars.length}`}</p>
          <FontAwesomeIcon
            icon={faChevronRight}
            className={styles.icon_chevron_right}
            onClick={() => {
              if (page * carsXPage < cars.length) setPage(page + 1);
            }}
          />
        </div>
      ) : (
        <></>
      )}
    </section>
  );
}

export default SectionProducts;
