import React from "react";
import { shallow, mount, render } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import CardProduct from "../components/CardProduct/CardProduct.js";

const car = {
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

describe("CardProduct", () => {
  const history = createMemoryHistory();
  test("must img exist", () => {
    const wrapper = mount(
      <Router history={history}>
        <CardProduct {...car} />
      </Router>
    );

    expect(wrapper.find("img").prop("src")).toEqual(car.img);
    expect(wrapper.text().includes(car.description)).toBeTruthy();
  });
  test("fav click", () => {
    const wrapper = mount(
      <Router history={history}>
        <CardProduct {...car} />
      </Router>
    );
    //todo comprobar que cambia de ico
    //todo por q devuelve 2 nodos?
    wrapper.find(`#carFav${car.id}`).map((e) => e.simulate("click"));
  });
  test("button click", () => {
    const wrapper = mount(
      <Router history={history}>
        <CardProduct {...car} />
      </Router>
    );

    wrapper.find(`button`).simulate("click");
  });
});
