import React, { useState, useEffect } from "react";
import Subheader from "../../components/Subheader/Subheader";
import styles from "./ReservationByUser.module.css";
import axios from "axios";
import Url from "../../Url";

function ReservationByUser(props) {
  const [reservations, setReservations] = useState([]);

  useEffect(() => {
    const params = {
      params: {
        userId: props.userId,
      },
    };
    axios
      .get(Url+"/booking/find", params)
      .then((response) => {
        if (response.data.length > 0) setReservations(response.data);
      })
      .catch((error) => {});
  }, []);

  const stateReservation = (index) => {
    if (new Date() < new Date(reservations[index].startDateTime.slice(0, 10))) {
      return "Pendiente";
    } else {
      return "Concretado";
    }
  };

  const reservationList = () =>
    reservations.map((reservation, index) => (
      <tr>
        <td>{index + 1}</td>
        <td>{reservation.startDateTime.slice(0, 10)}</td>
        <td>{reservation.endDate}</td>
        <td>{reservation.startDateTime.slice(11, 16)} hs</td>
        <td>{stateReservation(index)}</td>
      </tr>
    ));
  const reservationListMobile = () =>
    reservations.map((reservation, index) => (
      <tr>
        <tr>
          <td>PEDIDO # </td>
          <td> {index + 1}</td>
        </tr>
        <tr>
          <td>FECHA RETIRO </td>
          <td> {reservation.startDateTime.slice(0, 10)}</td>
        </tr>
        <tr>
          <td>FECHA DEVOLUCION </td>
          <td> {reservation.endDate}</td>
        </tr>
        <tr>
          <td>HORA RETIRO </td>
          <td> {reservation.startDateTime.slice(11, 16)} hs</td>
        </tr>
        <tr>
          <td>ESTADO </td>
          <td> {stateReservation(index)}</td>
        </tr>
      </tr>
    ));

  return (
    <div className={styles.container}>
      <div className={styles.container_header}>
        <Subheader title={"Mis Reservas"} link={"/"} />
      </div>
      <section className={styles.container_reservations}>
        <div>
          <div className={styles.container_table_mobile}>
            <h3>Lista de reservas</h3>
            <table>
              <tbody>{reservationListMobile()}</tbody>
            </table>
          </div>

          <table className={styles.container_table}>
            <thead className={styles.thead}>
              <tr>
                <th>PEDIDO #</th>
                <th>FECHA RETIRO</th>
                <th>FECHA DEVOLUCION</th>
                <th>HORA RETIRO</th>
                <th>ESTADO</th>
              </tr>
            </thead>
            <tbody>{reservationList()}</tbody>
          </table>
        </div>
      </section>
    </div>
  );
}

export default ReservationByUser;
