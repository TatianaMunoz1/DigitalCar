import React from "react";
import { mount } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import Home from "../pages/Home/Home";

describe("Home", () => {
  const history = createMemoryHistory();
  // const user = {
  //   id: 0,
  //   name: "Juan",
  //   lastName: "Perez",
  //   email: "juanperez@gmail.com",
  //   token: "",
  // };

  const wrapper = mount(
    <Router history={history}>
      <Home {...user} />
    </Router>
  );

  test("must img exist", () => {
    // expect(wrapper.find("img").prop("src")).toEqual("logo.png");
    // expect(wrapper.find("img").prop("alt")).toEqual("SuccesIcon");
  });
  //   test("must render text", () => {
  //     expect(wrapper.text().includes("¡Muchas gracias!")).toBeTruthy();
  //     expect(
  //       wrapper.text().includes("Su reserva se ha realizado con éxito.")
  //     ).toBeTruthy();
  //   });
  //   test("button click", () => {
  //     wrapper.find(`button`).simulate("click");
  //   });
});
