import React, { useState } from "react";
import styles from "./CardProduct.module.css";
import Button from "../Button/Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart, faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";
import { faHeart as farFaHeart } from "@fortawesome/free-regular-svg-icons";
import Rating from "../Rating/Rating";

function CardProduct(props) {
  const [favClicked, setFavClicked] = useState(false);
  const [showMore, setShowMore] = useState(false);
  const [car, setCar] = useState({ ...props });

  const handleFavChange = () => {
    setFavClicked(!favClicked);
  };

  const toggleShowMore = () => {
    setShowMore(!showMore);
  };

  return (
    <div key={`${car.id}_${car.name}`} className={styles.container}>
      <div className={styles.image_container}>
        <FontAwesomeIcon
          icon={favClicked ? faHeart : farFaHeart}
          id={`carFav${car.id}`}
          className={favClicked ? styles.icon_heart_clicked : styles.icon_heart}
          onClick={() => {
            car.handleFav(car.id);
            handleFavChange();
          }}
        />
        <img src={car.img} alt={car.title} className={styles.image} />
      </div>
      <div className={styles.information}>
        <div className={styles.header}>
          <div className={styles.category}>
            {car.category}
            <Rating
              car={car}
              dontShowLabel={true}
              dontShowRating={true}
              className={styles.stars_rating}
            />
          </div>
          <h3 className={styles.title}>{car.title}</h3>
          <p>
            <FontAwesomeIcon icon={faMapMarkerAlt} />
            <span>
              &nbsp; {car.address}. {car.city},
            </span>
            <span> {car.country}</span>
          </p>
        </div>

        <div className={styles.body_information}>
          {car.features.map((feature, index) => (
            <p key={`${car.id}feature${index}`} className={styles.details}>
              <span className={styles.icon_details}>
                <i className={`${feature.icon}`}></i>
              </span>
              <span> {feature.name}</span>
            </p>
          ))}
          <div>
            <p
              className={[
                styles.description,
                showMore ? styles.show_all : "",
              ].join(" ")}
            >
              {car.description}
            </p>
            {showMore ? (
              <span className={styles.btn_show_all} onClick={toggleShowMore}>
                ver menos
              </span>
            ) : (
              <span className={styles.btn_show_all} onClick={toggleShowMore}>
                mas...
              </span>
            )}
          </div>
        </div>
        <Button
          title="Ver más"
          link={`/product-detail/${car.id}`}
          btnStyle="btn--full"
          btnSize="btn--responsive"
          onClick={() => null}
        />
      </div>
    </div>
  );
}

export default CardProduct;
