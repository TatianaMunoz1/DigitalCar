import React from "react";
import { mount, shallow } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import ReservationByUser from "../pages/ReservationByUser/ReservationByUser.js";

describe("ReservationByUser", () => {
  const history = createMemoryHistory();
  const dataReservations = [
    {
      id: 1,
      startDateTime: "2021-01-01T07:00:00.5",
      endDate: "2022-01-08",
    },
    {
      id: 2,
      startDateTime: "2020-01-01T07:00:00.5",
      endDate: "2022-01-08",
    },
    {
      id: 3,
      startDateTime: "2022-01-01T07:00:00.5",
      endDate: "2022-01-08",
    },
    {
      id: 4,
      startDateTime: "2022-01-01T22:00:00.5",
      endDate: "2022-01-08",
    },
  ];
  
  test("render Reservation by user ", () => {
        const wrapper = shallow(<ReservationByUser/>)
        expect(wrapper).toMatchSnapshot();
    });

  test("must be exist", () => {
    const wrapper = mount(
      <Router history={history}>
        <ReservationByUser {...dataReservations} />
      </Router>
    );
    expect(wrapper.text().includes("Mis Reservas")).toBeTruthy();
    expect(wrapper.text().includes("PEDIDO #")).toBeTruthy();
    expect(wrapper.text().includes("FECHA RETIRO")).toBeTruthy();
    expect(wrapper.text().includes("FECHA DEVOLUCION")).toBeTruthy();
    expect(wrapper.text().includes("HORA RETIRO")).toBeTruthy();
    expect(wrapper.text().includes("ESTADO")).toBeTruthy();
  });
});
