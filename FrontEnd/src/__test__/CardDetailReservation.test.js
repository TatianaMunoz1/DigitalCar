import React, { useEffect } from "react";
import { shallow, mount, render, mock } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import CardDetailReservation from "../components/CardDetailReservation/CardDetailReservation.js";
const carTest = {
    id: 1,
    title: "Citroën C4",
    seats: 5,
    doors: 5,
    transmission: "Manual",
    baggage: 2,
    description:
      "Esta oferta incluye 100km gratis! The fire truck braked the upfitted Express 1500. The mechanical Hino was developed by the Hino and once the tow truck totaled the seat belt.",
    category: "SUV",
    features: [{ name: "name", details: "details" }],
    img: "https://www.sixt.com.ar/fileadmin/files/global/user_upload/fleet/png/350x200/citroen-c4-5d-blue-2021.png",
    handleFav: () => {},
  };
  
describe("Create detail reservation", () => {
    const history = createMemoryHistory();
  test("must img exist", () => {
    const wrapper = mount(
      <Router history={history}>
        <CardDetailReservation {...carTest}/>
      </Router>
    );

    //expect(wrapper.find("img").prop("src")).toEqual(car.img);
    expect(wrapper.text().includes("Detalle de la reserva")).toBeTruthy();
  });

    test("render card detail reservation ", () => {
        const wrapper = shallow(<CardDetailReservation/>)
        expect(wrapper).toMatchSnapshot();
    });
});