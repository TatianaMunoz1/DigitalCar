import React, { useState, useEffect } from "react";
import styles from "./SectionProductDetail.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";
import CalendarReservation from "../CalendarReservation/CalendarResevation";
import Button from "../Button/Button";
import Gallery from "../Gallery/Gallery";
import Map from "../MapComponent/Map";
import ProductPolicy from "../ProductPolicy/ProductPolicy";
import { useParams } from "react-router-dom";
import axios from "axios";
import Rating from "../Rating/Rating";
import ShareButton from "../ShareButton/ShareButton";
import FavoriteHeart from "../FavoriteHeart/FavoriteHeart";
import Subheader from "../Subheader/Subheader";
import Url from "../../Url";

function SectionProductDetail() {
  const [car, setCar] = useState(null);
  const { carId } = useParams();

  useEffect(() => {
    axios
      .get(Url+"/cars/" + carId)
      .then((response) => {
        setCar(response.data);
      })
      .catch((error) => {});
  }, []);

  return car ? (
    <div className={styles.container}>
      <Subheader subtitle={car.category.title} title={car.name} link={"/"} />
      <div className={styles.ubication_box}>
        <div className={styles.ubication}>
          <FontAwesomeIcon icon={faMapMarkerAlt} className={styles.map_icon} />
          <div>
            <p className={styles.city}>{car.city.name}</p>
            <p className={styles.country}>{car.city.country}</p>
          </div>
        </div>
        <Rating />
      </div>
      <div>
        <div className={styles.image_container}>
          <div className={styles.icons}>
            <ShareButton url={`http://localhost:8080/cars/${carId}`} />
            <FavoriteHeart />
          </div>
          <Gallery images={car.images} />
        </div>
      </div>
      <div className={styles.description_container}>
        <h3>Viajá cómodo en un {car.name}</h3>
        <div className={styles.description_box}>
          <div className={styles.description}>
            <h4>Esta oferta incluye</h4>
            <p>{car.description}</p>
          </div>
          <div className={styles.details_box}>
            <p className={styles.subtitle}>Características</p>
            <div className={styles.details_items_box}>
              {car.features.map((feature) => (
                <p
                  key={`${feature.id}_${feature.name}`}
                  className={styles.details}
                >
                  <span className={styles.icon_details}>
                    <i className={`${feature.icon}`}></i>
                  </span>
                  <span> {feature.name}</span>
                </p>
              ))}
            </div>
          </div>
        </div>
      </div>
      <div className={styles.calendar_reservation_container}>
        <p className={styles.subtitle_second_type}>Fechas disponibles</p>
        <div className={styles.calendar_reservation_box}>
          <div className={styles.calendar_reservation}>
            <div className={styles.datePicker_one_month_reservation}>
              <CalendarReservation
                datesExcluded={car ? car.datesExcluded : null}
                months={1}
              />
            </div>
            <div className={styles.datePicker_two_months_reservation}>
              <CalendarReservation
                datesExcluded={car ? car.datesExcluded : null}
                months={2}
              />
            </div>
          </div>
          <div className={styles.reservation_box}>
            <p className={styles.text_reservation}>
              Elegí tus fechas de viaje para obtener los mejores precios.
            </p>
            <div className={styles.btn_reserva}>
              <Button
                title="Iniciar reserva"
                link={`/${car.id}/reservation`}
                btnStyle="btn--full"
                btnSize="btn--responsive"
                onClick={() => null}
              />
            </div>
          </div>
        </div>
      </div>
      <div className={styles.map_section}>
        <p className={styles.subtitle}>¿Dónde vas a estar?</p>
        <p className={styles.text_ubication}>
          {car.city.name}, {car.city.country}
        </p>
        <Map latitude={car.city.latitude} longitude={car.city.longitude} />
      </div>
      <ProductPolicy />
    </div>
  ) : (
    <></>
  );
}

export default SectionProductDetail;
