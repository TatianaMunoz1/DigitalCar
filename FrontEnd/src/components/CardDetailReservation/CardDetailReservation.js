import React from "react";
import { useHistory } from "react-router-dom";
import styles from "../CardDetailReservation/CardDetailReservation.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";
import Rating from "../Rating/Rating";
import Button from "../Button/Button";
import Url from "../../Url"
import axios from "axios";
import Swal from "sweetalert2";

function CardDetailReservation(props) {
  const car = props.car;
  const dateReservation = props.dateReservation;
  const userCityInput = props.userCityInput;
  const history = useHistory();

  const onSubmit = (e) => {
    e.preventDefault();

    if (
      userCityInput === null ||
      dateReservation.startTime === null ||
      dateReservation.endDate === null ||
      dateReservation.startDate === null
    ) {
      props.handleValidation(false);
    } else {
      const config = {
        headers: {
          Authorization: props.user.token,
        },
      };

      const body = {
        car: { id: car.id },
        startDateTime:
          dateReservation.startDate.toISOString().slice(0, 11) +
          dateReservation.startTime.toISOString().slice(11, 21),
        endDate: dateReservation.endDate.toISOString().slice(0, 10),
        user: { email: props.user.email },
      };

      axios
        .post(Url+"/booking", body, config)
        .then(function (response) {
          if (response.status === 201) {
            history.push("/succes-reservation");
          } else {
            const Toast = Swal.mixin({
              toast: true,
              position: "top-end",
              showConfirmButton: false,
              background: "#ffc2c2",
              timer: 3000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener("mouseenter", Swal.stopTimer);
                toast.addEventListener("mouseleave", Swal.resumeTimer);
              },
            });
            Toast.fire({
              icon: "error",
              title:
                "<h4 style='color:#B00020'>Para realizar una reserva necesitas estar logueado</h4>",
            });
          }
        })
        .catch(function (error) {
          const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            background: "#ffc2c2",
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener("mouseenter", Swal.stopTimer);
              toast.addEventListener("mouseleave", Swal.resumeTimer);
            },
          });
          Toast.fire({
            icon: "error",
            title:
              "<h4 style='color:#B00020'>No se pudo procesar su solicitud</h4>",
          });
        });
    }
  };

  return car ? (
    <div className={styles.container}>
      <h1 className={styles.title}>Detalle de la reserva</h1>
      <div className={styles.container_information}>
        <div className={styles.image_container}>
          <img
            src={car.images[0].url}
            alt={car.title}
            className={styles.image}
          />
        </div>
        <form className={styles.information} onSubmit={onSubmit}>
          <div className={styles.datails_box}>
            <div className={styles.datails}>
              <h4 className={styles.category}>
                {car.category.title.toUpperCase()}
              </h4>
              <h1 className={styles.carName}>{car.name}</h1>
              <Rating dontShowLabel={true} dontShowRating={true} />
              <h4 className={styles.subtitle_location}>
                <FontAwesomeIcon
                  icon={faMapMarkerAlt}
                  className={styles.mapBtn}
                />
                &nbsp;
                {car.address}
                {car.city.name}, {car.city.country}
              </h4>
            </div>
            <div className={styles.dates_box}>
              <p className={styles.dates}>
                <span>Check in</span>
                {props.dateReservation.startDate !== null ? (
                  <span className={styles.date_start}>
                    {props.dateReservation.startDate
                      .toLocaleDateString("es")
                      .toString()}
                  </span>
                ) : (
                  <span className={styles.date_space}>_/_/_</span>
                )}
              </p>
              <p className={styles.dates}>
                <span>Check out</span>
                {props.dateReservation.endDate !== null ? (
                  <span className={styles.date_end}>
                    {props.dateReservation.endDate
                      .toLocaleDateString("es")
                      .toString()}
                  </span>
                ) : (
                  <span className={styles.date_space}>_/_/_</span>
                )}
              </p>
            </div>
          </div>
          <div className={styles.btn_reservation}>
            <Button
              title="Confirmar reserva"
              link={`/${car.id}/reservation`}
              type="submit"
              btnStyle="btn--full"
              btnSize="btn--responsive"
              onClick={onSubmit}
            />
          </div>
        </form>
      </div>
    </div>
  ) : (
    <></>
  );
}

export default CardDetailReservation;
