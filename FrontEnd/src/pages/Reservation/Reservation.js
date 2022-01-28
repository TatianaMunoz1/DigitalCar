import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import CardDetailReservation from "../../components/CardDetailReservation/CardDetailReservation";
import ProductPolicy from "../../components/ProductPolicy/ProductPolicy";
import CalendarReservation from "../../components/CalendarReservation/CalendarResevation";
import styles from "./Reservation.module.css";
import axios from "axios";
import TimePicker from "../../components/TimePicker/TimePicker";
import ReservationFormAlt from "../../components/ReservationForm/ReservationFormAlt";
import Subheader from "../../components/Subheader/Subheader";
import Url from "../../Url";

function Reservation(props) {
  const { carId } = useParams();
  const [car, setCar] = useState(null);
  const [startTime, setStartTime] = useState(null);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [userCityInput, setUserCityInput] = useState(null);
  const [validateInputs, setValidateInputs] = useState(null);

  useEffect(() => {
    axios
      .get(Url+"/cars/" + carId)
      .then((response) => {
        setCar(response.data);
      })
      .catch((error) => {});
  }, []);

  const handleTimeReservation = (date) => {
    setStartTime(date);
  };
  const handleDateReservation = (start, end) => {
    setStartDate(start);
    setEndDate(end);
  };

  const handleValidation = (submit) => {
    setValidateInputs(submit);
  };

  const handleValidationCity = (validationCityInput, userCityInput) => {
    validationCityInput && setUserCityInput(userCityInput);
  };

  return (
    <>
      <section className={styles.container}>
        <div className={styles.container_header}>
          {car && (
            <Subheader
              subtitle={car.category.title}
              title={car.name}
              link={`/product-detail/${carId}`}
            />
          )}
        </div>
        <div className={styles.container_section}>
          <h1 className={styles.subtitle}>
            Completá tus datos &nbsp;
            {validateInputs === false &&
              (userCityInput === null || userCityInput === "") && (
                <span className={styles.error_message}>
                  *Debes ingresar tu ciudad de residencia.
                </span>
              )}
          </h1>
          <div className={styles.container_reservation}>
            <div className={styles.container_box}>
              <div className={styles.container_reservationForm}>
                <ReservationFormAlt
                  user={props.user}
                  handleValidationCity={handleValidationCity}
                />
              </div>
              <div className={styles.container_calendarReservation}>
                <h1 className={styles.subtitle}>
                  Seleccioná tu fecha de reserva &nbsp;
                  {validateInputs === false &&
                    (startDate === null || endDate === null) && (
                      <span className={styles.error_message}>
                        *Debes seleccionar las fechas de inicio y fin de
                        reserva.
                      </span>
                    )}
                </h1>
                <div className={styles.datePicker_one_month_reservation}>
                  <CalendarReservation
                    months={1}
                    type={"calendar--picker"}
                    datesExcluded={car ? car.datesExcluded : null}
                    handleDateReservation={handleDateReservation}
                  />
                </div>
                <div className={styles.datePicker_two_months_reservation}>
                  <CalendarReservation
                    months={2}
                    type={"calendar--picker"}
                    datesExcluded={car ? car.datesExcluded : null}
                    handleDateReservation={handleDateReservation}
                  />
                </div>
              </div>
              <div className={styles.container_timePicker}>
                <h1 className={styles.subtitle}>
                  Tu horario de reserva &nbsp;
                  {validateInputs === false && startTime === null && (
                    <span className={styles.error_message}>
                      *Debes seleccionar un horario de retiro.
                    </span>
                  )}
                </h1>
                <TimePicker handleTimeReservation={handleTimeReservation} />
              </div>
            </div>
            <div className={styles.container_cardDetailReservation}>
              <CardDetailReservation
                car={car}
                dateReservation={{
                  startTime: startTime,
                  startDate: startDate,
                  endDate: endDate,
                }}
                user={props.user}
                handleValidation={handleValidation}
                userCityInput={userCityInput}
              />
            </div>
          </div>
        </div>
      </section>
      <ProductPolicy />
    </>
  );
}

export default Reservation;
